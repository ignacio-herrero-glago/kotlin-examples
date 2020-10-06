package main.kotlin

/**
 * Funcion principal
 */
fun main() {

    librayInlineFunctions()

    whyInlines()

}



/************************************************************************************************************/

/**
 * Inline functions son funciones que cuando se utilizan, el compilador inserta su body en lugar de invocarlas y añadirlas a la pila de llamadas
 * No deberían usarse salvo que sean pequeñas, porque si no el código de la aplicación crecerá mucho
 */

fun librayInlineFunctions() {

    // run: ejecuta el bloque de codigo (lambda) y devuelve la última expresion como resultado
    val expresion = run {
        println("ejecutando run...")
        "TEXTO"
    }
    println("Expression: $expresion")

    // let: ejecuta un bloque sólo si el receiver no es nulo
    val email : String? = "hola@gmail.com"
    fun sendEmail(email : String?) = "email sent"
    // A) comprobar con if
    if (email != null) println(sendEmail(email))
    //B) use let
    email?.let { println(sendEmail(it)) } // it se refiere a la variable email

    // talkeif: devuelde el receiver object si cumple el predicado:; si no, null
    val cadena = "texto"
    val result = cadena.takeIf { it.length > 6 }
    println("Cadena: $result")

    // repeat: repite un bloque N veces
    repeat(3) {
        println("Hola->")
    }

}

fun runTradicional(f: () -> Unit) = f()
inline fun runInline(f: () -> Unit) = f()

inline fun Int.miTakeIfInline(predicado : (Int) -> Boolean) : Int? =
    if (predicado(this)) this else null

fun whyInlines() {

    // Creo una funcion run tradicional (recibe una funcion y la ejecuta)
    runTradicional { println("Texto") } // para la lambda, se creará una nueva clase anónima -> peor rendimiento

    // Con las funciones inline, el compilador lo optimiza
    runInline { println("Texto") } // aqui se inserta el cuerpo de la funcion inline en lugar de llamarla con la lambda de parámetro (es decir, no se ejecuta runInline(println("Texto")), sino sólo println("Texto"))

    // Otro ejemplo
    val numero1 = 6
    val res1 = numero1.miTakeIfInline { it > 5 } // Al llegar aqui, no se invoca a miTakeIfInline({ it > 5 }), sino que se inserta directamente el cuerpo de la funcion => if (it > 5) this else null
    println("miTakeIfInline: $res1")

}
