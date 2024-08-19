package com.zillionwon.spider.util;

import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具
 *
 * @author InwardFlow
 */
public class ParserUtils {
    /**
     * 保留文本中的正数
     *
     * @param input 字符串
     * @return Double
     */
    public static Double keepDigit(String input) {
        if (input == null) {
            return 0.0;
        }
        // 修正正则表达式，匹配整数或小数
        Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?|¥\\d+(?:\\.\\d+)?|\\d+[,.]\\d+");
        Matcher matcher = pattern.matcher(input);

        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            // 将匹配到的数字添加到字符串构建器中
            // 需要去除可能的货币符号
            String numberStr = matcher.group().replace("¥", "");
            sb.append(numberStr);
        }

        // 尝试将构建器中的字符串转换为Double
        try {
            return !sb.isEmpty() ? Double.parseDouble(sb.toString()) : 0;
        } catch (NumberFormatException e) {
            // 如果转换失败，返回null或处理异常
            return null;
        }
    }

    /**
     * 从地址栏元素中提取地址
     *
     * @param elements 元素集合，例如 "_3CIR0seeyY8j14XCMZj1jz" 对应
     * @return String[地址, 距离]
     */
    // TODO: 这里采用了硬编码, 0 号元素为地址， 1 号为距离
    public static String[] splitAddress(Elements elements) {
        String[] result = new String[]{"", ""};
        if (StringUtil.isBlank(elements.text())) {
            return result;
        }
        for (Element e : elements) {
            if (StringUtil.isBlank(e.text()) || e.text().length() < 2) {
                continue;
            }
            if (e.text().contains("m")) {
                result[1] = e.text();
            } else {
                result[0] = e.text();
            }
        }
        return result;
    }
}
