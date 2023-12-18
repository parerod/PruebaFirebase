package com.example.pruebafirebase.modelo.daos

import android.util.Log
import com.example.gestiondietapp2023.conexiones.BDFireBase
import com.example.pruebafirebase.modelo.entidades.Libro
import com.example.pruebafirebase.modelo.interfaces.InterfaceDaoLibro
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class DaoLibroFireBase : InterfaceDaoLibro {

    lateinit var conexion: FirebaseFirestore

    override fun addLibro(li: Libro) {
        conexion.collection("libros")
            .add(li)
            .addOnSuccessListener { documentReference ->
                li.idLibroFB = documentReference.id

                documentReference.update("idLibroFB",li.idLibroFB)
                    .addOnSuccessListener { documentReference ->
                        Log.d("TAG", "DocumentSnapshot successfully updated!")
                    }
                    .addOnFailureListener { e -> Log.w("TAG", "Error updating document", e) }

                Log.d("firebase", "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.d("firebase", "Error adding document", e)
            }
    }

    override fun getLibro(titulo: String): Libro {
        var librillo : Libro = Libro()
        conexion.collection("libros")
            .whereEqualTo("titulo", titulo)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    librillo = document.toObject(Libro::class.java)
                    Log.d("GetOne",librillo.titulo!!)
                    Log.d("TAG", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }
        return librillo
    }

    override fun getLibrosAutor(autor: String): MutableList<Libro> {
        var libros:MutableList<Libro> = mutableListOf()

        conexion.collection("libros")
            .whereEqualTo("autor", autor)
            .get()
            .addOnSuccessListener { querySnapshot ->

                for (document in querySnapshot) {
                    val librillo = document.toObject(Libro::class.java)
                    libros.add(librillo)
                }
                libros.forEach {
                    Log.d("firebase",it.idLibroFB+"--"+it.titulo)
                }
            }
            .addOnFailureListener { exception ->
                // Maneja el error
            }
        return libros
    }

    override fun getLibros(): MutableList<Libro> {
        /*var libros : MutableList<Libro> = mutableListOf()
        conexion.collection("libros")
            .get()
            .addOnSuccessListener { querySnapshot ->

                for (document in querySnapshot) {
                    val librillo = document.toObject(Libro::class.java)
                    libros.add(librillo)
                }
                libros.forEach {
                    Log.d("firebase",it.idLibroFB+"--"+it.titulo)
                }
            }
            .addOnFailureListener { exception ->
                // Maneja el error
            }
        return libros*/

        /*var  libros:MutableList<Libro> = mutableListOf()
        conexion.collection("libros")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val librillo = document.toObject(Libro::class.java)
                        librillo.idLibroFB=document.id
                        libros.add(librillo)
                    }
                    libros.forEach {
                        Log.d("firebase",it.idLibroFB+"--"+it.titulo)
                    }
                }
                else {
                    Log.d("firebase", "Error al obtener documentos.", task.exception)
                }
            }
        //return libros*/

        var libros : MutableList<Libro> = mutableListOf()

        conexion.collection("libros")
            .get()
            .addOnSuccessListener { querySnapshot ->

                for (document in querySnapshot) {
                    val librillo = document.toObject(Libro::class.java)
                    libros.add(librillo)
                }
                libros.forEach {
                    Log.d("firebase",it.idLibroFB+"--"+it.titulo)
                }
            }
            .addOnFailureListener { exception ->
                // Maneja el error
            }
        return libros


    }

    override fun updateLibro(li: Libro) {
        val datosActualizados = mapOf(
            "autor" to "Rebecca Torres"
        )
        conexion.collection("libros").document(li.idLibroFB)
            .update(datosActualizados)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e -> Log.w("TAG", "Error updating document", e) }
    }

    override fun deleteLibro(li: Libro) {
        conexion.collection("libros").document(li.idLibroFB)
            .delete()
            .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w("TAG", "Error deleting document", e) }
    }

    fun createConexion(bd : BDFireBase) {
        conexion = bd.conexion
    }


}