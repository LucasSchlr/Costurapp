package com.example.costurapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.costurapp.R
import com.example.costurapp.view.MaterialActivity
import com.example.costurapp.view.MaterialListaActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun NovoMaterialOnClick(view: View){
        val intent = Intent(this@MainActivity, MaterialActivity::class.java)
        startActivity(intent)
    }

    fun ListaOnClick(view: View){
        val intent = Intent(this@MainActivity, MaterialListaActivity::class.java)
        startActivity(intent)
    }

}
