package br.com.sistemalima.filmes.service;

import br.com.sistemalima.filmes.builders.Top250DataDetailBuilder;
import br.com.sistemalima.filmes.http.imdb.ImdbFeingClient;
import br.com.sistemalima.filmes.http.imdb.dto.Top250Data;
import br.com.sistemalima.filmes.http.imdb.dto.Top250DataDetail;
import br.com.sistemalima.filmes.model.Observabilidade;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class FilmeServiceTest {

    @InjectMocks
    private FilmeService filmeService;

    @Mock
    private ImdbFeingClient imdbFeingClient;

    private static final String version = "v1";
    private static final String apiKey = "apiKey";
    private static final String correlationId = "correlationId";
    private static final String resourceName = "resourceName";
    private static final String erroMesssage = "apiKey invalida";

    private static final Observabilidade observabilidade = new Observabilidade(
            version,
            apiKey,
            resourceName,
            correlationId
    );

    @Test
    @DisplayName("deve listar filmes top 250 com sucesso e retornar erro message vazio")
    public void deveListarFilmesTop250() throws IOException {

        // Dado
        Top250DataDetail top250DataDetail1 = new Top250DataDetailBuilder().random();
        Top250DataDetail top250DataDetail2 = new Top250DataDetailBuilder().random();
        Top250DataDetail top250DataDetail3 = new Top250DataDetailBuilder().random();
        Top250Data top250Data = new Top250Data();
        top250Data.getItems().add(top250DataDetail1);
        top250Data.getItems().add(top250DataDetail2);
        top250Data.getItems().add(top250DataDetail3);

        Mockito.when(imdbFeingClient.buscar250TopFilmes(apiKey)).thenReturn(top250Data);

        // Quando / Então
        Top250Data response = filmeService.listarFilmesTop250(apiKey, observabilidade);

        assertNull(response.getErrorMessage());
        assertEquals(3, response.getItems().size());

        verify(imdbFeingClient, Mockito.times(1)).buscar250TopFilmes(apiKey);

    }

    @Test
    @DisplayName("deve listar filmes top 250 retornando a lista de filmes vazia com erro message apiKey invalida")
    public void deveListarFilmesTop250ErroMessage() throws IOException {

        // Dado
        Top250Data top250Data = new Top250Data();
        top250Data.setErrorMessage(erroMesssage);

        Mockito.when(imdbFeingClient.buscar250TopFilmes(apiKey)).thenReturn(top250Data);

        // Quando / Então

        Top250Data response = filmeService.listarFilmesTop250(apiKey, observabilidade);

        assertEquals(erroMesssage, response.getErrorMessage());
        verify(imdbFeingClient, Mockito.times(1)).buscar250TopFilmes(apiKey);

    }

    @Test
    @DisplayName("deve falhar ao listar filmes top 250 retornando exception IoExceptions")
    public void deveFalharQuandoListarFilmesTop250() throws IOException {

        // Dado
        Mockito.when(imdbFeingClient.buscar250TopFilmes(apiKey)).thenThrow(FeignException.FeignClientException.class);

        // Quando / Então
        Assertions.assertThrows(IOException.class, () ->
                filmeService.listarFilmesTop250(apiKey, observabilidade)
        );
        verify(imdbFeingClient, Mockito.times(1)).buscar250TopFilmes(apiKey);
    }

}