package xyz.why.chatbot.api.domain.zsxq.model.vo;

import xyz.why.chatbot.api.domain.zsxq.model.resp.RespData;

public class Root
{
    private boolean succeeded;

    private RespData resp_data;

    public void setSucceeded(boolean succeeded){
        this.succeeded = succeeded;
    }
    public boolean getSucceeded(){
        return this.succeeded;
    }
    public void setResp_data(RespData resp_data){
        this.resp_data = resp_data;
    }
    public RespData getResp_data(){
        return this.resp_data;
    }
}