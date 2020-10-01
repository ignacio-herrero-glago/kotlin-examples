// Esta anotación cambia el nombre de la clase para ser usada desde otra
@file:JvmName("KotlinUtils")
package main.kotlin

/*
 * - En este fichero no hay nombre de clase, las funciones son de primer nivel
 * - si hubiera una clase, las funciones de dentro serían de segundo nivel
*/

/**
 * Funcion principal
 */
fun main() {

    listas()

    // LLamadas a funciones
    println("Calling function foo once: ${foo()} and twice: ${foo()}")

    listas()

    funciones()

}

/************************************************************************************************************/

/**
 * Foo
 */
fun foo() : String {
    println("(function called...)")
    return "foo"
}

/**
 * Variables (Unit es como void en Java)
 */
fun variables() : Unit {
    // VAL (better) vs VAR
    // Variable val readonly (una sola asignacion), como el final de Java -> indicamos el tipo explicitamente
    val name : String = "Nacho"
    // Variable var read/write (de escritura) -> no indicamos el tipo explicitamente y el compilador lo infiere
    var surname = "Herrero"

    // Impresión de variables
    println("Hello $name $surname!!!")
}


/**
 * Listas
 */
fun listas() {
    // Listas
    val readOnlyList = listOf("a","b","c")
    //readOnlyList.add("d") -> error de compilacion
    val readWriteList = mutableListOf("a","b","c")
    readWriteList.add("d")

    println("The readOnlyList is $readOnlyList")
    println("The readWriteList is $readWriteList")

    // Named arguments: se pueden pasar en cualquier orden pues llevan correspondencia por nombre
    println("The readWriteList as string is: " + readWriteList.joinToString(separator = "-", prefix = "(", postfix = ")"))
    println("The readWriteList as another string is: " + readWriteList.joinToString(postfix = "."))
}

/**
 * Funciones
 */
fun funciones() {
    val a = 5
    val b = 7
    println("The maximum of $a and $b is ${maximum(number1 = a, number2 = b)}")
}

fun maximum(number1 : Int, number2 : Int) : Int = if (number1 > number2) number1 else number2
// Same as:
//fun maximum(a : Int, b : Int) : Int {
//    return if (a > b) a else b
//}
