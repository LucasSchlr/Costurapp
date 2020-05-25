package com.example.costurapp.model.classes

data class Material(
    val TipoMaterial:TipoMaterial,
    val Descricao:String,
    val UnidadeMedida:UnidadeMedida,
    val Nome:String,
    val Estampa:String,
    val TecidoPlano:Boolean,
    val Transparente:Boolean,
    val Overlock:Boolean,
    val Tamanho:Tamanho,
    val TipoZiper:TipoZiper,
    val Estampado:Boolean
) {
}