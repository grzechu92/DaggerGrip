package ch.grze.daggergripprocessor

import ch.grze.daggergripcommons.InjectInActivity
import ch.grze.daggergripcommons.InjectInActivityMethod
import ch.grze.daggergripcommons.InjectionsMap
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.KModifier.ABSTRACT
import com.squareup.kotlinpoet.KModifier.OVERRIDE
import javax.lang.model.element.Element
import kotlin.reflect.KClass

class InjectInActivityAnnotationProcessor : AnnotationProcessor() {

    companion object {
        const val MODULE_CLASS_NAME = "ActivitiesModule"
        const val INJECTIONS_CLASS_NAME = "ActivityInjectionsMap"
    }

    override fun getAnnotation() = InjectInActivity::class

    override fun process(elements: List<Element>) = listOf(
        generateModuleFile(elements),
        generateInjectionsMapFile(elements)
    )

    private fun generateInjectionsMapFile(elements: List<Element>): FileSpec {
        val genericInterface = ParameterizedTypeName.get(InjectionsMap::class, InjectInActivityMethod::class)
        val classOfAny = ParameterizedTypeName.get(KClass::class, Any::class)
        val propertyType = ParameterizedTypeName.get(Map::class.asTypeName(), classOfAny, InjectInActivityMethod::class.asTypeName())

        val typeBuilder = TypeSpec.classBuilder(INJECTIONS_CLASS_NAME).apply {
            addSuperinterface(genericInterface)
        }

        val injectionsBuilder = PropertySpec.builder("injections", propertyType, OVERRIDE)
        val assignments: MutableList<CodeBlock> = mutableListOf()

        elements
            .map { elementAsClassName(it) to it.getAnnotation(getAnnotation().java).activityMethod }
            .map { (className, injectMethod) ->
                assignments.add(CodeBlock.of("%T::class to %T.%L", className, InjectInActivityMethod::class, injectMethod))
            }

        injectionsBuilder
            .initializer(CodeBlock.of("mapOf(${assignments.joinToString(",")}) as %T", propertyType))
            .build()
            .let {
                typeBuilder.addProperty(it)
            }

        return getFile(INJECTIONS_CLASS_NAME)
            .apply { addType(typeBuilder.build()) }
            .build()
    }

    private fun generateModuleFile(elements: List<Element>): FileSpec {
        val typeBuilder = TypeSpec.classBuilder(MODULE_CLASS_NAME).apply {
            addModifiers(ABSTRACT)
            addAnnotation(DaggerClasses.MODULE)
        }

        elements
            .map { elementAsClassName(it) }
            .map {
                FunSpec.builder("contributes${it.simpleName()}")
                    .addAnnotation(DaggerClasses.CONTRIBUTES_ANDROID_INJECTOR)
                    .addModifiers(ABSTRACT)
                    .returns(it)
                    .build()
                    .let {
                        typeBuilder.addFunction(it)
                    }
            }

        return getFile(MODULE_CLASS_NAME)
            .apply { addType(typeBuilder.build()) }
            .build()
    }
}