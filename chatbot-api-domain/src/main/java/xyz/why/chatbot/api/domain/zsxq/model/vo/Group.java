package xyz.why.chatbot.api.domain.zsxq.model.vo;

/**
 * @Description: 知识星球响应组
 * @Title: Group
 * @Author WHY
 * @Date: 2023/3/21 16:32
 * @Version 1.0
 */
public class Group {

    private String group_id;

    private String name;

    private String type;

    public void setGroup_id(String group_id){
        this.group_id = group_id;
    }
    public String getGroup_id(){
        return this.group_id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }

}
