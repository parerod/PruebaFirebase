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
                Log.d("firebase", "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.d("firebase", "Error adding document", e)
            }
    }

    override fun getLibro(titulo: String): Libro {
        TODO("Not yet implemented")
    }

    override fun getLibros(): MutableList<Libro> {
        var libros : MutableList<Libro> = mutableListOf()
        conexion.collection("libros")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val librillo = document.toObject(Libro::class.java)
                    libros.add(librillo)
                }
                libros.forEach {
                    Log.d("firebas",it.idLibroFB)
                }
            }
        return libros
    }

    override fun updateLibro(li: Libro) {
        TODO("Not yet implemented")
    }

    override fun deleteLibro(li: Libro) {
        TODO("Not yet implemented")
    }

    fun createConexion(bd : BDFireBase) {
        conexion = bd.conexion
    }


}