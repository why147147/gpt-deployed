package xyz.why.chatbot.api.domain.zsxq.model.aggregates;

import xyz.why.chatbot.api.domain.zsxq.model.resp.RespData;

/**
 * @Description: 未回答问题的聚合信息
 * @Title: UnAnsweredQuestionsAggregates
 * @Author WHY
 * @Date: 2023/3/19 18:13
 * @Version 1.0
 */
public class UnAnsweredQuestionsAggregates {
    private boolean succeeded;
    private RespData respData;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public RespData getRespData() {
        return respData;
    }

    public void setRespData(RespData respData) {
        this.respData = respData;
    }
}
