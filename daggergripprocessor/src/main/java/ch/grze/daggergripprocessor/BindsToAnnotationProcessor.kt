package ch.grze.daggergripprocessor

import ch.grze.daggergripannotations.BindsTo
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.KModifier.ABSTRACT
import java.io.File
import javax.lang.model.element.Element

class BindsToAnnotationProcessor : AnnotationProcessor() {

    companion object {
        const val CLASS_NAME = "BindsModule"
    }

    override fun getAnnotation() = BindsTo::class

    override fun process(elements: List<Element>) {
        val typeBuilder = TypeSpec.classBuilder(CLASS_NAME)
        val fileBuilder = FileSpec.builder(PACKAGE_NAME, CLASS_NAME)

        typeBuilder.apply {
            addModifiers(KModifier.ABSTRACT)
            addAnnotation(DaggerClasses.MODULE)

            elements.forEach { element ->
                element.annotationMirrors
                    .filter { it.annotationType.asElement().simpleName.contentEquals(getAnnotation().simpleName) }
                    .forEach {
                        it.elementValues.forEach { _, clazz ->
                            val bindClass = ClassName.bestGuess(clazz.value.toString())
                            val annotatedClass = element.asType().asTypeName()

                            addFunction(
                                FunSpec.builder("binds${bindClass.simpleName()}To${element.simpleName}")
                                    .addAnnotation(DaggerClasses.BINDS)
                                    .addModifiers(ABSTRACT)
                                    .addParameter("binds", annotatedClass)
                                    .returns(bindClass)
                                    .build()
                            )
                        }
                    }
            }
        }

        fileBuilder.apply {
            addType(typeBuilder.build())

            build().writeTo(File(getPath()))
        }
    }
}