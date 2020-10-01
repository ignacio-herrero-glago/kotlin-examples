package main.kotlin

/**
 * Funcion principal
 */
fun main() {

    whens()

    bucles()

    rangos()

    excepciones()

}


/************************************************************************************************************/

enum class Color {
    RED, YELLOW, GREEN, BLUE;
}

/**
 * When (parecido al switch en Java)
 */
fun whens() {

    println("Call to function with when: ${imprimirColor(Color.RED)}")

    asignarPar(12)

}

fun imprimirColor(color: Color): String =
    when (color) {
        Color.YELLOW, Color.RED -> "hot"
        Color.GREEN -> "cold"
        else -> "unknown"
    }

fun asignarPar(temperature: Int) {
    val (color, sensation) = when {
        temperature < 0 -> Color.BLUE to "frozen"
        temperature < 10 -> Color.GREEN to "cold"
        temperature < 20 -> Color.YELLOW to "calm"
        temperature < 30 -> Color.RED to "hot"
        else -> null to ""
    }
    println("Par assigned: $color-$sensation")
}

/**
 * Bucles
 */
fun bucles() {

    print("Calling function 'repetir'... ")
    repetir(character = '/', times = 3)
    print("Calling function 'repetir'... ")
    repetir(times = 10)

    print("Calling function 'recorrerLista'... ")
    recorrerLista(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))

    print("Calling function 'recorrerMapa'... ")
    recorrerMapa(mapOf(1 to "one", 2 to "two", 3 to "three"))

    print("Calling function 'conteoInverso'... ")
    conteoInverso(1, 9, 2)

}

fun repetir(character: Char = '*', times: Int = 5) {
    repeat(times) {
        print(character)
    }
    println()
}

fun recorrerLista(lista: List<Int>) {
    for (elem in lista) {
        print(elem)
    }
    println()
}

fun recorrerMapa(mapa: Map<Int, String>) {
    for ((key, value) in mapa) {
        print("$key=$value ")
    }
    println()
}

fun conteoInverso(from: Int, to: Int, step: Int) {
    for (i in to downTo from step step) {
        print(i)
    }
    println()
}

/**
 * Rangos: se usan para bucles pero también para pertenencias
 */
fun rangos() {

    val letra = 'c'
    println("Checking if $letra is a letter... ${comprobarLetra(letra)}")

}

fun comprobarLetra(letra: Char) =
    letra in 'a'..'z' || letra in 'A'..'Z'

/**
 * Excepciones: no hay checked and unchecked
 */
fun excepciones() {

    val number1 = 70
    val number2 = 150
    println("Checking number $number1 in range... ${comprobarNumeroEnRango(number1)}")
    println("Checking number $number2 in range... ${comprobarNumeroEnRango(number2)}")

}

fun comprobarNumeroEnRango(number: Int): Boolean {
    if (number in 1..100)
        return true
    else
        throw IllegalArgumentException("Number out of range!!")

}
