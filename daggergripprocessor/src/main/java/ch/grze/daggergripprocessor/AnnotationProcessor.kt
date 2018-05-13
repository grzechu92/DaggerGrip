package ch.grze.daggergripprocessor

import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import kotlin.reflect.KClass

abstract class AnnotationProcessor : Debuggable {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
        const val PACKAGE_NAME = "daggergrip"
    }

    lateinit var env: ProcessingEnvironment

    override fun getEnvironment() = env

    abstract fun process(elements: List<Element>)

    abstract fun getAnnotation(): KClass<out Annotation>

    protected fun getPath() = env
        .options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
        ?.replace("build/generated/source/kaptKotlin/debug", "src/main/java")
}