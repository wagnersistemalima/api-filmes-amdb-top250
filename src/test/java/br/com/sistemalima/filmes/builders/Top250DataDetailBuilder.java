package br.com.sistemalima.filmes.builders;

import br.com.sistemalima.filmes.http.imdb.dto.Top250DataDetail;

import java.util.Random;

public class Top250DataDetailBuilder {

    private final Random random = new Random();

    public Top250DataDetailBuilder() {

    }

    public Top250DataDetail random() {

        return new Top250DataDetail(
                random.toString(),
                random.toString(),
                random.toString(),
                random.toString(),
                random.toString(),
                random.toString(),
                random.toString(),
                random.toString(),
                random.toString()
        );
    }
}
