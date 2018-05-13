package ch.grze.daggergripprocessor

import ch.grze.daggergripcommons.InjectInActivity
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.KModifier.ABSTRACT
import java.io.File
import javax.lang.model.element.Element

class InjectInActivityAnnotationProcessor : AnnotationProcessor() {

    companion object {
        const val MODULE_CLASS_NAME = "ActivitiesModule"
        const val INJECTIONS_CLASS_NAME = "ActivitiyInjections"
    }

    override fun getAnnotation() = InjectInActivity::class

    override fun process(elements: List<Element>) {
        generateModule(elements)
        generateInjectionsMap(elements)
    }

    private fun generateInjectionsMap(elements: List<Element>) {
        val typeBuilder = TypeSpec.classBuilder(INJECTIONS_CLASS_NAME)
        val fileBuilder = FileSpec.builder(PACKAGE_NAME, INJECTIONS_CLASS_NAME)

        typeBuilder.apply {
            addModifiers(ABSTRACT)
            addAnnotation(DaggerClasses.MODULE)

            elements.forEach {
                val bindClass = ClassName.bestGuess(it.asType().asTypeName().toString())

                addFunction(
                    FunSpec.builder("contributes${bindClass.simpleName()}").addAnnotation(
                        DaggerClasses.CONTRIBUTES_ANDROID_INJECTOR
                    ).addModifiers(ABSTRACT).returns(bindClass).build()
                )
            }
        }

        fileBuilder.apply {
            addType(typeBuilder.build())

            build().writeTo(File(getPath()))
        }
    }

    private fun generateModule(elements: List<Element>) {
        val typeBuilder = TypeSpec.classBuilder(MODULE_CLASS_NAME)
        val fileBuilder = FileSpec.builder(PACKAGE_NAME, MODULE_CLASS_NAME)

        typeBuilder.apply {
            addModifiers(ABSTRACT)
            addAnnotation(DaggerClasses.MODULE)

            elements.forEach {
                val bindClass = ClassName.bestGuess(it.asType().asTypeName().toString())

                addFunction(
                    FunSpec.builder("contributes${bindClass.simpleName()}").addAnnotation(
                        DaggerClasses.CONTRIBUTES_ANDROID_INJECTOR
                    ).addModifiers(ABSTRACT).returns(bindClass).build()
                )
            }
        }

        fileBuilder.apply {
            addType(typeBuilder.build())

            build().writeTo(File(getPath()))
        }
    }
}