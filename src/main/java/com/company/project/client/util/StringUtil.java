package com.company.project.client.util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rock on 3/22/17.
 */
public class StringUtil {
    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    /**
     * unicode 转字符串
     */
    public static String unicode2String(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    /**
     * ncr 转字符串 reference http://blog.csdn.net/u010720985/article/details/60960432
     *
     * @param str
     * @return
     */
    public static String ncr2String(String str) {
        if (str == null) return null;

        StringBuffer sb = new StringBuffer();
        int i1 = 0;
        int i2 = 0;


        while (i2 < str.length()) {
            i1 = str.indexOf("&#", i2);
            if (i1 == -1) {
                sb.append(str.substring(i2));
                break;
            }
            sb.append(str.substring(i2, i1));
            i2 = str.indexOf(";", i1);
            if (i2 == -1) {
                sb.append(str.substring(i1));
                break;
            }


            String tok = str.substring(i1 + 2, i2);
            try {
                int radix = 10;
                if (tok.charAt(0) == 'x' || tok.charAt(0) == 'X') {
                    radix = 16;
                    tok = tok.substring(1);
                }
                sb.append((char) Integer.parseInt(tok, radix));
            } catch (NumberFormatException exp) {
                sb.append(exp.getMessage());
            }
            i2++;
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("/home/rock/uumai/upwork/taobao/distirbutedcode/taobao/uumai-taobao/test.txt"), StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line);
        }
        String fromFile = sb.toString();
//        System.out.println(fromFile);


//        String content = StringUtil.unicode2String(fromFile);
//        System.out.println(content);
    }
}
