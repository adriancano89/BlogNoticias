package org.mongo.blognoticias.Models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Comentario {
    private String fecha;
    private String cuerpo;

    public Comentario(String fecha, String cuerpo) {
        this.fecha = fecha;
        this.cuerpo = cuerpo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public MongoCollection<Document> obtenerColeccionComentarios() {
        MongoDatabase database = ConexionBD.conexion();
        MongoCollection<Document> coleccionComentarios = database.getCollection("comentarios");
        return coleccionComentarios;
    }
}
