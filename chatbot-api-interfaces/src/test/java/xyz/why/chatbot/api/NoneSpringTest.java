package xyz.why.chatbot.api;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 非Spring框架测试
 * @Title: NoneSpringTest
 * @Author WHY
 * @Date: 2023/3/20 16:51
 * @Version 1.0
 */
public class NoneSpringTest {

//    @Test
    public void test() {
        String content = "{\"id\":\"chatcmpl-6w5mdO1Pg6EAIMU2thohJ4g6EdMTX\",\"object\":\"chat.completion\",\"created\":1679303275,\"model\":\"gpt-3.5-turbo-0301\",\"usage\":{\"prompt_tokens\":10,\"completion_tokens\":10,\"total_tokens\":20},\"choices\":[{\"message\":{\"role\":\"assistant\",\"content\":\"\\n\\nHello! How can I assist you today?\"},\"finish_reason\":\"stop\",\"index\":0}]}";
        Pattern pattern = Pattern.compile("(?<=\"content\":\").*(?=\\?\"})");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            String group = matcher.group();
            System.out.println(group);
            System.out.println(group.substring(4));
        }
    }
}
