package com.example.costurapp.model.classes

data class Material(
    val TipoMaterial: TipoMaterial,
    val Descricao:String,
    val UnidadeMedida:UnidadeMedida,
    var Nome:String? = null,
    var Estampa:String? = null,
    var TecidoPlano:Boolean? = null,
    var Transparente:Boolean? = null,
    var TipoLinha:TipoLinha? = null,
    var Tamanho:Tamanho? = null,
    var TipoZiper:TipoZiper? = null,
    var Estampado:Boolean? = null,
    var Ativo:Boolean = true
) {
}