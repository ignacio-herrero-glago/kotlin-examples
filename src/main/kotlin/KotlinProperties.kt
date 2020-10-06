package main.kotlin

import main.kotlin.extensions.*

/**
 * Java: se considera un property al atributo de un bean+getter+setter
 * Kotlin: idem, pero se define mas conciso, con el matiz:
 * - property = field + accesor(s)
 * - val = field + getter
 * - var = field + getter + setter
 */
fun main() {

    lazyProperties()

    lazyInitialization()

}


/************************************************************************************************************/

class Car {

    var ages : Int = 0

    // Se puede customizar el get()
    var isOld : Boolean = false
        get() {
            return ages > 18
        }
}

//------------------------------------

/**
 * Tambien pueden definirse propiedades en interfaces
 */
interface User {
    val nickname : String
}

class FacebookUser (val accounId : String) : User {
    // Podemos sobreescribir la propiedad (así, se calcula solo una vez)
    override val nickname: String = "tu"
}

class LinkedInUser (val accounId : String) : User {
    // Podemos sobreescribir la propiedad (con get, se calcula cada vez que se llama)
    override val nickname: String
        get() = "yo"
}

//------------------------------------

fun lazyProperties() {

    // Son propidades que no se calculan hasta que no se llaman
    val lazyString : String by lazy {
        println("Calculating...")
        "Lazy"
    }

    // Esto imprimirá: calculating, lazy, lazy
    println("$lazyString")
    println("$lazyString")

}

//------------------------------------

/**
 * late initialization: permite que las variables no se les tenga que dar valor hasta mas adelante
 */
class MiClase1 {
    // me obliga a darle valor, y si le doy null, tengo que poner la ? para evitar NPE y también cada vez que la use
    val clase2 : MiClase2? = null
    // esto indica que luego se inicializará (se hace porque sabemos que siempre tendrá valor)
    // - tiene que ser var, no se puede usar un nullable type ni un primitive type
    lateinit var otraClase2 : MiClase2

    fun postContrstuctor() {
        otraClase2 = MiClase2()
    }

}
class MiClase2 {
    val propiedad1 : String = "hola"
}

fun lazyInitialization() {
    val miObjeto1 : MiClase1 = MiClase1()
    println("Propiedad 1: ${miObjeto1.clase2?.propiedad1}") // Tengo que poner el ?
    // println("Propiedad 1: ${miObjeto1.otraClase2.propiedad1}") // Sin ? :) -> se deja comentado porque el postConstructor es solo un ejemplo, necesitaríamos un framework para que funcione
}