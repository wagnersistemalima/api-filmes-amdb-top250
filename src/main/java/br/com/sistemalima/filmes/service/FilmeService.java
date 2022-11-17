package br.com.sistemalima.filmes.service;

import br.com.sistemalima.filmes.http.imdb.ImdbFeingClient;
import br.com.sistemalima.filmes.http.imdb.dto.Top250Data;
import br.com.sistemalima.filmes.model.Observabilidade;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FilmeService {

    @Autowired
    private ImdbFeingClient imdbFeingClient;

    private final Logger logger = LoggerFactory.getLogger(FilmeService.class);
    private final static String tag = "class: FilmeService, ";

    public Top250Data listarFilmesTop250(String apiKey, Observabilidade observabilidade) throws IOException {

        logger.info(String.format(tag + observabilidade));

        try {
            return imdbFeingClient.buscar250TopFilmes(apiKey);
        } catch (FeignException ex) {
            logger.info(String.format("Error, " + tag + ex.getMessage()));
            throw new IOException(ex.getMessage());
        }
    }
}
