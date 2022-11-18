package br.com.sistemalima.filmes.controller;

import br.com.sistemalima.filmes.mapper.ObservabilidadeMapper;
import br.com.sistemalima.filmes.model.Observabilidade;
import br.com.sistemalima.filmes.service.FilmeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FilmeControllerTest {

    @MockBean
    private FilmeService filmeService;

    @MockBean
    private ObservabilidadeMapper observabilidadeMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("deve retornar 200 ok com sucesso e a lista de filmes top250")
    public void deveRetornar200() {

        // Dado

        // Quando

        // Então

    }

}