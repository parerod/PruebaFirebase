package com.example.pruebafirebase.modelo.interfaces

import androidx.lifecycle.LiveData
import com.example.pruebafirebase.modelo.entidades.Libro

interface InterfaceDaoLibro {

    fun addLibro(li : Libro)
    fun getLibro(titulo : String) : Libro
    fun getLibrosAutor(autor : String):MutableList<Libro>
    fun getLibros(): MutableList<Libro>
    fun updateLibro(li : Libro)
    fun deleteLibro(li : Libro)
}