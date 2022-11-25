package br.com.sistemalima.filmes.domain.mapper;

import br.com.sistemalima.filmes.domain.dto.FilmeResponseDTO;
import br.com.sistemalima.filmes.interfaces.outcoming.http.imdb.dto.Top250DataDetail;
import org.springframework.stereotype.Component;

@Component
public class FilmeDTOMapper {

    public FilmeResponseDTO map(Top250DataDetail top250DataDetail) {
        return new FilmeResponseDTO(
                top250DataDetail.getTitle(),
                top250DataDetail.getImage(),
                top250DataDetail.getImDbRating(),
                top250DataDetail.getYear()
        );
    }
}
