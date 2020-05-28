package com.example.costurapp.model.classes

enum class Tamanho (
    val index:Int
) {
    PEQUENO(0),
    MEDIO(1),
    GRANDE(2);
    companion object{
        fun from(findValue: Int): Tamanho = values().first { it.index == findValue }
    }

}
