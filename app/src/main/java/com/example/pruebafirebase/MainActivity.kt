package com.example.pruebafirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.navigation.ui.AppBarConfiguration
import com.example.gestiondietapp2023.ModelViews.ServiceModelView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private  val svm: ServiceModelView by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pruebaFirebase()

        findViewById<Button>(R.id.btnRecoger).setOnClickListener {
            recogerDatos()
        }

    }

    fun recogerDatos() {
        val lista=svm.getLibros()
        lista.forEach {
            Log.d("firebase",it.titulo)
        }
    }

    fun pruebaFirebase() {
        svm.createService()
    }
}