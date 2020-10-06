package main.kotlin

import main.kotlin.beans.Person

/**
 * Funcion principal
 */
fun main() {

    declaracionLambdas()

    funcionesComunesCollections()

    functionTypes()

    memberReferences()

    boundUnboundReferences()

}



/************************************************************************************************************/

fun declaracionLambdas() {

    val lista = listOf<Int>(1,2,3,4)
    println("The list is... $lista")

    // 1. Una lambda va entre { } en negrita
    val lambda1 = lista.filter({ elem : Int -> elem > 2 })
    println("Lambda1: $lambda1")

    // 2. En Kotlin, si la lambda es el último parámetro, se puede sacar de los parentesis
    val lambda2 = lista.filter() { elem : Int -> elem > 2 }
    println("Lambda2: $lambda2")

    // 3. Es más, se pueden eliminar los parentesis
    val lambda3 = lista.filter { elem : Int -> elem > 2 }
    println("Lambda3: $lambda3")

    // 4. Si se puede inferir el tipo, se puede eliminar tambien
    val lambda4 = lista.filter { elem -> elem > 2 }
    println("Lambda4: $lambda4")

    // 5. Si tiene solo un argumento, puede reemplazarse por 'it'
    val lambda5 = lista.filter { it > 2 }
    println("Lambda5: $lambda5")

    // 6. Si la lambda tiene varios pasos, se ponen en varias lineas y la última será el resultado
    // OJO: no tienen operacion terminal como en Java!!! Se ejecutan según se declaran!!
    val lambda6 = lista.filter {
        println("   (procesando lambda)")
        it > 2
    }
    println("Lambda6:") // -> este texto sale DESPUES del texto de la lambda!!!
    println("$lambda6")

}

fun funcionesComunesCollections() {

    val listaPersonas = listOf<Person>(
        Person(name = "Nacho", age = 38, ciudad = "Madrid"),
        Person(name = "Reme", age = 37, ciudad = "Valencia"),
        Person(name = "Jose", age = 26, ciudad = "Valladolid"),
        Person(name = "Marta", age = 48, ciudad = "Zaragoza"),
        Person(name = "Pablo", age = 26, ciudad = "Cuenca")
    )

    // Map
    println("Map ages: ${listaPersonas.map { it.age }}")

    // Maxby
    println("Older person: ${listaPersonas.maxBy { it.age }?.name}")


    // Filter
    println("People from Valencia: ${listaPersonas.filter { it.ciudad == "Valencia" }}")

    // Any/All/None
    println("Any people is older than 40: ${listaPersonas.any { it.age > 40 }}")
    println("None people is older than 40: ${listaPersonas.none { it.age > 40 }}")
    println("All people is older than 40: ${listaPersonas.all { it.age > 40 }}")

    // Partition
    val particion: Pair<List<Person>, List<Person>> = listaPersonas.partition { it.age > 30 }
    println("Split list by age (30): ")
    println("     list1: ${particion.first}")
    println("     list2: ${particion.second}")

    // Group by
    val groups: Map<Int, List<Person>> = listaPersonas.groupBy { it.age }
    println("Group by age: ")
    for ((key, value) in groups) {
        println("key=$key, value=$value")
    }

    // Associate
    val mapa: Map<Char, Int> = listaPersonas.associate { it.name.get(0) to it.age * 10 }
    println("Mapa: ")
    for ((key, value) in mapa) {
        println("key=$key, value=$value")
    }

    // Flatmap
    // - combina map y luego flatten
    // map -> paso de un nombre a una lista de letras (es la funcion que le pasas al flatmap)
    // flatten -> simepre aplana, pasa de una lista de listas, a una sola
    val namesList: List<String> = listOf<String>("Pepe", "Juan", "Ana", "Mario")
    val lista: List<Char> = namesList.flatMap { name -> name.toList() }
    println("List of letters: $lista")

}

fun functionTypes() {

    // Lambda en una variable
    val lambdaSum: (Int, Int) -> Int = { x : Int, y : Int -> x + y }
    val lambdaIsEven: (Int) -> Boolean = { x : Int -> x % 2 == 0 }

    // Puede aplicarse directamente
    println("Sum 2 numbers: 5 + 7 = ${lambdaSum(5, 7)}")

    // Puede pasarse como argumento
    val listaNumeros: List<Int> = listOf<Int>(1,3,5,7,9)
    println("Any even number in list $listaNumeros?... ${listaNumeros.any(lambdaIsEven)}")

}

fun memberReferences() {

    // Como en Java
    val listaPersonas = listOf<Person>(
        Person(name = "Nacho", age = 38, ciudad = "Madrid"),
        Person(name = "Reme", age = 37, ciudad = "Valencia"),
        Person(name = "Jose", age = 26, ciudad = "Valladolid"),
        Person(name = "Marta", age = 48, ciudad = "Zaragoza"),
        Person(name = "Pablo", age = 26, ciudad = "Cuenca")
    )
    println("Oldest person: ${listaPersonas.maxBy(Person::age)}")

    // Function reference: giarda una referencia a una funcion en una variable
    fun isEven (x : Int) : Boolean = x % 2 == 0
    //val miFuncionIsEven = isEven -> error compilacion: pueden guardarse lambdas en vars pero no funciones

    // Con los :: (function reference) sí se puede
    val miFuncionIsEven = ::isEven
    println("Is even the number: 5?... ${miFuncionIsEven(5)}")

    // Luego pueden usarse también como parámetros
    val listaNumeros: List<Int> = listOf<Int>(1,3,5,7,9)
    println("Any even number in list $listaNumeros?... ${listaNumeros.any(::isEven)}")


}

fun boundUnboundReferences() {

    class Numero (val name : String, val value : Int) {
        fun isGreaterThan(maxValue : Int) : Boolean = value > maxValue
    }

    // Unbound reference: el type de la lambda necesita recibir la clase Numero, no está atada a una instancia concreta
    val isGreaterLambda1: (Numero, Int) -> Boolean = Numero::isGreaterThan // Lambda: { numero: Numero, maxValue: Int -> numero.isGreaterThan(maxValue) }
    val miNumeroCinco : Numero = Numero(name = "cinco", value = 5)
    isGreaterLambda1(miNumeroCinco, 6) // y aqui hay que pasar el objeto como parámetro

    // Bound reference: el type de la lambda NO lleva la clase Numero, sino que está asociada ya a una instancia concreta de esa clase
    val miNumeroSiete : Numero = Numero(name = "siete", value = 7)
    val isGreaterLambda2 : (Int) -> Boolean = miNumeroSiete::isGreaterThan // Lambda: { maxValue: Int -> miNumeroSiete.isGreaterThan(maxValue) }
    isGreaterLambda2(6) // y aqui NO hay que pasar el objeto como parámetro

}
