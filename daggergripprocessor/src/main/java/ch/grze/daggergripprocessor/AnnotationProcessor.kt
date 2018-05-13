package ch.grze.daggergripprocessor

import com.squareup.kotlinpoet.FileSpec
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import kotlin.reflect.KClass

abstract class AnnotationProcessor : Debuggable {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
        const val PACKAGE_NAME = "daggergrip"
    }

    override lateinit var environment: ProcessingEnvironment

    abstract fun process(elements: List<Element>): List<FileSpec>

    abstract fun getAnnotation(): KClass<out Annotation>

    protected fun getFile(fileName: String, packageName: String = PACKAGE_NAME)
            = FileSpec.builder(packageName, fileName)
}