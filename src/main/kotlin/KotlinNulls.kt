package main.kotlin

/**
 * Funcion principal
 */
fun main() {

    nullVariables()

    elvisOperator()

    notNullAssertion()

}



/************************************************************************************************************/

fun nullVariables() {
    val variable1 : String = "This is a String"
    //val variable2 : String = null -> ya no da nullPointerException, da error de compilación
    val variable2 : String? = null // con la ? indicamos que es nula, y el tipo de la variable es String?

    // Ejemplos mas complejos de listas
    val listaEnterosONulos : List<Int?> // lista de enteros que pueden ser nulos
    val listaEnterosNula : List<Int>? // lista de enteros que puede ser nula, pero si no lo es, no puede tener nulos

    val length1 : Int = variable1.length
    val length2a : Int = if (variable2 != null) variable2.length else 0
    //val length2b : Int = variable2.length -> error compilacion
    //val length2b : Int = variable2?.length -> error compilacion
    val length2b : Int? = variable2?.length // el tipo tiene que ser nullable Int

    println("The var1 is... $variable1")
    println("The var2 is... $variable2")

    // No es posible acceder a propiedades de variables de nullable type
    println("The var1 length is... ${variable1.length}")

    // println("The var2 length is... ${variable2.length}") -> error de compilacion
    if (variable2 != null) {
        println("Option A) The var2 length is... ${variable2.length}")
    }
    println("Option B) The var2 length is... ${variable2?.length}")

}

/**
 * Se usa así: x ?: y
 * Devuelve x si x!=null e y si x==null
 */
fun elvisOperator() {

    val a : Int? = null
    val b : Int? = 1
    val c : Int = 2

    val sumAC : Int =  (a ?: 0) + c
    val sumBC : Int =  (b ?: 0) + c

    println("The sum of a+c is... $sumAC")
    println("The sum of b+c is... $sumBC")

}

/**
 * Se usa con 2 exclamaciones de la forma: x!!
 * Devuelve x si x!=null y KotlinNullPointerExcepcion si x==null
 */
fun notNullAssertion() {

    val s1 : String? = "test"
    val s2 : String? = null

    // No ocurre nada
    s1!!
    // Lanza KotlinNullPointerExcepcion
    s2!!

}
