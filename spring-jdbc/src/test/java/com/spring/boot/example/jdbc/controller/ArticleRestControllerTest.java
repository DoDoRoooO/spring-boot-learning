package com.spring.boot.example.jdbc.controller;

import com.spring.boot.example.jdbc.service.jdbc.ArticleJDBCService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Slf4j
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest()
public class ArticleRestControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Resource
    private ArticleJDBCService articleJDBCService;

//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController()).build();
//    }

    @Test
    public void saveArticle() throws Exception {
        String article = "{\"id\":1,\"author\":\"surun\",\"title\":\"Mockito 使用\",\"createTime\":\"2019-10-09 21:27:55\"}";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/rest/article")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(article))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.author").value("surun"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title").value("Mockito 使用"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info(mvcResult.getResponse().getContentAsString());

    }

}
