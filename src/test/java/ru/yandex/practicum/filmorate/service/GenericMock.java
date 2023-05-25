package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.model.Generic;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GenericMock<T extends Generic> {
    @Autowired
    protected MockMvc mockMvc;
    private static ObjectMapper objectMapper;
    private String responseBody;

    protected T getEntity(String url) throws Exception {
        T entity = null;
        responseBody = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        return (T) objectMapper.readValue(responseBody, entity.getClass());
    }

    protected T postEntity(T entity, String url) throws Exception {
        responseBody = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entity)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        return (T) objectMapper.readValue(responseBody, entity.getClass());
    }

    protected T putEntity(T entity, String url) throws Exception {
        responseBody = mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entity)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        return (T) objectMapper.readValue(responseBody, entity.getClass());
    }

    protected T deleteEntity(T entity, String url) throws Exception {
        responseBody = mockMvc.perform(delete(url).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entity)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        return (T) objectMapper.readValue(responseBody, entity.getClass());
    }
}
