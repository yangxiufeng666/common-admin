package com.common.system.util;

import java.util.regex.Pattern;

/**
 * Created by Mr.Yangxiufeng on 2017/8/30.
 * Time:15:35
 * ProjectName:Common-admin
 */
public class CheckPhone {

    /**
     * 中国电信号码格式验证 手机段： 133,153,180,181,189,177,1700,173
     * **/
    private static final String CHINA_TELECOM_PATTERN = "(?:^(?:\\+86)?1(?:33|53|7[37]|8[019])\\d{8}$)|(?:^(?:\\+86)?1700\\d{7}$)";
    /**
     * 中国联通号码格式验证 手机段：130,131,132,155,156,185,186,145,176,1707,1708,1709,175
     * **/
    private static final String CHINA_UNICOM_PATTERN = "(?:^(?:\\+86)?1(?:3[0-2]|4[5]|5[56]|7[56]|8[56])\\d{8}$)|(?:^(?:\\+86)?170[7-9]\\d{7}$)";
    /**
     * 中国移动号码格式验证
     * 手机段：134,135,136,137,138,139,150,151,152,157,158,159,182,183,184
     * ,187,188,147,178,1705
     *
     **/
    private static final String CHINA_MOBILE_PATTERN = "(?:^(?:\\+86)?1(?:3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(?:^(?:\\+86)?1705\\d{7}$)";

    /**
     * 仅手机号格式校验
     */
    private static final String PHONE_PATTERN = new StringBuilder(300)
            .append(CHINA_MOBILE_PATTERN).append("|")
            .append(CHINA_TELECOM_PATTERN).append("|")
            .append(CHINA_UNICOM_PATTERN).toString();
    /**
     * 转义字符串中的[]-^\元字符
     *
     * @param input
     * @return
     */
    private static String escapeMetacharacterOfStr(String input) {
        String regex = "[-^\\[\\]\\\\]";
        return input.replaceAll(regex, "\\\\$0");
    }

    /**
     * 仅手机号码校验
     *
     * @param input
     * @return
     */
    public static boolean isPhone(String input) {
        return match(PHONE_PATTERN, input);
    }
    /**
     * 匹配函数
     *
     * @param regex
     * @param input
     * @return
     */
    private static boolean match(String regex, String input) {
        return Pattern.matches(regex, input);
    }
    /**
     * 验证【电信】手机号码的格式
     * @param str
     *            校验手机字符串
     * @return 返回true,否则为false
     */
    public static boolean isChinaTelecomPhoneNum(String str) {
        return match(CHINA_TELECOM_PATTERN, str);
    }

    /**
     * 验证【联通】手机号码的格式
     * @param str
     *            校验手机字符串
     * @return 返回true,否则为false
     */
    public static boolean isChinaUnicomPhoneNum(String str) {
        return match(CHINA_UNICOM_PATTERN, str);
    }

    /**
     * 验证【移动】手机号码的格式
     *
     * @param str
     *            校验手机字符串
     * @return 返回true,否则为false
     */
    public static boolean isChinaMobilePhoneNum(String str) {
        return match(CHINA_MOBILE_PATTERN, str);
    }

    /**
     * <p>验证是否是三大运营商的手机号</p>
     * @param phone
     * @return
     */
    public static boolean isCorrectPhone(String phone){
        return CheckPhone.isChinaMobilePhoneNum(phone)|| CheckPhone.isChinaTelecomPhoneNum(phone)||CheckPhone.isChinaUnicomPhoneNum(phone);
    }
}
