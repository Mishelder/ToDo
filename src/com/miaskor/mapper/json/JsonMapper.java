package com.miaskor.mapper.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.dto.RegistrationClientDto;
import com.miaskor.entity.Client;
import com.miaskor.entity.Task;
import lombok.SneakyThrows;

public class JsonMapper {
    private final ObjectMapper jsonMapper = new ObjectMapper();


    public RegistrationClientDto readValue(String json){
        try {
            return jsonMapper.readValue(json, RegistrationClientDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String writeValue(RegistrationClientDto client){
        try {
            return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(client);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
