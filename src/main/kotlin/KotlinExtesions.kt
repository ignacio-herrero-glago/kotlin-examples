package main.kotlin

import main.kotlin.extensions.*

/**
 * Funcion principal
 */
fun main() {

    extensionFunctions()

}

/************************************************************************************************************/

/**
 * Extensions: son como funciones static en Java
 */
fun extensionFunctions() {

    val cadena1 = "Hola mundo"
    val cadena2 = "Hola mundo"
    // inline extension function
    val areEquals = cadena1 eq cadena2
    val repeat = 5
    val par1 = "clave"
    val par2 = 5
    val pair = par1 pareja par2
    val extensionOrMember1 = cadena1.get(3)
    val extensionOrMember2 = cadena1.get(index = 3, last = ".")

    println("The last char of $cadena1 is... ${cadena1.lastChar()}")
    println("The number of chars of $cadena1 is... ${cadena1.countChars()}")
    println("The $cadena1 repeated $repeat times is... ${cadena1.repetir(times = repeat)}")
    println("The strings $cadena1 and $cadena2 are equals... $areEquals")
    println("The pair from $par1 and $par2 is... $pair")
    println("Extension or member for string $cadena1... $extensionOrMember1")
    println("Extension or member for string $cadena1... $extensionOrMember2")

    println("Check empty or null for $cadena1... ${cadena1.isEmptyOrNull()}")

}

