package com.zeyiyouhuo.utils;

import com.zeyiyouhuo.constants.Constants;
import com.zeyiyouhuo.exception.SecretException;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

public class ZeyiUtils {

    private static final String AES_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final byte[] IV_BYTES = "0102030405060708".getBytes();
    private static final String MD5_ALGORITHM = "MD5";


    /**
     * 给TOP请求签名。
     *
     * @param params     所有字符型的TOP请求参数
     * @param body       请求主体内容
     * @param secret     签名密钥
     * @param signMethod 签名方法，目前支持：空（老md5)、md5, hmac_md5三种
     * @return 签名
     */
    public static String signRequest(Map<String, String> params, String body, String secret, String signMethod)
            throws IOException {
        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
            query.append(secret);
        }
        for (String key : keys) {
            String value = params.get(key);
            if (StringUtils.allNotEmpty(key, value)) {
                query.append(key).append(value);
            }
        }

        // 第三步：把请求主体拼接在参数后面
        if (body != null) {
            query.append(body);
        }

        // 第四步：使用MD5/HMAC加密
        byte[] bytes;
        if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
            bytes = encryptHMAC(query.toString(), secret);
        } else if (Constants.SIGN_METHOD_HMAC_SHA256.equals(signMethod)) {
            bytes = encryptHMACSHA256(query.toString(), secret);
        } else {
            query.append(secret);
            bytes = encryptMD5(query.toString());
        }

        // 第五步：把二进制转化为大写的十六进制
        return byte2hex(bytes);
    }

    /**
     * AES 加密操作
     *
     * @param source 待加密内容
     * @param secret 加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String aesEncrypt(String source, String secret) throws SecretException {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), AES_ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(IV_BYTES);
            byte[] byteContent = source.getBytes(Constants.CHARSET_UTF8);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] result = cipher.doFinal(byteContent);
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception ex) {
            throw new SecretException("AES加密失败!", ex);
        }
    }

    /**
     * AES 解密操作
     *
     * @param source 待解密的字符串
     * @param secret 密钥
     * @return
     */
    public static String aesDecrypt(String source, String secret) throws SecretException {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), AES_ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(IV_BYTES);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(source));
            return new String(result, Constants.CHARSET_UTF8);
        } catch (Exception ex) {
            throw new SecretException("AES解密失败!", ex);
        }
    }

    /**
     * 对字符串采用UTF-8编码后，用MD5进行摘要
     */
    public static byte[] encryptMD5(String source) throws IOException {
        return encryptMD5(source.getBytes(Constants.CHARSET_UTF8));
    }

    /**
     * 对字节流进行MD5摘要
     */
    public static byte[] encryptMD5(byte[] source) throws IOException {
        byte[] bytes;
        try {
            MessageDigest md = MessageDigest.getInstance(MD5_ALGORITHM);
            bytes = md.digest(source);
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    /**
     * 把字节流转换为十六进制表示方式。
     */
    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    private static byte[] encryptHMACSHA256(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacSHA256");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    private static byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }
}
