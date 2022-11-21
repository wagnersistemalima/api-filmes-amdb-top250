package br.com.sistemalima.filmes.service;

import br.com.sistemalima.filmes.builders.Top250DataBuilder;
import br.com.sistemalima.filmes.exceptions.BadRequestExceptions;
import br.com.sistemalima.filmes.http.imdb.ImdbFeingClient;
import br.com.sistemalima.filmes.http.imdb.dto.Top250Data;
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

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class FilmeServiceTest {

    @InjectMocks
    private final FilmeService filmeService = new FilmeService();

    @Mock
    private ImdbFeingClient imdbFeingClient;

    private static final String version = "v1";

    private static final String correlationId = "correlationId";
    private static final String resourceName = "resourceName";
    private static final String erroMesssage = "apiKey invalida";


    private static final Observabilidade observabilidade = new Observabilidade(
            version,
            resourceName,
            correlationId
    );

    @Test
    @DisplayName("deve listar filmes top 250 com sucesso e retornar erro message vazio")
    public void deveListarFilmesTop250() throws IOException {

        // Dado

        Top250Data top250Data = new Top250DataBuilder().random();

        Mockito.when(imdbFeingClient.buscar250TopFilmes(Mockito.any())).thenReturn(top250Data);

        // Quando / Então
        Assertions.assertDoesNotThrow( () -> filmeService.listarFilmesTop250(observabilidade));
        assertNull(top250Data.getErrorMessage());
        verify(imdbFeingClient, Mockito.times(1)).buscar250TopFilmes(Mockito.any());

    }

    @Test
    @DisplayName("deve lancar exception BadRquestException quando receber a message error do client apiKey invalida")
    public void deveLancarBadRequestException() throws IOException {

        // Dado
        Top250Data top250Data = new Top250Data();
        top250Data.setErrorMessage(erroMesssage);

        Mockito.when(imdbFeingClient.buscar250TopFilmes(Mockito.any())).thenReturn(top250Data);

        // Quando / Então
        Assertions.assertThrows(BadRequestExceptions.class, () ->
                    filmeService.listarFilmesTop250(observabilidade)

        );
        verify(imdbFeingClient, Mockito.times(1)).buscar250TopFilmes(Mockito.any());
        Assertions.assertNotNull(top250Data.getErrorMessage());

    }

    @Test
    @DisplayName("deve falhar ao listar filmes top 250 retornando exception IoExceptions")
    public void deveFalharQuandoListarFilmesTop250() throws IOException {

        // Dado
        Mockito.when(imdbFeingClient.buscar250TopFilmes(Mockito.any())).thenThrow(FeignException.FeignClientException.class);

        // Quando / Então
        Assertions.assertThrows(IOException.class, () ->
                filmeService.listarFilmesTop250(observabilidade)
        );

        verify(imdbFeingClient, Mockito.times(1)).buscar250TopFilmes(Mockito.any());
    }
}