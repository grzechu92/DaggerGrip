package ch.grze.daggergripprocessor

import javax.annotation.processing.ProcessingEnvironment
import javax.tools.Diagnostic.Kind

interface Debuggable {
    fun getEnvironment(): ProcessingEnvironment

    fun println(something: Any?) {
        getEnvironment().messager.printMessage(Kind.NOTE, "=======> $something")
    }
}