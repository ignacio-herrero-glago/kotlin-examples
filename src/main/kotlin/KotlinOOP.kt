package main.kotlin

/**
 * Funcion principal
 */
fun main() {

    val clase1 = Clase1("texto1")
    println("The class1 att is...${clase1.attr1}")

    val clase3 = Clase3(attr1 = "hello ", attr2 = "world")
    println("The class3 att is...${clase3.attr1}")

    println("The enum1 values are... ${Enumeracion1.VALOR1}, ${Enumeracion1.VALOR2}")
    print("The enum2 value sum is => ${Enumeracion2.VALUE3.sum()}")

    val claseData1 = ClaseData(value1 = "hola", value2 = 6)
    println("The clase data is...${claseData1.toString()}")

    val clase1_1 = Clase1("texto1")
    val clase1_2 = Clase1("texto1")
    val claseData1_1 = ClaseData(value1 = "texto", value2 = 1)
    val claseData1_2 = ClaseData(value1 = "texto", value2 = 1)

    // En la primera, al no ser clases data, se compara objetos por referencia, y son distintos
    println("clase1_1 - clase1_2 equals? ${clase1_1 == clase1_2}")
    // En la segunda, al SI ser clases data, se compara objetos por valores (se proporciona el método equals implementado que compara las propiedades)
    println("claseData1_1 - claseData1_2 equals? ${claseData1_1 == claseData1_2}")
    // Para comparación por referencia, usar '==='
    println("claseData1_1 - claseData1_2 equals? ${claseData1_1 === claseData1_2}")

    // Las clases selladas contienen toda la información en el mismo fichero
    val claseSellada = Sum(valorIzda = Num(5), valorDcha = Num(6))
    println("Selaed class eval: ${eval(claseSellada)}")

    // Objects: no pueden instanciarse, las funciones de los objetcs se invocan como las static de Java
    Objeto.funcion()

    // Clase con companion object: las funciones del companion se invocan como estáticas a la clase externa, pues ese companion es un singleton
    val claseConObject = ClaseConObjeto(valor1 = "hola")
    println("claseConObject. ${claseConObject.valor1}, ${ClaseConObjeto.funcion()}")

}

/************************************************************************************************************/

/* Ambias declaraciones son equivalentes */
class Clase1 (val attr1 : String)

class Clase2 (attr1 : String) {
    val attr1 : String
    init {
        this.attr1 = attr1
    }
}

class Clase3 (val attr1 : String) {
    // Secondary constructor
    constructor(attr1: String, attr2 : String) : this(attr1+attr2)
}

/* Enumeracion */
enum class Enumeracion1 { VALOR1, VALOR2 }

enum class Enumeracion2 (val num1 : Int, val num2 : Int) {

    VALUE1(1,1),    VALUE2(2,2),
    VALUE3(3,3)

    ; // Separa valores de funciones

    fun sum() = num1 + num2

}

/* Data class */
// Genera metodos extra: toString, hasCode, equals, copy, componentX
data class ClaseData(val value1 : String,val  value2 : Int) {
    // value3 no se incluirá en toStirng, equals... pues no se pasa como argumento en el constructor primario
    val value3 : String? = null
}

/** STATIC METHODS
 * - No existen en Kotlin
 * - Se puede hacer lo equivalente con: methods top level (fuera de una clase), dentro de un object o dentro de un companion object
 */

/* Objects: son singletons, no permiten constructores ya que solo hay una instancia */
object Objeto {
    fun funcion() {
        println("Texto de objeto singleton")
    }
}

/* Companion object: son objects dentro de clases */
class ClaseConObjeto(val valor1 : String) {
    companion object {
        fun funcion() = 1
    }
}