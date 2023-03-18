package api;

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

/**
 * @Description:
 * @Title: ApiTest
 * @Author WHY
 * @Date: 2023/3/18 8:47
 * @Version 1.0
 */
public class ApiTest {

    @Test
    public void query_unanswer_questions() throws IOException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 创建Http请求客户端
        CloseableHttpClient httpClient = httpClientBuilder.build();

        // 创建get请求
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/88885882424842/topics?scope=unanswered_questions&count=20");
        // 设置请求标头
        get.addHeader("cookie", "zsxq_access_token=46F8868C-77ED-D3B0-1A35-A09F5986BC3A_FB373ADF8E4A8587; abtest_env=product; zsxqsessionid=ae226b71eaa055539e89358082592acc");
        get.addHeader("Content-Type", "application/json;charset=utf8");
        get.addHeader("origin", "https://wx.zsxq.com");
        CloseableHttpResponse response = httpClient.execute(get);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String entity = EntityUtils.toString(response.getEntity());
            System.out.println(entity);
        } else {
            System.out.println("异常状态码: " + response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer_question() throws IOException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 创建Http请求客户端
        CloseableHttpClient httpClient = httpClientBuilder.build();

        // 创建get请求
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/412811244144428/answer");
        // 设置请求标头
        post.addHeader("cookie", "zsxq_access_token=46F8868C-77ED-D3B0-1A35-A09F5986BC3A_FB373ADF8E4A8587; abtest_env=product; zsxqsessionid=ae226b71eaa055539e89358082592acc");
        post.addHeader("Content-Type", "application/json;charset=utf8");
        // 创建返回信息
        String paramJson = "{\"req_data\":{\"text\":\"笑死了\\n\",\"image_ids\":[],\"silenced\":true}}";
        StringEntity entity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));

        // 设置请求体内容
        post.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(entity);
        } else {
            System.out.println("异常状态码: " + response.getStatusLine().getStatusCode());
        }
    }
}
