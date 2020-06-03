package com.example.costurapp.presenter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.costurapp.model.classes.Material
import kotlinx.coroutines.*

class CadastroPresenter(
    val view:viewCadastro,
    val model:modelCadastro,
    val idCadastro:Int
) {
    val telaEmConsulta = if (idCadastro >= 0){true}else{false}
    val uiScope = CoroutineScope(Dispatchers.Default)

    init{
        if (telaEmConsulta){
            uiScope.launch {
                val cadastro = uiScope.async {  model.abreCadastro(idCadastro, view.contexto())}
                val objeto = cadastro.await()

                withContext(Dispatchers.Main) {
                    view.configuraUI(
                        objeto
                    )
                }
            }
        }
    }

    fun clickSalvar() {
        uiScope.launch {
            val sucesso = uiScope.async {
                if (telaEmConsulta) {
                    model.updateCadastro(idCadastro, view.retornaMaterial(), view.contexto())
                    true
                } else {
                    model.insertMaterial(view.retornaMaterial(), view.contexto())
                    true
                }
            }

            if (sucesso.await()) {
                val result = Intent()
                    .putExtra(RESULT_CADASTRO, "Procedimento realizado")

                (view.contexto() as AppCompatActivity).setResult(
                    ListaCadastroPresenter.REQUEST_ABRE_DETALHE,
                    result
                )
                (view.contexto() as AppCompatActivity).finish()
            }
        }
    }

    interface viewCadastro {
        fun retornaMaterial(): Material
        fun contexto():Context
        fun configuraUI(objetoCadastro:Material?)
    }

    interface modelCadastro {
        suspend fun insertMaterial(material: Material, context: Context)
        suspend fun updateCadastro(idCadastro:Int, cadastro:Material, context: Context)
        suspend fun abreCadastro(idCadastro:Int, context: Context):Material?
    }

    companion object{
        val RESULT_CADASTRO = "RESULT_CADASTRO"
    }
}
