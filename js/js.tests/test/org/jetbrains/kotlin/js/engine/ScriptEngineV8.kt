/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.js.engine

class ScriptEngineV8(doTrace: Boolean = true) : ProcessBasedScriptEngine(System.getProperty("javascript.engine.path.V8"), doTrace)

fun main() {
//    System.setProperty("javascript.engine.path.V8", "<path-to-d8>")
    val vm = ScriptEngineV8(doTrace = false)
    println("Welcome!")
    while (true) {
        print("> ")
        val t = readLine()
        try {
            println(vm.eval(t!!))
        } catch (e: Throwable) {
            System.err.println(e)
        }
    }
}
