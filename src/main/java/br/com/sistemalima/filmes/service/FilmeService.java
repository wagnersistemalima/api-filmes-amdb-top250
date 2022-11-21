package br.com.sistemalima.filmes.service;

import br.com.sistemalima.filmes.dto.FilmeDTO;
import br.com.sistemalima.filmes.exceptions.BadRequestExceptions;
import br.com.sistemalima.filmes.http.imdb.ImdbFeingClient;
import br.com.sistemalima.filmes.http.imdb.dto.Top250Data;
import br.com.sistemalima.filmes.model.Observabilidade;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private ImdbFeingClient imdbFeingClient;

    private final Logger logger = LoggerFactory.getLogger(FilmeService.class);
    private final static String tag = "class: FilmeService, ";

    public List<FilmeDTO> listarFilmesTop250(String apiKey, Observabilidade observabilidade) throws IOException {

        logger.info(String.format(tag + observabilidade));

        try {
            Top250Data top250Data = imdbFeingClient.buscar250TopFilmes(apiKey);
            if (top250Data.getErrorMessage() == null || top250Data.getErrorMessage().equals("")) {
                return top250Data.getItems().stream().map(FilmeDTO::new).toList();
            }
            logger.error(String.format("Error, " + tag + top250Data.getErrorMessage()));
            throw new BadRequestExceptions(top250Data.getErrorMessage());


        } catch (FeignException ex) {
            logger.error(String.format("Error, " + tag + ex.getMessage()));
            throw new IOException(ex.getMessage());
        }
    }
}
