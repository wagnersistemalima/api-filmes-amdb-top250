package br.com.sistemalima.filmes.domain.dto;

import br.com.sistemalima.filmes.interfaces.outcoming.http.imdb.dto.Top250DataDetail;

public class FilmeResponseDTO {

    private String title;

    private String urlImage;

    private String rating;

    private String year;

    public FilmeResponseDTO() {
    }

    public FilmeResponseDTO(String title, String urlImage, String rating, String year) {
        this.title = title;
        this.urlImage = urlImage;
        this.rating = rating;
        this.year = year;
    }

    public FilmeResponseDTO(Top250DataDetail top250DataDetail) {
        this.title = top250DataDetail.getTitle();
        this.urlImage = top250DataDetail.getImage();
        this.rating = top250DataDetail.getImDbRating();
        this.year = top250DataDetail.getYear();

    }

    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getRating() {
        return rating;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "FilmeDTO{" +
                "title='" + title + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", rating='" + rating + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
