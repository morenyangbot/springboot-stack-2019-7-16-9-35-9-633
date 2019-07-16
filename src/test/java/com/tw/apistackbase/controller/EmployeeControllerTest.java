package com.tw.apistackbase.controller;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    public void pushEmployeeIntoRepository() throws Exception {
        this.mockMvc.perform(post("/employees")
                .content("{\n" +
                        "    \"name\": \"Teng\",\n" +
                        "    \"age\": 22,\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"salary\": 100\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void should_return_new_employee_when_post_a_new_employee() throws Exception {
        this.mockMvc.perform(
                post("/employees").content("{\n" +
                        "    \"name\": \"Jerry1\",\n" +
                        "    \"age\": 23,\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"salary\": 100\n" +
                        "}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\n" +
                        "    \"name\": \"Jerry1\",\n" +
                        "    \"age\": 23,\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"salary\": 100\n" +
                        "}"));
    }

    @Test
    public void should_return_new_employee_when_fetch_all_employees() throws Exception {
        pushEmployeeIntoRepository();
        this.mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[{\n" +
                        "    \"name\": \"Teng\",\n" +
                        "    \"age\": 22,\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"salary\": 100\n" +
                        "}]"));
    }

}