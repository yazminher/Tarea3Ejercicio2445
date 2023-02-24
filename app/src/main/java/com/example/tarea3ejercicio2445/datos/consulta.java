package com.example.tarea3ejercicio2445.datos;
public class consulta {
    public static final String persona="personas";
    public static final String id="id";
    public static final String nombre="nombre";
    public static final String apellido="apellido";
    public static final String edad="edad";
    public static final String correo= "correo";
    public static final String direccion="direccion";
    public static final String DataBase="lista";
    public static final String CrearTablaUsuario="CREATE TABLE "+persona+" "+
            "("+
            id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            nombre+" TEXT,"+
            apellido+" TEXT,"+
            edad+" TEXT,"+
            correo+" TEXT,"+
            direccion+" TEXT"+
            ")";
    public static final String DropTableUsuario="DROP TABLE IF EXISTS "+persona;
}