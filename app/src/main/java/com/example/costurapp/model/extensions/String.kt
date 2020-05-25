package com.example.costurapp.model.extensions

fun String.ParaBoolean():Boolean{
    return when(this.toUpperCase()){
        "S"->true
        else-> false
    }
}