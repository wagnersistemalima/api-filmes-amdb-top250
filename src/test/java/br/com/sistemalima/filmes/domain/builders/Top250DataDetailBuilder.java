package br.com.sistemalima.filmes.domain.builders;

import br.com.sistemalima.filmes.interfaces.outcoming.http.imdb.dto.Top250DataDetail;

import java.util.Random;
import java.util.UUID;

public class Top250DataDetailBuilder {

    private final Random random = new Random();

    public Top250DataDetailBuilder() {

    }

    public Top250DataDetail random1() {

        return new Top250DataDetail(
                UUID.randomUUID().toString(),
                "rank",
                "O senhor dos aneis",
                "fullTitle",
                "year",
                "image",
                "crew",
                "imDbRating",
                "imDbRatingCount"
                );
    }

    public Top250DataDetail random2() {

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

    public Top250DataDetail random3() {

        return new Top250DataDetail(
                UUID.randomUUID().toString(),
                "rank",
                "title3",
                "fullTitle",
                "year",
                "image",
                "crew",
                "imDbRating",
                "imDbRatingCount"
        );
    }
}
