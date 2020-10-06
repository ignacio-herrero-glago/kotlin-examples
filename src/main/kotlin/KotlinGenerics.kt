package main.kotlin

/**
 * Funcion principal
 */
fun main() {

    genericos()

}



/************************************************************************************************************/

fun <T, U> concat (value1 : T, value2: U) : String {
    return "Concat: $value1-$value2"
}

fun <T> imprimeLista(lista : List<T>) {
    for (elem in lista) {
        print("$elem ")
    }
}

// Any es como Object
fun <T : Any> imprimeListaNoNulos(lista : List<T>) {
    for (elem in lista) {
        print("$elem ")
    }
}

//-----------------------------

fun genericos() {

    val cadena = "mi cadena"
    val numero = 111
    println("The concat function: ${concat(cadena, numero)}")
    println("The concat function: ${concat(numero, cadena)}")

    val lista = listOf("hola", 2, 'a', null)
    imprimeLista(lista)
    //imprimeListaNoNulos(lista) -> no compila pues la lista tiene un nulo, y debe tener elementos que extiendan de Any (no nulos)

}
