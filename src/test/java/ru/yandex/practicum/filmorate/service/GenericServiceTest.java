package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.inmemory.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.inmemory.InMemoryUserStorage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class GenericServiceTest {
    protected ApplicationContext applicationContext;
    @Autowired
    protected MockMvc mockMvc;
    protected DefaultSingletonBeanRegistry registry;
    protected static ObjectMapper objectMapper;
    protected InMemoryUserStorage inMemoryUserStorage;
    protected InMemoryFilmStorage inMemoryFilmStorage;
    protected User user1;
    protected User user2;
    protected User user3;
    protected Film film1;
    protected Film film2;
    protected String responseBody;

    @BeforeAll
    static void init() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public GenericServiceTest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        registry = (DefaultSingletonBeanRegistry) applicationContext.getAutowireCapableBeanFactory();
    }

    protected User getUserFromMock(User user) throws Exception {
        responseBody = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk()).
                andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(responseBody, User.class);
    }

    protected Film getFilmFromMock(Film film) throws Exception {
        responseBody = mockMvc.perform(post("/films").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(film)))
                .andExpect(status().isOk()).
                andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(responseBody, Film.class);
    }
}
