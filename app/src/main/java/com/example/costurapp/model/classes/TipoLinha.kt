package com.example.costurapp.model.classes

enum class TipoLinha(
    val index:Int
) {
    RETA(0),
    OVERLOCK(1);

    companion object {
        fun from(findValue: Int): TipoLinha = values().first { it.index == findValue }
    }
}