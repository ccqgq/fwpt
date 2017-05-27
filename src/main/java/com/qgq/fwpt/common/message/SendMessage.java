package com.qgq.fwpt.common.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.qgq.fwpt.common.utils.Json;
import com.qgq.fwpt.openaccount.dto.Performance;

import java.util.List;
import java.util.Objects;

/**
 * Created on 2017/05/24
 *
 * @author 繁华
 */
public class SendMessage {
    public static String sendPerformanceMessage(String json) {

        JsonNode jsonNodeCode = Json.string2JsonNode(json).get("code");
        String code = Json.jsonNode2Object(jsonNodeCode,String.class);
        if(!Objects.equals("1",code)) {
            JsonNode jsonNodeMessage = Json.string2JsonNode(json).get("message");
            return Json.jsonNode2Object(jsonNodeMessage,String.class);

        }
        JsonNode jsonNodeData = Json.string2JsonNode(json).get("data");
        List<Performance> data = Json.jsonNode2Object(jsonNodeData, new TypeReference<List<Performance>>() {
        });
        return dataFmart(data);
    }

    private static String dataFmart(List<Performance> data) {
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<data.size();i++){
            if (i==0) {
                builder.append("姓名:"+data.get(i).getUserName()+"   学号:"+data.get(i).getNumber()+"     ");
                builder.append(data.get(i).getCourseNmae()+":"+data.get(i).getCount()+"     ");
            }else {
                builder.append(data.get(i).getCourseNmae()+":"+data.get(i).getCount()+"     ");
            }
        }
        return builder.toString();
    }

}
