package com.selimsahin.userservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selimsahin.userservice.dto.response.RestResponse;
import com.selimsahin.userservice.producer.KafkaProducer;
import com.selimsahin.userservice.util.AppLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = UserServiceApplication.class)
@ExtendWith(MockitoExtension.class)
class UserServiceApplicationTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private AppLogger appLogger;

    @Mock
    private KafkaProducer kafkaProducer;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_whenUsersExist_shouldReturnListOfUsers() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();

        RestResponse<?> restResponse = objectMapper.readValue(content, RestResponse.class);

        boolean isSuccess = restResponse.isSuccess();
        assertTrue(isSuccess);
    }

//    @Test
//    void getUserById_whenIdIsValid_shouldReturnUser() throws Exception {
//
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        MockHttpServletResponse response = mvcResult.getResponse();
//        String content = response.getContentAsString();
//
//        RestResponse<?> restResponse = objectMapper.readValue(content, RestResponse.class);
//
//        boolean isSuccess = restResponse.isSuccess();
//        assertTrue(isSuccess);
//    }

    @Test
    void createUser_whenDataIsValid_shouldCreateAndReturnUser() throws Exception {

        String requestJson = "{\n" +
                "  \"name\": \"name\",\n" +
                "  \"surname\": \"surname\",\n" +
                "  \"email\": \"mail@test.com\",\n" +
                "  \"location\": {\n" +
                "    \"latitude\": 38.38634955600047,\n" +
                "    \"longitude\": 26.791997783482447\n" +
                "  },\n" +
                "  \"status\": \"ACTIVE\"\n" +
                "}";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                                                                    .content(requestJson)
                                                                    .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();

        RestResponse<?> restResponse = objectMapper.readValue(content, RestResponse.class);

        boolean isSuccess = restResponse.isSuccess();
        assertTrue(isSuccess);
    }

    @Test
    void getAllUserReviews_whenUserReviewsExist_shouldReturnListOfUserReviews() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user-reviews"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();

        RestResponse<?> restResponse = objectMapper.readValue(content, RestResponse.class);

        boolean isSuccess = restResponse.isSuccess();
        assertTrue(isSuccess);
    }

    @Test
    void getUserReviewById_whenIdNotFound_shouldThrowUserReviewNotFoundException() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user-reviews/1000"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();

        RestResponse<?> restResponse = objectMapper.readValue(content, RestResponse.class);

        boolean isSuccess = restResponse.isSuccess();
        assertFalse(isSuccess);
    }
}
