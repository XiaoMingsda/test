package com.exp.exp8;

import java.util.Random;

public class Protocol {
    public String protocolWorking(String clientSide) {
        String serverSide = null; //服务器的选择
        String result = null; //单局比赛结果
        String answer = null; //返回结果，结果形式：服务器选择 # 输赢和
                                //平局，例如：Stone#ServerWin
        //用随机数0、1、2模拟服务器的选择，0 表示石头，1 表示剪刀，2 表示布
        Random random = new Random();
        int serverChoice = random.nextInt(3);
        switch (serverChoice) {
            case 0:
                serverSide = "Stone";
                if (clientSide.equalsIgnoreCase("Stone")) {
                    result = "TwoDraw";//平局
                } else if (clientSide.equalsIgnoreCase("Scissors")) {
                    result = "ServerWin";//服务器赢
                } else {
                    result = "ClientWin";//玩家赢
                }
                break;
            case 1:
                serverSide = "Scissors";
                if (clientSide.equalsIgnoreCase("Stone")) {
                    result = "ClientWin";
                } else if (clientSide.equalsIgnoreCase("Scissors")) {
                    result = "TwoDraw";//服务器赢
                } else {
                    result = "ServerWin";//玩家赢
                }
                break;
            case 2:
                serverSide = "Paper";
                if (clientSide.equalsIgnoreCase("Stone")) {
                    result = "ServerWin";
                } else if (clientSide.equalsIgnoreCase("Scissors")) {
                    result = "ClientWin";//服务器赢
                } else {
                    result = "TwoDraw";//玩家赢
                }
                break;
        }
        answer = serverSide + " # " + result;
        return answer;
    }
}
