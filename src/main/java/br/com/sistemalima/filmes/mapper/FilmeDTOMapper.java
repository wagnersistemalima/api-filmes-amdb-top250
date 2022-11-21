package br.com.sistemalima.filmes.mapper;

import br.com.sistemalima.filmes.dto.FilmeDTO;
import br.com.sistemalima.filmes.http.imdb.dto.Top250DataDetail;
import org.springframework.stereotype.Component;

@Component
public class FilmeDTOMapper {

    public FilmeDTO map(Top250DataDetail top250DataDetail) {
        return new FilmeDTO(
                top250DataDetail.getTitle(),
                top250DataDetail.getImage(),
                top250DataDetail.getImDbRating(),
                top250DataDetail.getYear()
        );
    }
}
