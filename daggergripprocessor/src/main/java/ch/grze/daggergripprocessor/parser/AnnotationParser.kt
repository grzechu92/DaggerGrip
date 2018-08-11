package ch.grze.daggergripprocessor.parser

import ch.grze.daggergripprocessor.Component
import ch.grze.daggergripprocessor.Debuggable
import com.squareup.kotlinpoet.FileSpec
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import kotlin.reflect.KClass

abstract class AnnotationParser : Debuggable, Component {

    override lateinit var environment: ProcessingEnvironment

    abstract fun parse(elements: List<Element>): List<FileSpec>

    abstract fun getAnnotation(): KClass<out Annotation>
}