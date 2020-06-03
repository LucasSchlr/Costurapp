package com.example.costurapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.costurapp.R
import com.example.costurapp.view.MaterialActivity
import com.example.costurapp.view.MaterialListaActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ListaOnClick(view: View){
        startActivity(retornaTesteIntent(MaterialListaActivity::class))
    }

    fun retornaTesteIntent(toOpenClass: KClass<*>):Intent{
        return Intent(this@MainActivity, toOpenClass.java)
    }

}
