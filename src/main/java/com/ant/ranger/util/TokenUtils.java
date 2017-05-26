package com.ant.ranger.util;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @packgeName: com.ant.ranger.util
 * @ClassName: TokenUtils
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<Token工具类>
 * @author: hexinlei
 * @date: 2017/5/17-下午3:14
 * @version: 1.0
 * @since: JDK 1.8
 */
public class TokenUtils {

    public static final String MAGIC_KEY = "obfuscate";

    /**
     * 创建Token
     * @param userDetails
     * @return
     */
    public static String createToken(UserDetails userDetails)
    {
      /* Expires in one hour */
        long expires = System.currentTimeMillis() + 1000L * 60 * 60;

        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append(userDetails.getUsername());
        tokenBuilder.append(":");
        tokenBuilder.append(expires);
        tokenBuilder.append(":");
        tokenBuilder.append(TokenUtils.computeSignature(userDetails, expires));

        return tokenBuilder.toString();
    }

    public static String computeSignature(UserDetails userDetails, long expires)
    {
        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(userDetails.getUsername());
        signatureBuilder.append(":");
        signatureBuilder.append(expires);
        signatureBuilder.append(":");
        signatureBuilder.append(userDetails.getPassword());
        signatureBuilder.append(":");
        signatureBuilder.append(TokenUtils.MAGIC_KEY);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
    }

    /**
     * 获取用户名
     * @param authToken
     * @return
     */
    public static String getUserNameFromToken(String authToken)
    {
        if (null == authToken) {
            return null;
        }

        String[] parts = authToken.split(":");
        return parts[0];
    }

    /**
     * 验证Token
     * @param authToken
     * @param userDetails
     * @return
     */
    public static boolean validateToken(String authToken, UserDetails userDetails)
    {
        String[] parts = authToken.split(":");
        long expires = Long.parseLong(parts[1]);
        String signature = parts[2];

        if (expires < System.currentTimeMillis()) {
            return false;
        }

        return signature.equals(TokenUtils.computeSignature(userDetails, expires));
    }
}
