package main.kotlin.beans

/**
 * Los beans se crean de esta forma
 * - los atributos de Java se llaman mienbros en Kotlin
 * - se indican junto con la clase (importante el val/var, según se quiera poder editar)
 * - se acceder a ellos desde fuera como obj.miembro
 */
class Person(val name : String, val age : Int, var ciudad : String, var hijos : Int = 0) {

    override fun toString(): String {
        return "Person(name='$name', age=$age, ciudad='$ciudad', hijos=$hijos)"
    }
}
