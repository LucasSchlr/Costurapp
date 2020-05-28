package com.example.costurapp.model.classes

enum class TipoZiper (
    val index:Int
) {
    NORMAL(0),
    JEANS(1),
    INVISIVEL(2);

    companion object {
        fun from(findValue: Int): TipoZiper = values().first { it.index == findValue }
    }
}