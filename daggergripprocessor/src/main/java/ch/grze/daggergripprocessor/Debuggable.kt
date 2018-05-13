package ch.grze.daggergripprocessor

import javax.annotation.processing.ProcessingEnvironment
import javax.tools.Diagnostic.Kind

interface Debuggable {
    val environment: ProcessingEnvironment

    fun println(something: Any?) {
        environment.messager.printMessage(Kind.NOTE, "=======> $something")
    }
}