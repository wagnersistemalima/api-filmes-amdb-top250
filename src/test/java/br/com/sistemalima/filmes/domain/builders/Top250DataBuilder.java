package br.com.sistemalima.filmes.domain.builders;

import br.com.sistemalima.filmes.interfaces.outcoming.http.imdb.dto.Top250Data;
import br.com.sistemalima.filmes.interfaces.outcoming.http.imdb.dto.Top250DataDetail;

public class Top250DataBuilder {

    public Top250DataBuilder() {

    }

    public Top250Data random() {
        Top250DataDetail top250DataDetail1 = new Top250DataDetailBuilder().random1();
        Top250DataDetail top250DataDetail2 = new Top250DataDetailBuilder().random2();
        Top250DataDetail top250DataDetail3 = new Top250DataDetailBuilder().random3();
        Top250Data top250Data = new Top250Data();
        top250Data.getItems().add(top250DataDetail1);
        top250Data.getItems().add(top250DataDetail2);
        top250Data.getItems().add(top250DataDetail3);
        return top250Data;

    }
}
