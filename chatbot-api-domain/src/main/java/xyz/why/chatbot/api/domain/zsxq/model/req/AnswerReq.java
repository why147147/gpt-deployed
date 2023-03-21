package xyz.why.chatbot.api.domain.zsxq.model.req;

/**
 * @Description: 请求问答接口信息
 * @Title: AnswerReq
 * @Author WHY
 * @Date: 2023/3/19 18:37
 * @Version 1.0
 */
public class AnswerReq {

    private ReqData req_data;

    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }

    public ReqData getReq_data() {
        return req_data;
    }

    public void setReq_data(ReqData req_data) {
        this.req_data = req_data;
    }

}
