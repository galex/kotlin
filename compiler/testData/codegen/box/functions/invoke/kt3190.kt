// IGNORE_BACKEND: JS_IR
// IGNORE_BACKEND: JS_IR_ES6
//KT-3190 Compiler crash if function called 'invoke' calls a closure
// JS backend does not allow to implement Function{N} interfaces
// IGNORE_IR_DESERIALIZATION_TEST: JS_IR
// ^^^ Source code is not compiled in JS.

fun box(): String {
    val test = Cached<Int,Int>({ it + 2 })
    return if (test(1) == 3) "OK" else "fail"
}

class Cached<K, V>(private val generate: (K)->V): Function1<K, V> {
    val store = HashMap<K, V>()

    // Everything works just fine if 'invoke' method is renamed to, for example, 'get'
    override fun invoke(p1: K) = store.getOrPut(p1) { generate(p1) }
}

//from library
fun <K,V> MutableMap<K,V>.getOrPut(key: K, defaultValue: ()-> V) : V {
    if (this.containsKey(key)) {
        return this.get(key) as V
    } else {
        val answer = defaultValue()
        this.put(key, answer)
        return answer
    }
}
