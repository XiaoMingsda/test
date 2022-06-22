/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu.security;

import java.security.MessageDigest;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Administrator
 */
public class Cryptography {
    /**
     * 消息摘要算法，实现明文加密功能
     *  plainTest：待加密的明文
     *  hashType：算法类型
     *  return 密文的十六进制字符串
     */
    public static String getHash(String plainText, String hashType) {
        try {
            MessageDigest md = MessageDigest.getInstance(hashType);//算法
            byte[] encryptStr = md.digest(plainText.getBytes("UTF-8"));//摘要
            return DatatypeConverter.printHexBinary(encryptStr);//十六进制字符串
        } catch (Exception e) {
            return null;
        }
    }
    
    
    /**
     * 用AES对称加密算法，生成一个新的密钥
     *  return 生成的密钥
     */
    public static SecretKey generateNewKey() {
        try {
            //密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();//新密钥
            return secretKey;
        } catch (Exception e) {
            return null;
        }
    }
}