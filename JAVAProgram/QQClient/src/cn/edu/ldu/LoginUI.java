/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu;

import cn.edu.ldu.security.Cryptography;
import cn.edu.ldu.util.Message;
import cn.edu.ldu.util.Translate;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class LoginUI extends javax.swing.JDialog {

    /**
     * Creates new form LoginUI
     */
    public LoginUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        txtUserId = new javax.swing.JTextField();
        btnRegister = new javax.swing.JButton();
        btnGetPassword = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtRemoteName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtRemotePort = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        chkRemember = new javax.swing.JCheckBox();
        chkLogin = new javax.swing.JCheckBox();
        lblLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtUserId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserIdActionPerformed(evt);
            }
        });

        btnRegister.setText("注册账号");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnGetPassword.setText("找回密码");

        btnLogin.setText("登录");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel1.setText("服务器主机名：");

        txtRemoteName.setText("127.0.0.1");
        txtRemoteName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRemoteNameActionPerformed(evt);
            }
        });

        jLabel2.setText("服务器端口：");

        txtRemotePort.setText("50000");

        chkRemember.setText("记住密码");

        chkLogin.setText("自动登录");

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn/edu/ldu/images/Login.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRemoteName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLogo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGetPassword))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRegister))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chkRemember)
                                .addGap(18, 18, 18)
                                .addComponent(chkLogin))
                            .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(32, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegister))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGetPassword))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkRemember)
                            .addComponent(chkLogin)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblLogo)))
                .addGap(29, 29, 29)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtRemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRemoteName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserIdActionPerformed
    
    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
        try {
            String id = txtUserId.getText();
            String password = String.valueOf(txtPassword.getPassword());
            if (id.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "账号或密码不能为空！", "错误提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //获取服务器地址和端口
            String remoteName = txtRemoteName.getText();
            InetAddress remoteAddr = InetAddress.getByName(remoteName);
            int remotePort = Integer.parseInt(txtRemotePort.getText());
            //创建UDP套接字
            DatagramSocket clientSocket = new DatagramSocket();
            clientSocket.setSoTimeout(3000);//设置超时时间
            //构建用户注册消息
            Message msg = new Message();
            msg.setUserId(id);//注册名
            msg.setPassword(Cryptography.getHash(password, "SHA-256"));
            msg.setType("M_REGISTER");//登录消息类型
            msg.setToAddr(remoteAddr);//目标主机地址
            msg.setToPort(remotePort);//目标主机端口
            byte[] data = Translate.ObjectToByte(msg);//消息对象序列化与字节数组
            //定义注册报文
            DatagramPacket packet = new DatagramPacket(data, data.length, remoteAddr, remotePort);
            //发送注册报文
            clientSocket.send(packet);
            //接收服务器回送的报文
            DatagramPacket backPacket = new DatagramPacket(data, data.length);
            clientSocket.receive(backPacket);
            clientSocket.setSoTimeout(0);//取消超时时间
            Message backMsg = (Message) Translate.ByteToObject(data);
            //System.out.println(backMsg.getType());
            //处理登录结果
            if (backMsg.getType().equalsIgnoreCase("M_SUCCESS")) {
                //注册成功
                JOptionPane.showMessageDialog(null, "注册成功!\n", "注册成功", JOptionPane.ERROR_MESSAGE);
            } else {
                //注册失败
                JOptionPane.showMessageDialog(null, "用户ID已存在!\n", "注册失败", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "注册错误", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void txtRemoteNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRemoteNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRemoteNameActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        try {
            String id = txtUserId.getText();
            String password = String.valueOf(txtPassword.getPassword());
            if (id.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "账号或密码不能为空！", "错误提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //获取服务器地址和端口
            String remoteName = txtRemoteName.getText();
            InetAddress remoteAddr = InetAddress.getByName(remoteName);
            int remotePort = Integer.parseInt(txtRemotePort.getText());
            //创建UDP套接字
            DatagramSocket clientSocket = new DatagramSocket();
            clientSocket.setSoTimeout(3000);//设置超时时间
            //构建用户登录消息
            Message msg = new Message();
            msg.setUserId(id);//登录名
//            msg.setPassword(password);//密码

            msg.setPassword(Cryptography.getHash(password, "SHA-256"));
            msg.setType("M_LOGIN");//登录消息类型
            msg.setToAddr(remoteAddr);//目标主机地址
            msg.setToPort(remotePort);//目标主机端口
            byte[] data = Translate.ObjectToByte(msg);//消息对象序列化与字节数组
            //定义登录报文
            DatagramPacket packet = new DatagramPacket(data, data.length, remoteAddr, remotePort);
            //发送登录报文
            clientSocket.send(packet);
            
            //接收服务器回送的报文
            DatagramPacket backPacket = new DatagramPacket(data, data.length);
            clientSocket.receive(backPacket);
            clientSocket.setSoTimeout(0);//取消超时时间
            Message backMsg = (Message) Translate.ByteToObject(data);
            //System.out.println(backMsg.getType());
            //处理登录结果
            if (backMsg.getType().equalsIgnoreCase("M_SUCCESS")) {
                //登录成功
                this.dispose();//关闭登录对话框
                ClientUI client = new ClientUI(clientSocket, msg);//创建客户机界面
                client.setTitle(msg.getUserId());//设置标题
                client.setVisible(true);//显示会话窗体
            } else {
                //登录失败
                JOptionPane.showMessageDialog(null, "用户ID或密码错误!\n", "登录失败", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "登录错误", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

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
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginUI dialog = new LoginUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGetPassword;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JCheckBox chkLogin;
    private javax.swing.JCheckBox chkRemember;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtRemoteName;
    private javax.swing.JTextField txtRemotePort;
    private javax.swing.JTextField txtUserId;
    // End of variables declaration//GEN-END:variables
}