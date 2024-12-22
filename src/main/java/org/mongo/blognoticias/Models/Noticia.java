package org.mongo.blognoticias.Models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class Noticia {
    private String titulo;
    private String cuerpo;
    private String fecha;
    private ArrayList<String> tags;

    public Noticia(String titulo, String cuerpo, String fecha, ArrayList<String> tags) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
        this.tags = tags;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public MongoCollection<Document> obtenerColeccionNoticias() {
        MongoDatabase database = ConexionBD.conexion();
        MongoCollection<Document> coleccionNoticias = database.getCollection("noticias");
        return coleccionNoticias;
    }
}
