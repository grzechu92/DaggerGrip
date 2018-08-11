package ch.grze.daggergripprocessor.parser

import ch.grze.daggergripprocessor.Debuggable
import ch.grze.daggergripprocessor.Processor
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.asTypeName
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import kotlin.reflect.KClass

abstract class AnnotationParser : Debuggable {

    override lateinit var environment: ProcessingEnvironment

    abstract fun parse(elements: List<Element>): List<FileSpec>

    abstract fun getAnnotation(): KClass<out Annotation>

    protected fun getFile(fileName: String, packageName: String = Processor.PACKAGE_NAME) =
        FileSpec.builder(packageName, fileName)

    protected fun elementAsClassName(element: Element) =
        ClassName.bestGuess(element.asType().asTypeName().toString())
}