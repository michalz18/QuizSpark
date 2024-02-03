package com.example.quizspark.admin.controller;

import com.example.quizspark.security.configuration.JwtService;
import com.example.quizspark.security.configuration.SecurityConfiguration;
import com.example.quizspark.security.user.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.quizspark.security.authentication.service.AuthenticationService;
import com.example.quizspark.admin.dto.UserDTO;

import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AdminController.class,
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = SecurityConfiguration.class))
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    @WithMockUser(authorities="ADMIN")
    public void getAllUsers_WhenAdmin_ShouldReturnListOfUsers() throws Exception {
        UserDTO userDTO = new UserDTO(UUID.randomUUID(), "Jan", "Kowalski", "jan@example.com", Role.ADMIN);
        given(authenticationService.getAllUsers()).willReturn(Collections.singletonList(userDTO));

        mockMvc.perform(get("/api/admin/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstname", is(userDTO.getFirstname())))
                .andExpect(jsonPath("$[0].lastname", is(userDTO.getLastname())))
                .andExpect(jsonPath("$[0].email", is(userDTO.getEmail())))
                .andExpect(jsonPath("$[0].role", is("ADMIN")));
    }

    @Test
    @WithMockUser(authorities="USER")
    public void getAllUsers_WhenUserRole_ShouldReturnForbidden() throws Exception {
        mockMvc.perform(get("/api/admin/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
