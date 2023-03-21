package xyz.why.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * @Description: chatgpt openai接口
 * @Title: OpenAI
 * @Author WHY
 * @Date: 2023/3/21 15:47
 * @Version 1.0
 */

public interface IOpenAI {
    String doChatGPT(String question) throws IOException;
}
