package br.com.sistemalima.filmes.domain.entity;

import java.time.LocalDateTime;

public class Observabilidade {

    private String version;

    private String resourceName;

    private LocalDateTime date = LocalDateTime.now();

    private String correlationId;

    public Observabilidade() {
    }

    public Observabilidade(String version, String resourceName, String correlationId) {
        this.version = version;
        this.resourceName = resourceName;
        this.correlationId = correlationId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public String toString() {
        return "Observabilidade{" +
                "version='" + version + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", date=" + date +
                ", correlationId='" + correlationId + '\'' +
                '}';
    }
}
