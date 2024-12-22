package org.mongo.blognoticias.Models;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombreUsuario;
    private String cuentaTwitter;
    private String nombre;
    private String descripcion;
    private ArrayList<String> telefonos;
    private Direccion direccion;

    public Usuario(String nombreUsuario, String cuentaTwitter, String nombre, String descripcion, ArrayList<String> telefonos, Direccion direccion) {
        this.nombreUsuario = nombreUsuario;
        this.cuentaTwitter = cuentaTwitter;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.telefonos = telefonos;
        this.direccion = direccion;
    }

    public Usuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Usuario() {

    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCuentaTwitter() {
        return cuentaTwitter;
    }

    public void setCuentaTwitter(String cuentaTwitter) {
        this.cuentaTwitter = cuentaTwitter;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<String> telefonos) {
        this.telefonos = telefonos;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public MongoCollection<Document> obtenerColeccionUsuarios() {
        MongoDatabase database= ConexionBD.conexion();
        MongoCollection<Document> coleccionUsuarios = database.getCollection("usuarios");
        return coleccionUsuarios;
    }

    public void registrarUsuario(Usuario usuario) {
        String calle = usuario.getDireccion().getCalle();
        String numero = usuario.getDireccion().getNumero();
        String ciudad = usuario.getDireccion().getCiudad();
        String codigoPostal = usuario.getDireccion().getCp();
        Document direccion = new Document("calle", calle).append("numero", numero).append("ciudad", ciudad).append("codigo_postal", codigoPostal);
        Document documentoInsertar = new Document("usuario", usuario.getNombreUsuario()).append("cuenta_twitter", usuario.getCuentaTwitter()).append("nombre", usuario.getNombre()).append("descripcion", usuario.getDescripcion()).append("telefonos", usuario.getTelefonos()).append("direccion", direccion);
        System.out.println(documentoInsertar);
        obtenerColeccionUsuarios().insertOne(documentoInsertar);
        ConexionBD.cerrarConexion();
    }

    public Usuario verificarUsuario(Usuario usuario) {
        Document consultarUsuario = new Document("usuario", usuario.getNombreUsuario());
        FindIterable<Document> documentos = obtenerColeccionUsuarios().find(consultarUsuario);
        Usuario usuarioValidado = null;
        for (Document documento : documentos) {
            String nombreUsuario = documento.getString("usuario");
            String cuentaTwitter = documento.getString("cuenta_twitter");
            String nombre = documento.getString("nombre");
            String descripcion = documento.getString("descripcion");
            List<String> arrayTelefonos = documento.getList("telefonos", String.class);
            ArrayList<String> telefonos = new ArrayList<>(arrayTelefonos);

            Document datosDireccion = (Document) documento.get("direccion");
            String calle = datosDireccion.getString("calle");
            String numero = datosDireccion.getString("numero");
            String ciudad = datosDireccion.getString("ciudad");
            String codigoPostal = datosDireccion.getString("codigo_postal");
            Direccion direccion = new Direccion(calle, numero, ciudad, codigoPostal);

            usuarioValidado = new Usuario(nombreUsuario, cuentaTwitter, nombre, descripcion, telefonos, direccion);
        }
        ConexionBD.cerrarConexion();
        return usuarioValidado;
    }
}
