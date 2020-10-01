package main.kotlin.extensions

/**
 * Extiende la clase String con más funciones
 */

// El 'String' inicial se llama receiver
// Esta extension función es el equivalente a un método estático en Java:
// String.funcion() : Boolean -> public static Boolean funcion(String s);
// el uso de 'this' referencia al receiver que llega como parámetro al método estático
fun String.lastChar() = this.get(this.length-1)

fun String.countChars() = this.length

fun String.repetir(times : Int) : String {
    val repeatedString : StringBuilder = java.lang.StringBuilder()
    for (i in 1..times) {
        repeatedString.append(this)
        if (i != times) {
            repeatedString.append("-")
        }
    }
    return repeatedString.toString()
}

// infix permite usar la funcion entre 2 Strings además de la forma habitual
infix fun String.eq(cadena : String) = this == cadena

infix fun <A, B> A.pareja(elem : B) : Pair<A, B> = Pair(this, elem)

// Esta extensión coindice con el member 'get' de String; como tiene la misma firma, tiene preferencia el member
fun String.get(index : Int) = "*"

// Esta extensión no coindice con el member 'get' de String, porque tiene 2 parámetros, así que sí se usa
fun String.get(index : Int, last : String) = "*"

fun String?.isEmptyOrNull() : Boolean = this == null || this.isEmpty()

