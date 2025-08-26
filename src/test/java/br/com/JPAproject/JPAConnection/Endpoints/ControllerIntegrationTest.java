package br.com.JPAproject.JPAConnection.Endpoints;

import br.com.JPAproject.JPAConnection.entity.Users;
import br.com.JPAproject.JPAConnection.repository.RepositoryUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
@ActiveProfiles("test")
public class ControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RepositoryUser repositoryUser;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
        repositoryUser.deleteAll();
    }

    @Test
    void testCreateUser() throws Exception {
        Users user = new Users();
        user.setNome("Test User");
        user.setPassword(123456L);

        mockMvc.perform(post("/api/users/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Test User"))
                .andExpect(jsonPath("$.password").value(123456));
    }

    @Test
    void testGetAllUsers_EmptyList() throws Exception {
        mockMvc.perform(get("/api/users/getAll"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAllUsers_WithData() throws Exception {
        Users user = new Users();
        user.setNome("Test User");
        user.setPassword(123456L);
        repositoryUser.save(user);

        mockMvc.perform(get("/api/users/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Test User"))
                .andExpect(jsonPath("$[0].password").value(123456));
    }

    @Test
    void testGetUserById_Found() throws Exception {
        Users user = new Users();
        user.setNome("Test User");
        user.setPassword(123456L);
        Users savedUser = repositoryUser.save(user);

        mockMvc.perform(get("/api/users/getById/{id}", savedUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Test User"))
                .andExpect(jsonPath("$.password").value(123456));
    }

    @Test
    void testGetUserById_NotFound() throws Exception {
        mockMvc.perform(get("/api/users/getById/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateUser_Success() throws Exception {
        Users user = new Users();
        user.setNome("Original Name");
        user.setPassword(123456L);
        Users savedUser = repositoryUser.save(user);

        savedUser.setNome("Updated Name");
        savedUser.setPassword(654321L);

        mockMvc.perform(put("/api/users/updateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Updated Name"))
                .andExpect(jsonPath("$.password").value(654321));
    }

    @Test
    void testUpdateUser_NotFound() throws Exception {
        Users user = new Users();
        user.setId(999L);
        user.setNome("Non-existent User");
        user.setPassword(123456L);

        mockMvc.perform(put("/api/users/updateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteUser_Success() throws Exception {
        Users user = new Users();
        user.setNome("User to Delete");
        user.setPassword(123456L);
        Users savedUser = repositoryUser.save(user);

        mockMvc.perform(delete("/api/users/deleteUserById/{id}", savedUser.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteUser_NotFound() throws Exception {
        mockMvc.perform(delete("/api/users/deleteUserById/{id}", 999L))
                .andExpect(status().isNotFound());
    }
}