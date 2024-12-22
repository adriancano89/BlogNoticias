package org.mongo.blognoticias.Models;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionBD {
    private static final String host = "mongodb://localhost:27017";
    private static MongoClient mongoClient;

    public ConexionBD() {

    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static void setMongoClient(MongoClient mongoClient) {
        ConexionBD.mongoClient = mongoClient;
    }

    public static MongoDatabase conexion() {
        MongoDatabase database = null;
        try {
            setMongoClient(MongoClients.create(host));
            database = getMongoClient().getDatabase("blog_noticias");
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        return database;
    }

    public static void cerrarConexion() {
        ConexionBD.getMongoClient().close();
    }
}
