package com.example.costurapp.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.example.costurapp.model.database.CosturappDataBaseHelper
import com.example.costurapp.model.extensions.ParaString
import com.example.costurapp.presenter.ListaCadastroPresenter

class DatabaseListaMaterial(): ListaCadastroPresenter.modelListaCadastro {
    override val campoDescritivo = "DESCRICAO"

    override suspend fun cursorLista(context: Context): Cursor {
        val db = CosturappDataBaseHelper(context).readableDatabase

        Log.i("teste", "pega cursor")
        return db.query(
            "MATERIAL",
            arrayOf("_id",campoDescritivo),
            "ATIVO = ?",
            arrayOf("S"),null,null,null
        )
    }

    override suspend fun updateDeleteItems(context: Context, idsToUpdate:List<Int>):Boolean{
        val db = CosturappDataBaseHelper(context).writableDatabase

        for (value in idsToUpdate) {
            val registroMaterial = ContentValues()
            registroMaterial.put("ATIVO", "N")
            db.update(
                "MATERIAL",
                registroMaterial,
                "_id = ?",
                arrayOf(value.toString() )
            )
        }

        return true
    }
}