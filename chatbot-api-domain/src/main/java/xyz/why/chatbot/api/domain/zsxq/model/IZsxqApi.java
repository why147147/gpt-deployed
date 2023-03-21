package xyz.why.chatbot.api.domain.zsxq.model;

import xyz.why.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/**
 * @Description: 知识星球Api接口
 * @Title: ZsxqApi
 * @Author WHY
 * @Date: 2023/3/19 18:16
 * @Version 1.0
 */

public interface IZsxqApi {

    UnAnsweredQuestionsAggregates queryUnAnswerdQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;
}
