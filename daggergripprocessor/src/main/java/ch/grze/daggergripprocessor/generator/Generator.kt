package ch.grze.daggergripprocessor.generator

import ch.grze.daggergripprocessor.Component
import ch.grze.daggergripprocessor.Debuggable
import com.squareup.kotlinpoet.FileSpec
import javax.annotation.processing.ProcessingEnvironment

abstract class Generator : Debuggable, Component {

    override lateinit var environment: ProcessingEnvironment

    abstract fun generate(): List<FileSpec>
}

