package com.example.nueva;

public class Item {

    String titulo;

    String link;

    String enclosure;

    String description;

    String pubDate;

    public Item() {
    }

    public Item(String titulo, String link, String enclosure, String description, String pubDate) {
        this.titulo = titulo;
        this.link = link;
        this.enclosure = enclosure;
        this.description = description;
        this.pubDate = pubDate;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
