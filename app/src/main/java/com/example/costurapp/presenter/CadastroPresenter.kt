package com.example.costurapp.presenter

import android.content.Context
import com.example.costurapp.model.classes.Material

class CadastroPresenter(
    val view:viewCadastroMaterial,
    val model:modelCadastroMaterial
) {
    fun insereMaterial() {
        model.insertMaterial(view.retornaMaterial(), view.contexto() )
    }

    interface viewCadastroMaterial {
        fun retornaMaterial(): Material
        fun contexto():Context
    }

    interface modelCadastroMaterial {
       fun insertMaterial(material: Material, context: Context)
    }
}
