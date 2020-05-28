package com.example.costurapp.model.classes

enum class TipoMaterial(
    val index:Int
) {
    TECIDO(0),
    LINHA(1),
    ZIPER(2),
    BOTAO(3),
    GANCHO(4);

    companion object {
        fun from(findValue: Int): TipoMaterial = values().first { it.index == findValue }
    }
}