package br.com.fiap.hackathon.adapters.in.controller;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue; // Adicione esta importação
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CadastroController.class)
public class CadastroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void testShowNewUsersForm() throws Exception {
        mockMvc.perform(get("/cadastro"))
                .andExpect(status().isOk()) 
                .andExpect(view().name("cadastro"))
                .andExpect(model().attributeExists("usuario")) 
                .andExpect(model().attribute("usuario", hasProperty("id", is(nullValue())))) 
                .andExpect(model().attribute("usuario", hasProperty("nome", is(nullValue())))) 
                .andExpect(model().attribute("usuario", hasProperty("cpf", is(nullValue()))))
                .andExpect(model().attribute("usuario", hasProperty("email", is(nullValue()))))
                .andExpect(model().attribute("usuario", hasProperty("senha", is(nullValue()))));
    }
}
