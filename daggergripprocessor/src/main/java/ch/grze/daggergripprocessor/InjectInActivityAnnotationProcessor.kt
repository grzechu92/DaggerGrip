package ch.grze.daggergripprocessor

import ch.grze.daggergrip.InjectInActivity
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.KModifier.ABSTRACT
import java.io.File
import javax.lang.model.element.Element

class InjectInActivityAnnotationProcessor : AnnotationProcessor() {

    companion object {
        const val CLASS_NAME = "ActivitiesModule"
    }

    override fun getAnnotation() = InjectInActivity::class

    override fun process(elements: List<Element>) {
        val typeBuilder = TypeSpec.classBuilder(CLASS_NAME)
        val fileBuilder = FileSpec.builder(PACKAGE_NAME, CLASS_NAME)

        typeBuilder.apply {
            addModifiers(KModifier.ABSTRACT)
            addAnnotation(DaggerClasses.MODULE)

            elements.forEach {
                val bindClass = ClassName.bestGuess(it.asType().asTypeName().toString())

                addFunction(
                    FunSpec.builder("contributes${bindClass.simpleName()}")
                        .addAnnotation(DaggerClasses.CONTRIBUTES_ANDROID_INJECTOR)
                        .addModifiers(ABSTRACT)
                        .returns(bindClass)
                        .build()
                )
            }
        }

        fileBuilder.apply {
            addType(typeBuilder.build())

            build().writeTo(File(getPath()))
        }
    }
}