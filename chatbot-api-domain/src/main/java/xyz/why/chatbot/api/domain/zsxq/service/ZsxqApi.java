package xyz.why.chatbot.api.domain.zsxq.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xyz.why.chatbot.api.domain.zsxq.model.IZsxqApi;
import xyz.why.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import xyz.why.chatbot.api.domain.zsxq.model.req.AnswerReq;
import xyz.why.chatbot.api.domain.zsxq.model.req.ReqData;
import xyz.why.chatbot.api.domain.zsxq.model.resp.AnswerRes;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Title: ZsxqApi
 * @Author WHY
 * @Date: 2023/3/19 18:21
 * @Version 1.0
 */
@Service
public class ZsxqApi implements IZsxqApi {
    private static final Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public UnAnsweredQuestionsAggregates queryUnAnswerdQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/"
                + groupId
                + "/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie", cookie);
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 查询结果转换字符串
            HttpEntity entity = response.getEntity();
            String jsonStr = EntityUtils.toString(entity);

            String res = null;
            Pattern pattern = Pattern.compile("[1-9]{15}");
            Matcher matcher = pattern.matcher(jsonStr);
            if (matcher.find()) {
                String topic_id = matcher.group();
                res = jsonStr.replaceAll(topic_id, "\"" + topic_id + "\"");
            }

//            String jsonStr = JSON.toJSONString(response.getEntity());
            logger.info("星球获取的问题为：\n groupId: {}, \njsonStr: {}", groupId, res);
            return JSON.parseObject(jsonStr, UnAnsweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("queryUnAnswerQuestionsTopicId Err Code is {}" + response.getStatusLine().getStatusCode());
        }

    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        // 创建Http请求客户端
        CloseableHttpClient client = HttpClientBuilder.create().build();

        // POST请求
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/answer");
        // 设置请求标头
        post.addHeader("cookie", cookie);
        post.addHeader("Content-Type", "application/json;charset=utf8");
        post.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");

        // 回复请求封装
        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
        // JSON字符串转换
        String paramJson = JSONObject.toJSONString(answerReq);
        // 封装请求体
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        // 执行POST请求
        CloseableHttpResponse response = client.execute(post);
        // 检验请求是否成功
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 响应体转JSON字符串
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答问题结果。groupId：{} topicId：{} jsonStr：{}", groupId, topicId, jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            // 返回请求结果
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("answer Err Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
