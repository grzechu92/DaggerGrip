package ch.grze.daggergripprocessor

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class Processor : AbstractProcessor(), Debuggable {

    private val processors: List<AnnotationProcessor> = listOf(
        BindsToAnnotationProcessor(),
        InjectInActivityAnnotationProcessor()
    )

    private val annotatedClasses: MutableMap<ClassName, MutableList<Element>> = mutableMapOf()

    override fun getEnvironment() = processingEnv

    override fun process(types: MutableSet<out TypeElement>?, env: RoundEnvironment?): Boolean {
        types?.forEach { annotation ->
            env?.getElementsAnnotatedWith(annotation)?.forEach { element ->
                element.annotationMirrors
                    .filter { it.annotationType.asElement().simpleName.contentEquals(annotation.simpleName) }
                    .forEach {
                        annotatedClasses
                            .getOrPut(annotation.asClassName()) { mutableListOf() }
                            .add(element)
                    }
            }
        }

        processors.forEach { processor ->
            ClassName.bestGuess(processor.getAnnotation().qualifiedName!!).let {
                annotatedClasses[it]?.let {
                    processor.env = processingEnv
                    processor.process(it)
                }
            }
        }

        return true
    }

    override fun getSupportedAnnotationTypes() = processors
        .map { it.getAnnotation().qualifiedName }
        .toMutableSet()

    override fun getSupportedSourceVersion() = SourceVersion.latest()
}