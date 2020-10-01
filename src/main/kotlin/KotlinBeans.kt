package main.kotlin

import main.kotlin.beans.Person

/**
 * Funcion principal
 */
fun main() {

    createAndUseBean()

}


/************************************************************************************************************/

fun createAndUseBean() {
    val person : Person = Person(name = "Pedro", age = 25, ciudad = "Madrid")

    println("Person data: $person")

    //person.age = 40 -> age es val, error de compilacion
    person.ciudad = "Cuenca"
    person.hijos = 2

    println("Person data: $person")

}
