package main.kotlin

sealed class Exp
//interface Exp
class Num(val valor : Int) : Exp()
class Sum(val valorIzda : Exp, val valorDcha : Exp) : Exp()

// Si no añado un else, no compila; el 'when' se queja de que no es exhaustivo pues podria implementarse la interfax Exp en otro lado
// => si añadimos 'sealed', evitamos ese else (ojo, ya Exp tiene que ser una clase)
fun eval(expr : Exp) : Int = when(expr) {
    is Num -> expr.valor
    is Sum -> eval(expr.valorIzda) + eval(expr.valorDcha)
}