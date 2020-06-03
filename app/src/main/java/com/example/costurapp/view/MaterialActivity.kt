package com.example.costurapp.view

import android.content.Context
import android.content.Intent
import android.opengl.GLES31Ext
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.costurapp.R
import com.example.costurapp.model.classes.*
import com.example.costurapp.model.DatabaseCadastroMaterial
import com.example.costurapp.presenter.CadastroPresenter
import com.example.costurapp.presenter.ListaCadastroPresenter.Companion.CADASTRO_ID
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.activity_material_botao.*
import kotlinx.android.synthetic.main.activity_material_linha.*
import kotlinx.android.synthetic.main.activity_material_tamanhos.*
import kotlinx.android.synthetic.main.activity_material_tecido.*
import kotlinx.android.synthetic.main.activity_material_ziper.*

class MaterialActivity : AppCompatActivity(),CadastroPresenter.viewCadastro {

    var presenter:CadastroPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        presenter = CadastroPresenter(
            this,
            DatabaseCadastroMaterial(),
            intent.extras!!.getLong(CADASTRO_ID).toInt())

        desabilitaLayoutEspecificos()

        tipo_material.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    desabilitaLayoutEspecificos()
                    when (position){
                        0->layout_tecido.visibility = View.VISIBLE
                        1->layout_linha.visibility = View.VISIBLE
                        2->{
                            layout_tamanhos.visibility = View.VISIBLE
                            layout_ziper.visibility = View.VISIBLE
                        }
                        3->{
                            layout_tamanhos.visibility = View.VISIBLE
                            layout_botao.visibility = View.VISIBLE
                        }
                        else->layout_tamanhos.visibility = View.VISIBLE
                    }
                }
            }
    }

    fun desabilitaLayoutEspecificos(){
        layout_tecido.visibility = View.GONE
        layout_linha.visibility = View.GONE
        layout_ziper.visibility = View.GONE
        layout_tamanhos.visibility = View.GONE
        layout_botao.visibility = View.GONE
    }

    override fun contexto(): Context {
        return this
    }

    override fun retornaMaterial(): Material{
        val material = Material(
            TipoMaterial = TipoMaterial.from(tipo_material.selectedItemPosition),
            Descricao =  descricao_material.text.toString(),
            UnidadeMedida = UnidadeMedida.from(unidade_medida.selectedItemPosition)
        )

        when (material.TipoMaterial){
            TipoMaterial.TECIDO->{
                material.Nome = nome_material.text.toString()
                material.Estampa = estampa_tecido.text.toString()
                material.TecidoPlano = tecido_plano.isChecked
                material.Transparente = tecido_transparente.isChecked
            }
            TipoMaterial.LINHA->material.TipoLinha = TipoLinha.from(if (linha_overlock.isChecked){1}else{0})
            TipoMaterial.ZIPER->{
                material.Tamanho = Tamanho.from(tamanho_material.selectedItemPosition)
                material.TipoZiper = TipoZiper.from(tipo_zipper.selectedItemPosition)
            }
            TipoMaterial.BOTAO->{
                material.Tamanho = Tamanho.from(tamanho_material.selectedItemPosition)
                material.Estampado = estampado.isChecked
            }
            else->layout_tamanhos.visibility = View.VISIBLE
        }
        return material
    }
    fun onClickSalvar(view:View){
        presenter!!.clickSalvar()
    }

    override fun configuraUI(objetoCadastro: Material?) {
        objetoCadastro?.TipoMaterial?.index?.let { tipo_material.setSelection(it) }
        descricao_material.setText(objetoCadastro?.Descricao)
        objetoCadastro?.UnidadeMedida?.index?.let { unidade_medida.setSelection(it) }
        nome_material.setText(objetoCadastro?.Nome)
        estampa_tecido.setText(objetoCadastro?.Estampa)
        tecido_plano.isChecked = objetoCadastro?.TecidoPlano ?: false
        tecido_transparente.isChecked = objetoCadastro?.Transparente ?: false
        //tipoLinha  material.TipoLinha = TipoLinha.from(if (linha_overlock.isChecked){1}else{0})
        tamanho_material.setSelection(objetoCadastro?.Tamanho?.index?:-1)
        tipo_zipper.setSelection(objetoCadastro?.TipoZiper?.index?:-1)
        estampado.isChecked = objetoCadastro?.Estampado ?: false
    }
}
