/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLSocket;
import javax.swing.SwingWorker;
import javax.xml.bind.DatatypeConverter;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Administrator
 */
public class RecvFile extends SwingWorker<Integer, Object>{ 
    private final SSLSocket fileSocket;//接收文件的套接字
    private ServerUI parentUI;//主窗体类
    private static final int BUFSIZE = 8096;//缓冲区大小
    private  KeyStore ks;//私钥库对象
    private  KeyStore tks;//公钥库对象
    public RecvFile(SSLSocket fileSocket, ServerUI parentUI, KeyStore ks, KeyStore tks) {
        this.fileSocket = fileSocket;
        this.parentUI = parentUI;
        this.ks = ks;
        this.tks = tks;
    }

    @Override
    protected Integer doInBackground() throws Exception {System.out.println("线程运行了1");
        String SERVER_KEY_STORE_PASSWORD = "123456"; //server.keystore私钥库密码
        //获取服务器私钥
        PrivateKey privateKey=(PrivateKey)ks.getKey("server",SERVER_KEY_STORE_PASSWORD.toCharArray());
        System.out.println("privateKey：" + privateKey);
        //获取客户机公钥
        PublicKey publicKey=(PublicKey)tks.getCertificate("client").getPublicKey();
        //获取套接字输入流
        DataInputStream in=new DataInputStream(
                           new BufferedInputStream(
                           fileSocket.getInputStream()));   
        System.out.println("线程运行了2");
        
        //接收文件名、文件长度
        String filename = in.readUTF();//文件名
        int fileLen = (int)in.readLong();//文件长度
        System.out.println("接收到了");
        parentUI.txtArea.append("1.收到文件名：" + filename + "文件长度：" + fileLen + " 字节\n\n");
        //创建文件输出流
        File file = new File("src/cn/edu/ldu/upload/" + filename);
 //       //文件输出流
//        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(file));
        //定义摘要算法
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");//256位
        //基于文件输出流和摘要算法构建消息摘要流
        DigestOutputStream out = new DigestOutputStream(fout, sha256);
        //接收文件内容，存储为外部文件
        byte[] buffer = new byte[BUFSIZE];//读入缓冲区
        int numRead = 0;//单次读取的字节数
        int numFinished = 0;//完成字节总数
        while (numFinished < fileLen && (numRead = in.read(buffer)) != -1) {//输入流可读
            out.write(buffer, 0, numRead);
            numFinished += numRead;//已完成字节数
            
        }
        parentUI.txtArea.append("2.接收文件内容结束！\n\n");
        //3.接收加密的数字签名
        int size = in.readInt();
        byte[] signature = new byte[size];
        int i = in.read(signature);
        parentUI.txtArea.append("3.收到加密的数字签名：" + DatatypeConverter.printHexBinary(signature) + "\n\n");
        //4.接收加密的密钥
        byte[] encryptKey = new byte[128];
        i = in.read(encryptKey);
        parentUI.txtArea.append("4.收到加密的密钥：" + DatatypeConverter.printHexBinary(encryptKey) + "\n\n");
        //用服务器私钥解密密钥
        Cipher cipher = Cipher.getInstance("RSA");//解密器
        System.out.println("停止执行1");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);//用服务器私钥初始化解密器
        System.out.println("停止执行2");
        byte[] decryptKey = cipher.doFinal(encryptKey);//解密密钥
        parentUI.txtArea.append("密钥解密：" + DatatypeConverter.printHexBinary(decryptKey) + "\n\n");
        //用密钥解压数字签名
        SecretKey secretKey = new SecretKeySpec(decryptKey, "AES");
        Cipher cipher2 = Cipher.getInstance("AES");//解密器
        cipher2.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptSign = cipher2.doFinal(signature);//解密数字签名
        parentUI.txtArea.append("签名解密：" + DatatypeConverter.printHexBinary(decryptSign) + "\n\n");
        //"SHA-256"算法计算的摘要为256位，合32字节
        byte[] sourceDigest = new byte[32];//收到的摘要
        cipher.init(Cipher.DECRYPT_MODE, publicKey);//用客户机公钥初始化解密器
        sourceDigest = cipher.doFinal(decryptSign);//还原消息摘要
        parentUI.txtArea.append("去掉签名后的摘要：" + DatatypeConverter.printHexBinary(sourceDigest) + "\n\n");//更新显示
        //5.根据文件输出流重新计算消息摘要
        byte[] computedDigest = new byte[32];//重新计算的摘要
        computedDigest = out.getMessageDigest().digest();
        //输出相关提示信息
        parentUI.txtArea.append("服务器根据收到的文件重新计算的摘要：" + DatatypeConverter.printHexBinary(computedDigest) + "\n\n");
        //定义字符输出流
        PrintWriter pw = new PrintWriter(fileSocket.getOutputStream(), true);
        if (Arrays.equals(sourceDigest, computedDigest)) {
            if (numFinished >= fileLen) {//文件接收完成
                System.out.println("执行到这");
                pw.println("M_DONE");//回送成功消息
                parentUI.txtArea.append(filename + " 接收成功！\n");
            } else {
                pw.println("M_LOST");//回送失败消息
                parentUI.txtArea.append(filename + " 接收失败！\n");
            }
        }
        //关闭流
        in.close();
        out.close();
        pw.close();
        fileSocket.close();

