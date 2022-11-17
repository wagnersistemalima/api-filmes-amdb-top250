package br.com.sistemalima.filmes.http.imdb.dto;

import java.util.ArrayList;
import java.util.List;

public class Top250Data {

    private List<Top250DataDetail> items = new ArrayList<>();

    private String errorMessage;

    public Top250Data() {
    }

    public List<Top250DataDetail> getItems() {
        return items;
    }

    public void setItems(List<Top250DataDetail> items) {
        this.items = items;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Top250Data{" +
                "items=" + items +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
