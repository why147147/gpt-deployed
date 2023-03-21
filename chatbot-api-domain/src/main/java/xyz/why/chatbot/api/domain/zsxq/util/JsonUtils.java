package xyz.why.chatbot.api.domain.zsxq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Title: JsonUtils
 * @Author WHY
 * @Date: 2023/3/20 9:11
 * @Version 1.0
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static String intToString(String regex, String content) {
        String res = null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            res = matcher.group();
        }
        if (res != null) {
            return content.replaceFirst(res, "\"" + res + "\"");
        }
        return content;
    }
}
