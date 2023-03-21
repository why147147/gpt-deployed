package xyz.why.chatbot.api;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Title: ApiTest
 * @Author WHY
 * @Date: 2023/3/18 8:47
 * @Version 1.0
 */
public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/88885882424842/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie", "zsxq_access_token=46F8868C-77ED-D3B0-1A35-A09F5986BC3A_FB373ADF8E4A8587; __cuid=f95c11fdf1554165adb2fea9c5097c3f; amp_fef1e8=368e89b4-4618-499f-8eb0-d166ec1dd8f0R...1grp483h3.1grp483h6.1.1.2; abtest_env=product; zsxqsessionid=631b4a0a06573372e37d9a51c5985dbb");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer_question() throws IOException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 创建Http请求客户端
        CloseableHttpClient httpClient = httpClientBuilder.build();

        // 创建get请求
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/584188884412514/answer");
        // 设置请求标头
        post.addHeader("cookie", "zsxq_access_token=46F8868C-77ED-D3B0-1A35-A09F5986BC3A_FB373ADF8E4A8587; __cuid=f95c11fdf1554165adb2fea9c5097c3f; amp_fef1e8=368e89b4-4618-499f-8eb0-d166ec1dd8f0R...1grp483h3.1grp483h6.1.1.2; abtest_env=product; zsxqsessionid=d52aee0b45397585ae1a9a4ff665b8f0");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        // 回复接口的请求信息
        String paramJson = "{\"req_data\"" +
                ":{\"text\":\"笑死了\\n\"," +
                "\"image_ids\":[]," +
                "\"silenced\":true}}";
        StringEntity entity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));

        // 设置请求体内容
        post.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println("异常状态码: " + response.getStatusLine().getStatusCode());
        }
    }
    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://openai.proxy.mailjob.net/v1/chat/completions");

        // 添加请求头
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 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");

        String text = "Please give me a love letter";
        String paramJson = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \""+text+"\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "}";

//        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"" + "你好" + "\", \"temperature\": 0, \"max_tokens\": 1024}";
        StringEntity entity = new StringEntity(paramJson, ContentType.create("text/json"));
        post.setEntity(entity);

        CloseableHttpResponse response = client.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
            Pattern pattern = Pattern.compile("(?<=\"content\":\").*(?=\"})");
            Matcher matcher = pattern.matcher(res);
            if (matcher.find()) {
                String group = matcher.group();
                System.out.println(group.replaceAll("\\\\n", ""));
            }
        } else {
            System.out.println("异常状态码: " + response.getStatusLine().getStatusCode());
        }
    }
}