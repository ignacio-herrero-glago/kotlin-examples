package main.kotlin

import kotlin.random.Random

/**
 * Funcion principal
 */
fun main() {

    collectionsAndSequences()

    yields()

}


/************************************************************************************************************/

/**
 * Sequences en Kotlin son como streams en Java
 * COLLECTIONS                                   VS             SEQUENCES
 * cada paso de una cadena se evalua (eager)                    funcionan como los streams de Java (lazy)
 * generan una coleccion en cada paso                           no
 * las operaciones intermedias son fun inline                   no son inline (no puede insertarse su cuerpo y ejecutarlo, pues se ejecutan después bajo demanda)
 * las operaciones finales son inline                           idem
 * mal rendimiento encadenando muchas ops                       mejor
 */

fun m(i: Int): Int {
    print("m$i ")
    return i
}

fun f(i: Int): Boolean {
    print("f$i ")
    return i % 2 == 0
}


fun collectionsAndSequences() {

    // Una lista en cada paso (3 listas intermedias en total)
    val listaNumeros = listOf(1, 2, -3) // [1,2,-3]
    val cuadradoMaximo = listaNumeros
        .map { it * it }                        // [1,4,9]
        .filter { it % 2 == 1 }                 // [1,9]
        .max()
    println("Max odd square: $cuadradoMaximo")

    // Para convertirlo en secuencias(streams) solo hay que añadir una linea delante
    val cuadradoMaximoSeq = listaNumeros
        .asSequence()
        .map { it * it }                        // intermediate operation: son las que devuelven otra secuencia
        .filter { it % 2 == 1 }                 // intermediate operation
        .max()                                  // final operation: devuelven otra cosa, hace que se ejecute el resto
    println("Max odd square (sequence): $cuadradoMaximoSeq")

    // Ejemplo de eager vs lazy
    val list = listOf(1, 2, 3, 4)

    // Se evalua de izda a derecha
    print("Collection chain (eager, map-filter): ")
    list.map(::m).filter(::f).toList()
    println()

    // Se evalua de derecha a izda
    print("Collection chain (lazy, map-filter): ")
    list.asSequence().map(::m).filter(::f).toList()
    println()

    // Cambiando el orden de las operaciones
    print("Collection chain (lazy, filter-map): ")
    list.asSequence().filter(::f).map(::m).toList() //?
    println()

    // Podemos generar secuencias
    // "generateSequence": devuelve una secuencia. Invoca a la lambda para generar el siguiente valor hasta que devuelve null
    // "sequence": construye una secuencia construyendo valores de forma lazy. El parámetro que recibe es un bloque que se puede suspender (ver yield)
    val miSecuenciaRandom = generateSequence {
        Random.nextInt(100)
    }
    val miSecuenciaNumerosSeguidosDesde = generateSequence(7) {
        // Esta funcion indica como generar el siguiente
            next ->
        print(".")
        next + 1
    }
    // Es infinita si la llamamos
    print("miSecuenciaRandom: ")
    println(miSecuenciaRandom.take(5).toList())
    // Empezará por 7
    print("miSecuenciaNumerosSeguidosDesde: ")
    println(miSecuenciaNumerosSeguidosDesde.take(5).toList())


}

/**
 * Yield es unn concepto que implica ahorro de recursos. Se intentará explicar paso a paso con un ejemplo
 */
fun yields() {

    // 1. Iterable: es un tipo de dato que permite que se itere sobre el
    // -> lo malo de una lista es que si es enorme y hay que procesarla, es necesario tenerla completa en memoria
    val datoIterable = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("Iterable: ")
    for (i in datoIterable)
        print(i)
    println()

    // 2. Funcion: es una lista de comandos que solo se ejecutan cuando se invoca a la funcion
    println("Funcion: ")
    fun f() {
        println("Esto es un comando")
        println("Esto es un otro...")
    }
    f()

    // 3. Generador: es un tipo de iterable pensado para un solo uso
    // La idea es que un elemento de un iterable una vez se procesa, ya no se necesita
    // La idea es que no se almacene el iterable completo en memoria, sino que se produce cada elemento y se desecha
    // A la hora de usarlo en un for, se comporta igual que un iterable que está almacenado completo en memoria
    var i = 1
    val datoGenerado = generateSequence {
        (i++).takeIf { i <= 11 }
    }
    println("Generador: ")
    for (i in datoGenerado)
        print(i)
    println()

    // 4. Yield: esta palabra actúa de la siguiente forma
    // - reemplaza la palabra return en una función para crear un generador
    // - esto permite usarla en un iterable tradicional para obtener los beneficios de generadores y funciones
    // - la funcion se ejecuta hasta yield, y ahí queda pausada hasta que vuelve a invocarse, en cuyo caso continúa desde ese punto
    // "You can think of yield() as "return, and next time start from where you stopped"
    fun funcionConYield() = sequence {
        var x: Int = 1
        // Aqui vamos devolviendo i y suspendemos la ejecucion; si se invoca de nuevo, se continua desde el yield
        while (true) {
            yield(x)
            x++
        }
    }

    // Si invocamos la función vemos que devuelve una secuencia, que luego podemos iterar
    println("Yield numeros: ")
    print(funcionConYield().take(10).toList())
    println()

    // Otro ejemplo con varios yields (sería como una máquina de estados)
    fun comoEstoy() = sequence<String> {
        var i = 1
        yield("$i). Recien levantado!!")
        i++
        yield("$i). Desayunando...")
        i++
        yield("$i). Comiendo")
        i++
        yield("$i). Merendando")
        i++
        yield("$i). Cenando")
        i++
        yield("$i). Buenas noches!!")
        i++
    }

    println("Yield estados: ")
    val misEstados = comoEstoy()
    for (estado in misEstados)
        println(estado)

    // Se pueden devolver diferentes elementos en yield
    fun secuenciaNumeros() = sequence {
        // La primera vez devovemos 1
        println("   (primera llamada...)")
        yield(1)
        // Usamos yieldAll para devolver varios elementos como secuencia
        println("   (segunda llamada...)")
        yieldAll(2..9)
        // No se ejecutará con la llamada que haremos
        println("   (tercera llamada...)")
        yieldAll(listOf(10, 20, 30))
    }
    println("Yield secuenciaNumeros: ")
    println(secuenciaNumeros().map { it * it }.filter { it > 30 }.take(3).toList())

    // Fibonacci como secuencia
    val secuenciaFibonacci = sequence<Int> {
        var a = 0
        var b = 1
        // f(0) = 0
        yield(a)
        // f(1) = 1
        yield(b)

        while (true) {
            // f(n) = f(n-1) + f(n-2)
            val c = b + a
            yield(c)
            a = b
            b = c
        }
    }
    println("secuenciaFibonacci: ")
    println(secuenciaFibonacci.take(10).toList())

    // 5. Courutine: es una función con uno o más puntos de suspensión
    // En Kotlin, las courutines se definen con la palabra suspend
    // Ejemplo yield: "public abstract suspend fun yield(value: T)"
    // Solo se pueden llamar desde otra courutine/suspend function
    suspend fun miCourutine(paramtero: String) {
        val cadena1 = "saludar"
        val cadena2 = "hola"
    }

}

