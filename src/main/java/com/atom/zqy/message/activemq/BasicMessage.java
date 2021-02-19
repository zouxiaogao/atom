package com.atom.zqy.message.activemq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zouqingyuan
 * @version V1.0
 * @Description
 * @Date 2021/02/03
 */
@Data
public class BasicMessage implements Serializable {

    /**
     * 时间
     */
    Date time;

    /**
     * 消息名称
     */
    String message;


    /**
     * 消息内容
     */
    Object value;

    /**
     * 备注
     */
    String remark;


    public BasicMessage(Date time, String message, Object value, String remark) {
        this.time = time;
        this.message = message;
        this.value = value;
        this.remark = remark;
    }

    public BasicMessage() {
    }

    public static String buildMqString(Date time, String message, Object value, String remark){
        return JSON.toJSONString(new BasicMessage(time,message,value,remark));
    }

    public static BasicMessage stringToBean(String message){
        return JSONObject.parseObject(message, BasicMessage.class);
    }

    private static final long serialVersionUID = 1L;
}
