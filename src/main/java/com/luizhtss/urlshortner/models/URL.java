package com.luizhtss.urlshortner.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(URL.class)
public class URL implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Id
    private String encurtador;
    private String url;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEncurtador() {
        return encurtador;
    }

    public void setEncurtador(String encurtador) {
        this.encurtador = encurtador;
    }
}
