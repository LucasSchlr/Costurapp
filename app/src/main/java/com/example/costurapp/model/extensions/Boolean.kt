package com.example.costurapp.model.extensions

fun Boolean.ParaString():String{
    return when (this){
        true->"S"
        else->"N"
    }
}
