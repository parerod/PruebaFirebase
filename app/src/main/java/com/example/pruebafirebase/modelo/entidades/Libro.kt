package com.example.pruebafirebase.modelo.entidades

data class Libro(var titulo: String, var autor: String) {

    var idLibroFB:String=""

    // Constructor sin argumentos necesario para Firebase Firestore
    constructor() : this("", "") {
        // Puedes dejarlo vacío o initializer campos predeterminados si es necesario
    }
}