        return 100;
    }

   

    @Override
    protected void done() {
//        try {
//////                 String bye = "Bye!";
//////		byte[] buffer = new byte[50];
//////		while(true) {
//////			InputStream in = fileSocket.getInputStream();
//////			in.read(buffer);
//////			System.out.println("Received: " + new String(buffer));
//////			OutputStream out = fileSocket.getOutputStream();
//////			out.write(bye.getBytes());
//////			out.flush();
//////		}
//////获取套接字输入流
////        DataInputStream in=new DataInputStream(
////                           new BufferedInputStream(
////                           fileSocket.getInputStream()));   
////        System.out.println("线程运行了2");
////        //接收文件名、文件长度
////        String filename = in.readUTF();//文件名
////        int fileLen = (int)in.readLong();//文件长度
////        System.out.println("接收到了" + " " + filename + " " + fileLen);
//
//         String SERVER_KEY_STORE_PASSWORD = "123456"; //server.keystore私钥库密码
//        //获取服务器私钥
//        PrivateKey privateKey=(PrivateKey)ks.getKey("server",SERVER_KEY_STORE_PASSWORD.toCharArray());
//        //获取客户机公钥
//       // PublicKey publicKey=(PublicKey)tks.getCertificate("client").getPublicKey();
//       CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
//        FileInputStream bais = new FileInputStream("src/cn/edu/ldu/keystore/dongxiangzhi.cer");
//        X509Certificate Cert = (X509Certificate) certificatefactory.generateCertificate(bais);
//        PublicKey publicKey = Cert.getPublicKey();
//        BASE64Encoder bse = new BASE64Encoder();
//        String value = bse.encode(publicKey.getEncoded());
//        System.out.println("publicKey:" + value);
//        //获取套接字输入流
//        DataInputStream in=new DataInputStream(
//                           new BufferedInputStream(
//                           fileSocket.getInputStream()));   
//        System.out.println("线程运行了2");
//        
//        //接收文件名、文件长度
//        String filename = in.readUTF();//文件名
//        int fileLen = (int)in.readLong();//文件长度
//        System.out.println("接收到了");
//        parentUI.txtArea.append("1.收到文件名：" + filename + "文件长度：" + fileLen + " 字节\n\n");
//        //创建文件输出流
//        File file = new File("src/cn/edu/ldu/upload/" + filename);
// //       //文件输出流
////        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
//        BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(file));
//        //定义摘要算法
//        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");//256位
//        //基于文件输出流和摘要算法构建消息摘要流
//        DigestOutputStream out = new DigestOutputStream(fout, sha256);
//        //接收文件内容，存储为外部文件
//        byte[] buffer = new byte[BUFSIZE];//读入缓冲区
//        int numRead = 0;//单次读取的字节数
//        int numFinished = 0;//完成字节总数
//        while (numFinished < fileLen && (numRead = in.read(buffer)) != -1) {//输入流可读
//            out.write(buffer, 0, numRead);
//            numFinished += numRead;//已完成字节数
//        }
//        parentUI.txtArea.append("2.接收文件内容结束！\n\n");
//        //3.接收加密的数字签名
//        int size = in.readInt();
//        byte[] signature = new byte[size];
//        int i = in.read(signature);
//        parentUI.txtArea.append("3.收到加密的数字签名：" + DatatypeConverter.printHexBinary(signature) + "\n\n");
//        //4.接收加密的密钥
//        byte[] encryptKey = new byte[128];
//        i = in.read(encryptKey);
//        parentUI.txtArea.append("4.收到加密的密钥：" + DatatypeConverter.printHexBinary(encryptKey) + "\n\n");
//        //用服务器私钥解密密钥
//        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");//解密器
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);//用服务器私钥初始化解密器
//        byte[] decryptKey = cipher.doFinal(encryptKey);//解密密钥
//        parentUI.txtArea.append("密钥解密：" + DatatypeConverter.printHexBinary(decryptKey) + "\n\n");
//        //用密钥解压数字签名
//        SecretKey secretKey = new SecretKeySpec(decryptKey, "AES");
//        Cipher cipher2 = Cipher.getInstance("AES");//解密器
//        cipher2.init(Cipher.DECRYPT_MODE, secretKey);
//        byte[] decryptSign = cipher2.doFinal(signature);//解密数字签名
//        parentUI.txtArea.append("签名解密：" + DatatypeConverter.printHexBinary(decryptSign) + "\n\n");
//        //"SHA-256"算法计算的摘要为256位，合32字节
//        byte[] sourceDigest = new byte[32];//收到的摘要
//        cipher.init(Cipher.DECRYPT_MODE, publicKey);//用客户机公钥初始化解密器
//        sourceDigest = cipher.doFinal(decryptSign);//还原消息摘要
//        parentUI.txtArea.append("去掉签名后的摘要：" + DatatypeConverter.printHexBinary(sourceDigest) + "\n\n");//更新显示
//        //5.根据文件输出流重新计算消息摘要
//        byte[] computedDigest = new byte[32];//重新计算的摘要
//        computedDigest = out.getMessageDigest().digest();
//        //输出相关提示信息
//        parentUI.txtArea.append("服务器根据收到的文件重新计算的摘要：" + DatatypeConverter.printHexBinary(computedDigest) + "\n\n");
//        //定义字符输出流
//        PrintWriter pw = new PrintWriter(fileSocket.getOutputStream(), true);
//        if (Arrays.equals(sourceDigest, computedDigest)) {
//            if (numFinished >= fileLen) {//文件接收完成
//                System.out.println("执行到这");
//                pw.println("M_DONE");//回送成功消息
//                parentUI.txtArea.append(filename + " 接收成功！\n");
//            } else {
//                pw.println("M_LOST");//回送失败消息
//                parentUI.txtArea.append(filename + " 接收失败！\n");
//            }
//        }
//        //关闭流
//        in.close();
//        out.close();
//        pw.close();
//        fileSocket.close();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
    }
    
}
