package com.common.system.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import java.util.Random;

/**
 * Created by Mr.Yangxiufeng on 2017/6/20.
 * Time:14:49
 * ProjectName:Common-admin
 */
public class ShiroKit {
    private static final String NAMES_DELIMETER = ",";

    /**
     * 加盐参数
     */
    public final static String hashAlgorithmName = "MD5";

    /**
     * 循环次数
     */
    public final static int hashIterations = 1024;
    /**
     * <p>shiro密码加密工具类</p>
     * @param credentials 密码
     * @param saltSource 密码盐
     * @return
     */
    public static String md5(String credentials, String saltSource) {
        ByteSource salt = new Md5Hash(saltSource);
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
    }
    /**
     * 获取随机盐值
     * @param length
     * @return
     */
    public static String getRandomSalt(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    /**
     * 获取当前 Subject
     * @return Subject
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

//    public static void main(String[] args) {
//        /**
//         * 6lqyq
//         bd29ec972547217cbaec292f7e56a300
//         */
//        String salt = ShiroKit.getRandomSalt(5);
//        String password = ShiroKit.md5("123456",salt);
//        System.out.println(salt);
//        System.out.println(password);
//    }
}
