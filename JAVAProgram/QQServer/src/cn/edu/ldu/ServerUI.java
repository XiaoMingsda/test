/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author Administrator
 */
public class ServerUI extends javax.swing.JFrame {

    /**
     * Creates new form ServerUI
     */
    public ServerUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtHostName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtHostPort = new javax.swing.JTextField();
        btnStart = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("启动服务器"));

        jLabel1.setText("主机名：");

        txtHostName.setText("127.0.0.1");

        jLabel2.setText("端口：");

        txtHostPort.setText("50000");

        btnStart.setText("启动");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtHostName, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHostPort, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnStart)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtHostName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHostPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStart)
                    .addComponent(jLabel2))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("聊天室大厅"));

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:
        try {
            //获取服务器工作地址端口
            String hostName = txtHostName.getText();
            int hostPort = Integer.parseInt(txtHostPort.getText());
            //创建UDP数据报套接字，在指定端口侦听
            DatagramSocket serverSocket = new DatagramSocket(hostPort);
            txtArea.append("服务器开始侦听...\n");
            //创建UDP消息接收线程
            Thread recvThread = new ReceiveMessage(serverSocket, this);
            recvThread.start();//启动线程
            
            //实验十
            //创建并启动文件接收线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
 //                       SocketAddress serverAddr = new InetSocketAddress(InetAddress.getByName(hostName), hostPort);
 //                       ServerSocket listenSocket= new ServerSocket();//创建侦听套接字
  //                      listenSocket.bind(serverAddr);//绑定到工作地址
                        //用客户机密钥库初始化SSL传输框架
                        InputStream key = ServerUI.class.getResourceAsStream("/cn/edu/ldu/keystore/server.keystore");//私钥库
                        InputStream tkey = ServerUI.class.getResourceAsStream("/cn/edu/ldu/keystore/tserver.keystore");//公钥库
                        
                        String SERVER_KEY_STORE_PASSWORD = "123456";//server.keystore私钥库密码
                        String SERVER_TRUST_KEY_STORE_PASSWORD = "123456";//tserver.keystore公钥库密码
                        SSLContext ctx = SSLContext.getInstance("SSL");//SSL上下文
                        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");//私钥管理器
                        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");//公钥管理器
                        KeyStore ks = KeyStore.getInstance("JKS");//私钥库对象
                        KeyStore tks = KeyStore.getInstance("JKS");//公钥库对象
                        ks.load(key, SERVER_KEY_STORE_PASSWORD.toCharArray());//加载私钥库
                        tks.load(tkey,SERVER_TRUST_KEY_STORE_PASSWORD.toCharArray());//加载公钥库
                        kmf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());//私钥库访问初始化
                        tmf.init(tks);//公钥库访问初始化
                        //用私钥库和公钥库初始化SSL上下文
                        ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
                        //服务器侦听安全连接
                        SSLServerSocket sslListenSocket = (SSLServerSocket)ctx.getServerSocketFactory().createServerSocket(hostPort);
                        sslListenSocket.setNeedClientAuth(true);
                        int processors = Runtime.getRuntime().availableProcessors();//CPU数
                        ExecutorService fixedPool = Executors.newFixedThreadPool(processors * 2);//创建线程池
                        while (true) {
 //                           //accept()如果无连接，则阻塞，否则接受连接并创建新的会话套接字
//                            Socket fileSocket = listenSocket.accept();
//                            //文件接收线程为SwingWorker类型的后台工作线程
//                            SwingWorker<Integer, Object> recver = new RecvFile(fileSocket, ServerUI.this);
                            SSLSocket fileSocket = (SSLSocket)sslListenSocket.accept();
                            //创建客户线程
                            SwingWorker<Integer, Object> recver = new RecvFile(fileSocket, ServerUI.this, ks, tks);
                            System.out.println("recver");
                            fixedPool.execute(recver);
                        }
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }).start();
            
//            
//            //启动语音接收线程
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while (true) {
//                        try
//                    {
//                        ServerSocket serSock=new ServerSocket(6000);
//                        Socket cli=serSock.accept();
//                        System.out.println("接收到链接");
//                        ServerUI.joinPhone.add(cli);//有客户机加入到语音聊天
////                        for (Socket socket : ServerUI.joinPhone) {
////                            BufferedWriter o = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
////                            o.write("哈哈");
////                            o.newLine();
////                            o.flush();
////                            System.out.println("发送成功");
////                        }
////                        System.out.println("结束发送");
//                        Playback player=new Playback(cli);
//                        player.start();
//                    }
//                    catch(Exception e)
//                    {
//                    }
//                    }
//                }
//            }).start();
            
            
        } catch (NumberFormatException | SocketException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
        }
        btnStart.setEnabled(false);
    }//GEN-LAST:event_btnStartActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerUI().setVisible(true);
            }
        });
    }
    public static List<Socket> joinPhone = new ArrayList<>();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtHostName;
    private javax.swing.JTextField txtHostPort;
    // End of variables declaration//GEN-END:variables
}
