package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.model.Generic;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Component("GenericMock")
public class GenericMock<T extends Generic> {
    @Autowired
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;
    private String responseBody;

    public GenericMock() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public T getEntity(String url, Class<T> classType) throws Exception {
        responseBody = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        return objectMapper.readValue(responseBody, classType);
    }

    public T getEntity(String url, int httpResponseCode, Class<T> classType) throws Exception {
        responseBody = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(httpResponseCode))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        return objectMapper.readValue(responseBody, classType);
    }

    public String listEntityJson(String url) throws Exception {
        return mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    public T postEntity(String url, T entity, Class<T> classType) throws Exception {
        responseBody = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entity)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        return objectMapper.readValue(responseBody, classType);
    }

    public void putEntity(String url) throws Exception {
        mockMvc.perform(put(url));
    }

    public T putEntity(String url, T entity, Class<T> classType) throws Exception {
        responseBody = mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entity)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        return objectMapper.readValue(responseBody, classType);
    }

    public void deleteEntity(String url) throws Exception {
        mockMvc.perform(delete(url));
    }
}
