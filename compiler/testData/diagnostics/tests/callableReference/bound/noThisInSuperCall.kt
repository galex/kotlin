// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
open class A(val x: Any)

class B : A(<!NO_THIS!>this<!>::class)
