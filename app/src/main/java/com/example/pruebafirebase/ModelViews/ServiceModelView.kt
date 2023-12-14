package com.example.gestiondietapp2023.ModelViews

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.gestiondietapp2023.conexiones.BDFireBase
import com.example.pruebafirebase.modelo.daos.DaoLibroFireBase
import com.example.pruebafirebase.modelo.entidades.Libro
import com.example.pruebafirebase.modelo.interfaces.InterfaceDaoLibro
import com.google.firebase.firestore.FirebaseFirestore

class ServiceModelView(application: Application): AndroidViewModel(application), InterfaceDaoLibro {
    lateinit var firebase : BDFireBase
    lateinit var conex : FirebaseFirestore

    private lateinit var daoLibro: InterfaceDaoLibro

    private lateinit var libros:MutableList<Libro>
    private lateinit var libro:Libro

    fun createService(){
        firebase = BDFireBase()
        conex = firebase.conexion

        daoLibro = DaoLibroFireBase()
        (daoLibro as DaoLibroFireBase).createConexion(firebase)


    }

    override fun addLibro(li: Libro) {
        daoLibro.addLibro(li)
    }

    override fun getLibro(titulo: String): Libro {
        return daoLibro.getLibro(titulo)
    }

    override fun getLibros(): MutableList<Libro> {
        return daoLibro.getLibros()
    }

    override fun updateLibro(li: Libro) {
        daoLibro.updateLibro(li)
    }

    override fun deleteLibro(li: Libro) {
        daoLibro.deleteLibro(li)
    }


}