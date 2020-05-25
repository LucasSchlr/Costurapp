package com.example.costurapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.costurapp.R
import kotlinx.android.synthetic.main.activity_material.*

class MaterialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        desabilitaLayoutEspecifico()

        tipo_material.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    desabilitaLayoutEspecifico()
                    when (position){
                        0->layout_tecido.visibility = View.VISIBLE
                        1->layout_linha.visibility = View.VISIBLE
                        2->layout_ziper.visibility = View.VISIBLE
                        3->layout_botao.visibility = View.VISIBLE
                        else->layout_gancho.visibility = View.VISIBLE
                    }
                }
            }
    }

    fun desabilitaLayoutEspecifico(){
        layout_tecido.visibility = View.GONE
        layout_linha.visibility = View.GONE
        layout_ziper.visibility = View.GONE
        layout_gancho.visibility = View.GONE
        layout_botao.visibility = View.GONE
    }
}
