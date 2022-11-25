package br.com.sistemalima.filmes.domain.service;

import br.com.sistemalima.filmes.domain.dto.FilmeResponseDTO;
import br.com.sistemalima.filmes.domain.exceptions.BadRequestExceptions;
import br.com.sistemalima.filmes.domain.exceptions.EntityNotFoundException;
import br.com.sistemalima.filmes.interfaces.outcoming.http.imdb.ImdbFeingClient;
import br.com.sistemalima.filmes.interfaces.outcoming.http.imdb.dto.Top250Data;
import br.com.sistemalima.filmes.interfaces.outcoming.http.imdb.dto.Top250DataDetail;
import br.com.sistemalima.filmes.domain.entity.Observabilidade;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private ImdbFeingClient imdbFeingClient;

    @Value("${servers.imdb-filmes.apikey}")
    String apikey;

    private final Logger logger = LoggerFactory.getLogger(FilmeService.class);
    private final static String tag = "class: FilmeService, ";
    private final static String erroMessageNotFound = "Recurso não encontrado, ";

    public List<FilmeResponseDTO> listarFilmesTop250(Observabilidade observabilidade, String title) throws IOException {

        logger.info(String.format(tag + observabilidade));

        try {
            if (title == null) {
                Top250Data top250Data = imdbFeingClient.buscar250TopFilmes(apikey);
                if (top250Data.getErrorMessage() == null || top250Data.getErrorMessage().equals("")) {

                    return top250Data.getItems().stream().map(FilmeResponseDTO::new).toList();
                }
                logger.error(String.format("Error, " + tag + top250Data.getErrorMessage()));
                throw new BadRequestExceptions(top250Data.getErrorMessage());
            } else {

                Top250Data top250Data = imdbFeingClient.buscar250TopFilmes(apikey);
                if (top250Data.getErrorMessage() == null || top250Data.getErrorMessage().equals("")) {

                    Top250Data listaComFiltroTitle = extrairFilmeDaLista(top250Data.getItems(), title, observabilidade);

                    return listaComFiltroTitle.getItems().stream().map(FilmeResponseDTO::new).toList();
                }
                logger.error(String.format("Error, " + tag + top250Data.getErrorMessage()));
                throw new BadRequestExceptions(top250Data.getErrorMessage());
            }

        } catch (FeignException ex) {
            logger.error(String.format("Error, " + tag + ex.getMessage()));
            throw new IOException(ex.getMessage());
        }

    }

    public Top250Data extrairFilmeDaLista(List<Top250DataDetail> list, String title, Observabilidade observabilidade) {
        logger.info(String.format(tag + "method: extrairFilmeDaLista, " + title + ", " + observabilidade));
        Top250Data listaComFiltroTitle = new Top250Data();
        Top250DataDetail top250DataDetail = new Top250DataDetail();
        for (Top250DataDetail filme: list) {
            if(filme.getTitle().equals(title)) {
                top250DataDetail.setId(filme.getId());
                top250DataDetail.setRank(filme.getRank());
                top250DataDetail.setTitle(filme.getTitle());
                top250DataDetail.setFullTitle(filme.getFullTitle());
                top250DataDetail.setYear(filme.getYear());
                top250DataDetail.setImage(filme.getImage());
                top250DataDetail.setCrew(filme.getCrew());
                top250DataDetail.setImDbRating(filme.getImDbRating());
                top250DataDetail.setImDbRatingCount(filme.getImDbRatingCount());
                break;
            } else {
                logger.error(String.format("Error, " + tag + "method: extrairFilmeDaLista, " + erroMessageNotFound + title + ", " + observabilidade));
                throw new EntityNotFoundException(erroMessageNotFound + title);
            }
        }
        listaComFiltroTitle.getItems().add(top250DataDetail);
        return listaComFiltroTitle;
    }
}
