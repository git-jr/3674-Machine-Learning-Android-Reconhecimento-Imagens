package com.br.alura.galeria.extensions

fun String.cleanBrackets(): String {
    return this.replace("[", "").replace("]", "")
}