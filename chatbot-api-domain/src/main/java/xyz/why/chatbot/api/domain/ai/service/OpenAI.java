package xyz.why.chatbot.api.domain.ai.service;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.why.chatbot.api.domain.ai.IOpenAI;
import xyz.why.chatbot.api.domain.ai.model.aggregates.AIAnswer;
import xyz.why.chatbot.api.domain.ai.model.vo.Choices;

import java.io.IOException;

/**
 * @Description:
 * @Title: IOpenAI
 * @Author WHY
 * @Date: 2023/3/21 15:49
 * @Version 1.0
 */
@Service
public class OpenAI implements IOpenAI {

    private final static Logger logger = LoggerFactory.getLogger(OpenAI.class);

    @Value("${chatbot-api.openAIKey}")
    private String apiKey;

    @Override
    public String doChatGPT(String question) throws IOException {

        StringBuilder ans = new StringBuilder();
        // 创建客户端
        CloseableHttpClient client = HttpClientBuilder.create().build();
        // 设置POST请求
        HttpPost post = new HttpPost("https://openai.proxy.mailjob.net/v1/chat/completions");

        // 添加请求头
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + apiKey);

        // 设置请求体
        String paramJson = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"" + question + "\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "}";

        // 实体化JSON数据
        StringEntity entity = new StringEntity(paramJson, ContentType.create("ans/json"));
        post.setEntity(entity);

        CloseableHttpResponse response = client.execute(post);

        // 检测请求结果
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            // 封装响应信息
            AIAnswer aiAnswer = JSON.parseObject(res, AIAnswer.class);

//            正则过滤返回内容
//            Pattern pattern = Pattern.compile("(?<=\"content\":\").*(?=\"})");
//            Matcher matcher = pattern.matcher(res);
//            if (matcher.find()) {
//                String group = matcher.group();
//                logger.info("\n收到的返回信息为{}\n", group.replaceAll("\\\\n", ""));
//            }
            for (Choices choice : aiAnswer.getChoices()) {
                // 获取返回文本
                ans.append(choice.getMessage().getContent());
            }
            return ans.toString();
        } else {
            throw new RuntimeException("ERR Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
