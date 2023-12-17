package com.example.pruebafirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.gestiondietapp2023.ModelViews.ServiceModelView
import com.example.pruebafirebase.modelo.entidades.Libro

class MainActivity : AppCompatActivity() {

    private  val svm: ServiceModelView by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pruebaFirebase()

        /*findViewById<Button>(R.id.btnRecoger).setOnClickListener {
            recogerDatos()
        }*/

        //anadirDatos()
        //recogerDatos()
        //update()
        //borrarDatos()
        getOne()
        //recogerDatosAutor()

        //borrarDatos()

    }

    fun borrarDatos() {
        var librillo = svm.getLibro("Alas de Sangre")
        svm.deleteLibro(librillo)

    }

    fun update() {
        var librillo = Libro("Pepe hillo","Rebecca Navarrosa")
        svm.updateLibro(librillo)
    }

    fun getOne() {
        var librillo = svm.getLibro("Alas de Sangre")
        Log.d("GetOne",librillo.titulo + " " + librillo.autor)
    }

    fun anadirDatos() {
        /*var libro1 = Libro("El problema final","Arturo Perez Reverte")
        var libro2 = Libro("El imperio final","Brandon Sanderson")
        var libro3 = Libro("La leyenda del hechicero","Taran Matharu")
        svm.addLibro(libro1)
        svm.addLibro(libro2)
        svm.addLibro(libro3)*/
        var libroUltimo = Libro("Alas de Sangre","Rebeca Yarros")
        svm.addLibro(libroUltimo)

    }

    fun recogerDatos() {
        val lista = svm.getLibros()
        lista.forEach {
            it.titulo?.let { it1 -> Log.d("todos", it1) }
        }
    }

    fun recogerDatosAutor() {
        val lista=svm.getLibrosAutor("Arturo Perez Reverte")
        lista.forEach {
            it.titulo?.let { it1 -> Log.d("ver", it1) }
        }
    }

    fun pruebaFirebase() {
        svm.createService()
    }
}