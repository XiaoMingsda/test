/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu;

import cn.edu.ldu.security.Cryptography;
import cn.edu.ldu.util.Message;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.DigestInputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import javax.swing.SwingWorker;
import javax.xml.bind.DatatypeConverter;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Administrator
 */
public class FileSender extends SwingWorker<List<String>, String>{
    private File file;//文件
    private Message msg;//消息类
    private ClientUI parentUI;//父类
//    private Socket fileSocket;//传送文件的套接字
    private static final int BUFSIZE = 8096;//缓冲区的大小
    private int progress = 0;//文件传送进度
    private String lastResults = null;//传送结果
    private SSLSocket fileSocket;//传送文件的套接字
    //构造函数

    public FileSender(File file, Message msg, ClientUI parentUI) {
        this.file = file;
        this.msg = msg;
        this.parentUI = parentUI;
    }

    @Override
    protected List<String> doInBackground() throws Exception {
        System.out.println("Hello");
//        fileSocket = new Socket();
//        //连接服务器
//        SocketAddress remoteAddr = new InetSocketAddress(msg.getToAddr(), msg.getToPort());
//        fileSocket.connect(remoteAddr);
        
        //用客户机密钥库初始化SSL传输框架
        InputStream key = ClientUI.class.getResourceAsStream("/cn/edu/ldu/keystore/client.keystore");//私钥库
        InputStream tkey = ClientUI.class.getResourceAsStream("/cn/edu/ldu/keystore/tclient.keystore");//公钥库
        System.out.println(key);
        System.out.println(tkey);
        String CLIENT_KEY_STORE_PASSWORD = "123456";//client.keystore私钥库密码
        String CLIENT_TRUST_KEY_STORE_PASSWORD = "123456";//tclient.keystore公钥库密码
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");//私钥管理器
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");//公钥管理器
        KeyStore ks = KeyStore.getInstance("JKS");//私钥库对象
        KeyStore tks = KeyStore.getInstance("JKS");//公钥库对象
        ks.load(key, CLIENT_KEY_STORE_PASSWORD.toCharArray());//加载私钥库
        tks.load(tkey, CLIENT_TRUST_KEY_STORE_PASSWORD.toCharArray());//加载公钥库
        kmf.init(ks, CLIENT_KEY_STORE_PASSWORD.toCharArray());//私钥库访问初始化
        tmf.init(tks);//公钥库访问初始化
        //用私钥库和公钥库初始化SSL上下文
        SSLContext ctx = SSLContext.getInstance("SSL");//SSL上下文
        ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        //用SSLSocket连接服务器
        fileSocket = (SSLSocket)ctx.getSocketFactory().createSocket(msg.getToAddr(), msg.getToPort());
        //构建套接字输出流
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(fileSocket.getOutputStream()));
        //获取客户机私钥
        PrivateKey privateKey = (PrivateKey)ks.getKey("client", CLIENT_KEY_STORE_PASSWORD.toCharArray());
        System.out.println("privateKey：" + privateKey);
        //获取服务器公钥
        PublicKey publicKey = (PublicKey)tks.getCertificate("server").getPublicKey();
        //定义摘要算法
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");//256位
        //构建文件输入流
        DataInputStream din = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        //基于输入流和摘要算法构建消息摘要流
        DigestInputStream in = new DigestInputStream(din, sha256);
        
        
        //out.write(hello.getBytes(), 0, hello.getBytes().length);
        //out.write(file.getName().getBytes(), 0, file.getName().getBytes().length);
        //out.write(23);
        //out.flush();
        System.out.println("成功执行了");
        
        long fileLen = file.length();//计算文件长度
        //发送文件名称、文件长度
        out.writeUTF(file.getName());
        out.writeLong(fileLen);
        System.out.println("1234");
        out.flush();
        System.out.println("123");
        parentUI.txtArea.append("1.发送文件名称、文件长度成功！\n");
        //传送文件内容
        int numRead = 0;//单词读取的字节数
        int numFinished = 0;//总完成字节数
        byte[] buffer = new byte[BUFSIZE];
        while (numFinished < fileLen && (numRead = in.read(buffer)) != -1) {//文件可读
            out.write(buffer, 0, numRead);
            out.flush();
            numFinished += numRead;//已完成字节数
  //          Thread.sleep(1000);//演示文件传输进度用
            publish(numFinished + "/" + fileLen + "bytes");
            setProgress(numFinished * 100 / (int)fileLen);
        }
        in.close();
        din.close();
        parentUI.txtArea.append("2.传送文件内容成功!\n");
        byte[] fileDigest = in.getMessageDigest().digest();//生成文件摘要
        parentUI.txtArea.append("生成的摘要：" + DatatypeConverter.printHexBinary(fileDigest) + "\n\n");
        //用私钥对摘要加密，形成文件的数字签名
        Cipher cipher = Cipher.getInstance("RSA");//加密器
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);//用个人私钥初始化加密模式
        byte[] signature = cipher.doFinal(fileDigest);//计算数字签名
        parentUI.txtArea.append("生成的数字签名：" + DatatypeConverter.printHexBinary(signature) + "\n\n");//更新显示
        //生成AES对称密钥
        SecretKey secretKey = Cryptography.generateNewKey();
        parentUI.txtArea.append("生成的密钥：" + DatatypeConverter.printHexBinary(secretKey.getEncoded()) + "\n\n");
        //对数字签名加密
        Cipher cipher2 = Cipher.getInstance("AES");
        cipher2.init(Cipher.ENCRYPT_MODE, secretKey);//初始化加密器
        byte[] encryptSign = cipher2.doFinal(signature);//生成加密签名
        parentUI.txtArea.append("用密钥加密后的数字签名：" + DatatypeConverter.printHexBinary(encryptSign) + "\n\n");
        //对密钥加密
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);//用服务器公钥初始化加密模式
        byte[] encryptKey = cipher.doFinal(secretKey.getEncoded());//加密密钥
        parentUI.txtArea.append("对密钥加密：" + DatatypeConverter.printHexBinary(encryptKey) + "\n\n");
        //3.发送加密后的数字签名
        out.writeInt(encryptSign.length);
        out.flush();
        out.write(encryptSign);
        out.flush();
        parentUI.txtArea.append("3.发送加密的数字签名成功!\n");
        //4.发送加密密钥
        out.write(encryptKey);
        out.flush();
        parentUI.txtArea.append("4.发送加密的密钥成功!\n");
        //接收服务器反馈信息
        BufferedReader br = new BufferedReader(new InputStreamReader(fileSocket.getInputStream()));
        String response = br.readLine();//读取返回串
        if (response.equalsIgnoreCase("M_DONE")) {//服务器成功接收
            lastResults = file.getName() + " 传送成功！\n";
        } else if (response.equalsIgnoreCase("M_LOST")) {//服务器接收失败
            lastResults = file.getName() + " 传送失败！\n";
        }
        //关闭流
        br.close();
        out.close();
        fileSocket.close();

        return null;
    }
    
    @Override
    protected void process(List<String> middleResults) {
        for (String str:middleResults) {
            parentUI.progressLable.setText(str);
        }
                
    }
    
    @Override
    protected void done() {
        parentUI.progressBar.setValue(parentUI.progressBar.getMaximum());
        parentUI.txtArea.append(lastResults + "\n");
        parentUI.filePanel.setVisible(false);
    }
    
}
