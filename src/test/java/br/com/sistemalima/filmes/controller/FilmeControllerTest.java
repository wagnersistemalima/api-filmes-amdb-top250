package br.com.sistemalima.filmes.controller;

import br.com.sistemalima.filmes.builders.Top250DataBuilder;
import br.com.sistemalima.filmes.constant.ApiConstantVersion;
import br.com.sistemalima.filmes.dto.FilmeDTO;
import br.com.sistemalima.filmes.exceptions.BadRequestExceptions;
import br.com.sistemalima.filmes.http.imdb.dto.Top250Data;
import br.com.sistemalima.filmes.mapper.ObservabilidadeMapper;
import br.com.sistemalima.filmes.model.Observabilidade;
import br.com.sistemalima.filmes.service.FilmeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

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

    private static final String apiKey = "apiKey";
    private static final String version = ApiConstantVersion.versionApiOne;
    private static final String resourceName = "resourceName";
    private static final String correlationId = "correlationId";
    private final Observabilidade observabilidade = new Observabilidade(
            version, resourceName, correlationId
    );

    @Test
    @DisplayName("deve retornar 200 ok com sucesso e a lista de filmes top250")
    public void deveRetornar200() throws Exception {

        // Dado
        Top250Data top250Data = new Top250DataBuilder().random();
        List<FilmeDTO> listFilmeDTO = top250Data.getItems().stream().map(FilmeDTO::new).toList();

        Mockito.when(observabilidadeMapper.map(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(observabilidade);
        Mockito.when(filmeService.listarFilmesTop250(Mockito.any())).thenReturn(listFilmeDTO);

        // Quando / Então

        mockMvc.perform(MockMvcRequestBuilders.get("/filmes/top250")
                        .header("Accept-Version", "v1")
                        .header("Api-Key", "apiKey")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json(toJson(listFilmeDTO)));

        Assertions.assertNull(top250Data.getErrorMessage());

    }

    @Test
    @DisplayName("deve retornar 400 BadRequest quando header version nao for validado")
    public void deveRetornar400() throws Exception {

        // Dado
        Mockito.when(observabilidadeMapper.map(Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(new BadRequestExceptions("Cabeçalho da requisição version não validado!"));

        // Quando / Então
        mockMvc.perform(MockMvcRequestBuilders.get("/filmes/top250")
                        .header("Accept-Version", "v2")
                        .header("Api-Key", "apiKey")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

    private String toJson(List<FilmeDTO> listFilmeDTO) throws JsonProcessingException {
        return objectMapper.writeValueAsString(listFilmeDTO);
    }

}