package com.example.costurapp.model

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.costurapp.model.classes.*
import com.example.costurapp.model.database.CosturappDataBaseHelper
import com.example.costurapp.model.extensions.ParaBoolean
import com.example.costurapp.model.extensions.ParaString
import com.example.costurapp.presenter.CadastroPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseCadastroMaterial:CadastroPresenter.modelCadastro {
    override suspend fun insertMaterial(material: Material, context:Context) {
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

    override suspend fun updateCadastro(idCadastro: Int, cadastro: Material, context: Context) {
        val db = CosturappDataBaseHelper(context).writableDatabase

        val registroMaterial = ContentValues()
        registroMaterial.put("TIPO_MATERIAL", cadastro.TipoMaterial.index)
        registroMaterial.put("DESCRICAO", cadastro.Descricao)
        registroMaterial.put("UNIDADE_MEDIDA", cadastro.UnidadeMedida.index)
        registroMaterial.put("NOME", cadastro.Nome)
        registroMaterial.put("ESTAMPA", cadastro.Estampa)
        registroMaterial.put("TECIDO_PLANO", cadastro.TecidoPlano?.ParaString())
        registroMaterial.put("TRANSPARENTE", cadastro.Transparente?.ParaString())
        registroMaterial.put("TIPO_LINHA", cadastro.TipoLinha?.index)
        registroMaterial.put("TAMANHO", cadastro.Tamanho?.index)
        registroMaterial.put("TIPO_ZIPER", cadastro.TipoZiper?.index)
        registroMaterial.put("ESTAMPADO", cadastro.Estampado?.ParaString())
        db.update(
            "MATERIAL",
            registroMaterial,
            "_id = ?",
            arrayOf(idCadastro.toString())
        )
    }
    override suspend fun abreCadastro(idCadastro: Int, context:Context): Material? {
        val db = CosturappDataBaseHelper(context).readableDatabase
        val qr = db.query(
            "MATERIAL",
            arrayOf("TIPO_MATERIAL","DESCRICAO","UNIDADE_MEDIDA","NOME","ESTAMPA","TECIDO_PLANO","TRANSPARENTE",
                "TIPO_LINHA","TAMANHO","TIPO_ZIPER","ESTAMPADO"),
            "_id = ?",
            arrayOf(idCadastro.toString()),
            null,null,null
        )
    //    try {
            if (qr.moveToFirst()) {
                return Material(
                    TipoMaterial=TipoMaterial.from(qr.getInt(0)),
                    Descricao = qr.getString(1),
                    UnidadeMedida = UnidadeMedida.from(qr.getInt(2)),
                    Nome = qr.getString(3),
                    Estampa = qr.getString(4),
                    TecidoPlano = qr.getString(5)?.ParaBoolean(),
                    Transparente = qr.getString(6)?.ParaBoolean(),
                    //TipoLinha =  TipoLinha.from(qr.getInt(7)),
                    Tamanho = Tamanho.from(qr.getInt(8)),
                    TipoZiper = TipoZiper.from(qr.getInt(9)),
                    Estampado = qr.getString(10)?.ParaBoolean()
                )
            }else{
                return null
            }
//        }finally {
//            qr.close()
//            db.close()
//        }
    }
}