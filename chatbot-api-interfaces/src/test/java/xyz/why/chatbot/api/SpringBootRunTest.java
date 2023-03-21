package xyz.why.chatbot.api;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.why.chatbot.api.domain.ai.IOpenAI;
import xyz.why.chatbot.api.domain.zsxq.model.IZsxqApi;
import xyz.why.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import xyz.why.chatbot.api.domain.zsxq.model.resp.RespData;
import xyz.why.chatbot.api.domain.zsxq.model.vo.Topics;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Title: SpringBootRunTest
 * @Author WHY
 * @Date: 2023/3/19 21:48
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private final Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates res = zsxqApi.queryUnAnswerdQuestionsTopicId(groupId, cookie);
        logger.info("测试结果:{}", JSON.toJSONString(res));

        RespData respData = res.getRespData();
        List<Topics> topics = respData.getTopics();
        if (!topics.isEmpty()) {
            for (Topics topic: respData.getTopics()) {
                // 问题编号
                String topic_id = topic.getTopic_id();
                // 问题内容
                String text = topic.getQuestion().getText();

                logger.info("问题id：{}， 问题内容：{}", topic_id, text);

                boolean answer = zsxqApi.answer(groupId, cookie, topic_id, text, false);
                if (answer) {
                    logger.info("输出成功");
                }
            }
        }
    }

    @Test
    public void test_openai() throws IOException {
        String resp = openAI.doChatGPT("can i ask u a question?");
        logger.info("OpenAI测试结果: {}", resp);
    }
}
