package com.example.costurapp.model

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.costurapp.model.classes.Material
import com.example.costurapp.model.database.CosturappDataBaseHelper
import com.example.costurapp.model.extensions.ParaString
import com.example.costurapp.presenter.CadastroPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class databaseMaterial:CadastroPresenter.modelCadastroMaterial {
    val uiScope = CoroutineScope(Dispatchers.Default)
    override fun insertMaterial(material: Material, context:Context) {
        Log.i("teste","chegou aqui")
        uiScope.launch { insereMaterialSuspenso(material, context) }
    }

    fun insereMaterialSuspenso(material: Material, context:Context){
        val db = CosturappDataBaseHelper(context).writableDatabase

        val registroMaterial = ContentValues()
        registroMaterial.put("TIPO_MATERIAL", material.TipoMaterial.index)
        registroMaterial.put("DESCRICAO", material.Descricao)
        registroMaterial.put("UNIDADE_MEDIDA", material.UnidadeMedida.index)
        registroMaterial.put("NOME", material.Nome)
        registroMaterial.put("ESTAMPA", material.Estampa)
        registroMaterial.put("TECIDO_PLANO", material.TecidoPlano?.ParaString())
        registroMaterial.put("TRANSPARENTE", material.Transparente?.ParaString())
        registroMaterial.put("TIPO_LINHA", material.TipoLinha?.index)
        registroMaterial.put("TAMANHO", material.Tamanho?.index)
        registroMaterial.put("TIPO_ZIPER", material.TipoZiper?.index)
        registroMaterial.put("ESTAMPADO", material.Estampado?.ParaString())

        db?.insert("MATERIAL", null, registroMaterial)
        db.close()
    }
}