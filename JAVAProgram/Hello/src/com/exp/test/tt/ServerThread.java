package com.exp.test.tt;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class ServerThread extends Thread {
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private HashMap<String,ServerThread> threads;

    public ServerThread(Socket socket,HashMap<String,ServerThread> threads){
        this.socket = socket;
        this.threads = threads;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Socket getSocket(){
        return socket;
    }
    @Override
    public void run() {
        String infor = socket.getInetAddress()+":"+socket.getPort();
        IOUtil.writeString(os,infor+"你好");
        while (true){
            //等待客户端发送消息
            String msg = IOUtil.readString(is);
            String response = infor+">"+msg;
            if(msg.split(" ")[0].equals("allClients")){
                String users = "";
                Iterator<String> iterator = threads.keySet().iterator();
                while (iterator.hasNext()){
                    String user = iterator.next();
                    if (!user.equals(infor)){
                        users += "\n"+user;
                    }
                }
                response += users;
            }else if (msg.split(" ")[0].equals("sto")){
                String user = msg.split(" ")[1];
                if (threads.keySet().contains(user)){
                    Socket userSocket = threads.get(user).getSocket();
                    try {
                        OutputStream userSocketOutputStream = userSocket.getOutputStream();
                        IOUtil.writeString(userSocketOutputStream,infor+"说:"+msg.split(" ")[2]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    response = response+"\n用户不存在";
                }
            }else if (msg.split(" ")[0].equals("sta")){
                Iterator<String> users = threads.keySet().iterator();
                while (users.hasNext()){
                    String user = users.next();
                    if (!user.equals(infor)){
                        Socket userSocket = threads.get(user).getSocket();
                        try {
                            OutputStream userOs = userSocket.getOutputStream();
                            IOUtil.writeString(userOs,infor+"说:"+msg.split(" ")[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            IOUtil.writeString(os,response);
        }
    }
}
