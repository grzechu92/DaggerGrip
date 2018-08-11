package ch.grze.daggergripprocessor

import ch.grze.daggergripcommons.BindsTo
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.KModifier.ABSTRACT
import javax.lang.model.element.Element

class BindsToAnnotationProcessor : AnnotationProcessor() {

    companion object {
        const val CLASS_NAME = "BindsModule"
    }

    override fun getAnnotation() = BindsTo::class

    override fun process(elements: List<Element>): List<FileSpec> {
        val typeBuilder = TypeSpec.classBuilder(CLASS_NAME).apply {
            addModifiers(KModifier.ABSTRACT)
            addAnnotation(DaggerClasses.MODULE)
        }

        elements.map { element ->
            element.annotationMirrors
                .filter { it.annotationType.asElement().simpleName.contentEquals(getAnnotation().simpleName) }
                .flatMap { it.elementValues.entries }
                .map { ClassName.bestGuess(it.value.value.toString()) }
                .forEach {
                    FunSpec.builder("binds${it.simpleName}To${element.simpleName}")
                        .addAnnotation(DaggerClasses.BINDS)
                        .addModifiers(ABSTRACT)
                        .addParameter("binds", element.asType().asTypeName())
                        .returns(it)
                        .build()
                        .let {
                            typeBuilder.addFunction(it)
                        }
                }
        }

        return getFile(CLASS_NAME)
            .apply { addType(typeBuilder.build()) }
            .build()
            .let { listOf(it) }
    }
}