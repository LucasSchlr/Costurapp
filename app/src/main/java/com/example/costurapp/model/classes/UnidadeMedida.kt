package com.example.costurapp.model.classes

enum class UnidadeMedida(
    val index:Int
) {
    METROS(0),
    UNIDADES(1),
    CENTIMETROS(2);

    companion object {
        fun from(findValue: Int): UnidadeMedida = values().first { it.index == findValue }
    }
}