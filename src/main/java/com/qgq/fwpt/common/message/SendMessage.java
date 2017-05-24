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
    public static void sendPerformanceMessage(String json ,String phone) {
        JsonNode jsonNodeCode = Json.string2JsonNode(json).get("code");
        String code = Json.jsonNode2Object(jsonNodeCode,String.class);
        if(!Objects.equals("1",code)) {
            JsonNode jsonNodeMessage = Json.string2JsonNode(json).get("message");
            send(Json.jsonNode2Object(jsonNodeMessage,String.class),phone);
            return;
        }
        JsonNode jsonNodeData = Json.string2JsonNode(json).get("data");
        List<Performance> data = Json.jsonNode2Object(jsonNodeData, new TypeReference<List<Performance>>() {
        });
        send(data,phone);
    }

    private static void send (String message,String phone) {
        System.out.print(message+"发送成功"+phone);
    }
    private static void send (List<Performance> performances, String phone) {

        System.out.print(performances+"发送成功"+phone);
    }
}
