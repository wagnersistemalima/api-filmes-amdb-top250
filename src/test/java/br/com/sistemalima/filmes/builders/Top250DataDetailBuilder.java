package br.com.sistemalima.filmes.builders;

import br.com.sistemalima.filmes.http.imdb.dto.Top250DataDetail;

import java.util.Random;
import java.util.UUID;

public class Top250DataDetailBuilder {

    private final Random random = new Random();

    public Top250DataDetailBuilder() {

    }

    public Top250DataDetail random() {

        return new Top250DataDetail(
                UUID.randomUUID().toString(),
                "rank",
                "title",
                "fullTitle",
                "year",
                "image",
                "crew",
                "imDbRating",
                "imDbRatingCount"
                );
    }
}
