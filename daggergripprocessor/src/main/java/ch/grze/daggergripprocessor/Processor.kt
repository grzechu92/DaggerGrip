package ch.grze.daggergripprocessor

import ch.grze.daggergripprocessor.generator.DaggerGripModuleGenerator
import ch.grze.daggergripprocessor.generator.Generator
import ch.grze.daggergripprocessor.parser.AnnotationParser
import ch.grze.daggergripprocessor.parser.BindsToParser
import ch.grze.daggergripprocessor.parser.InjectInActivityParser
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class Processor : AbstractProcessor() {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
        const val PACKAGE_NAME = "daggergrip"
    }

    private val generators: List<Generator> = listOf(
        DaggerGripModuleGenerator()
    )

    private val parsers: List<AnnotationParser> = listOf(
        BindsToParser(),
        InjectInActivityParser()
    )

    private val annotatedClasses: MutableMap<ClassName, MutableList<Element>> = mutableMapOf()

    override fun process(types: MutableSet<out TypeElement>?, env: RoundEnvironment?): Boolean {
        types
            ?.map { it to env?.getElementsAnnotatedWith(it) }
            ?.map { (annotation, elements) ->
                elements?.map { element ->
                    element.annotationMirrors
                        .filter { it.annotationType.asElement().simpleName.contentEquals(annotation.simpleName) }
                        .map {
                            annotatedClasses
                                .getOrPut(annotation.asClassName()) { mutableListOf() }
                                .add(element)
                        }
                }
            }

        parsers
            .map { it.apply { environment = processingEnv } }
            .map { it to ClassName.bestGuess(it.getAnnotation().qualifiedName!!) }
            .map { (processor, annotation) ->
                annotatedClasses[annotation]?.let {
                    processor
                        .parse(it)
                        .map { it.writeTo(File(getPath())) }
                }
            }

        generators
            .map { it.apply { environment = processingEnv } }
            .flatMap { it.generate() }
            .map { it.writeTo(File(getPath())) }


        return true
    }

    override fun getSupportedAnnotationTypes() =
        parsers
            .map { it.getAnnotation().qualifiedName }
            .toMutableSet()

    override fun getSupportedSourceVersion() = SourceVersion.latest()

    private fun getPath() =
        processingEnv
            .options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
            ?.replace("build/generated/source/kaptKotlin/debug", "src/main/java")
//            ?.replace("kaptKotlin", "kapt")
}