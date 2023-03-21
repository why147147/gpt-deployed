package xyz.why.chatbot.api.domain.ai.model.aggregates;

import xyz.why.chatbot.api.domain.ai.model.vo.Choices;
import xyz.why.chatbot.api.domain.ai.model.vo.Usage;

import java.util.List;

/**
 * @Description: AI回答结果
 * @Title: AIAnswer
 * @Author WHY
 * @Date: 2023/3/21 15:52
 * @Version 1.0
 */

public class AIAnswer {

    private String id;
    private String object;
    private long created;
    private String model;
    private Usage usage;
    private List<Choices> choices;

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setObject(String object) {
        this.object = object;
    }
    public String getObject() {
        return object;
    }

    public void setCreated(long created) {
        this.created = created;
    }
    public long getCreated() {
        return created;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }
    public Usage getUsage() {
        return usage;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }
    public List<Choices> getChoices() {
        return choices;
    }

}

