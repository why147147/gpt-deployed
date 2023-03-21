package xyz.why.chatbot.api.domain.ai.model.vo;

/**
 * @Description: OpenAI返回内容
 * @Title: Choices
 * @Author WHY
 * @Date: 2023/3/21 16:34
 * @Version 1.0
 */
public class Choices {

    private Message message;
    private String finish_reason;
    private int index;
    public void setMessage(Message message) {
         this.message = message;
     }
     public Message getMessage() {
         return message;
     }

    public void setFinish_reason(String finish_reason) {
         this.finish_reason = finish_reason;
     }
     public String getFinish_reason() {
         return finish_reason;
     }

    public void setIndex(int index) {
         this.index = index;
     }
     public int getIndex() {
         return index;
     }

}