package com.example.costurapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import com.example.costurapp.R
import com.example.costurapp.model.database.CosturappDataBaseHelper
import kotlinx.android.synthetic.main.activity_material_lista.*

class MaterialListaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_lista)


        val db = CosturappDataBaseHelper(this).readableDatabase
        val qrMain = db?.query(
            "MATERIAL",
            arrayOf("_id","DESCRICAO"),
            null,null,null,null,null
        )

        val listAdapter = SimpleCursorAdapter(
            this,
            R.layout.database_list,
            qrMain,
            arrayOf("DESCRICAO"),
            intArrayOf(R.id.texto_unico_um),
            0
        )

        lista_materiais.adapter = listAdapter
//        lista_materiais.setOnItemClickListener{parent, view, position,id ->
//            val intent = Intent(this@MainActivity, DrinkActivity::class.java)
//            intent.putExtra("drinkId", id.toInt())
//
//            startActivity(intent)
//        }

    }
}
