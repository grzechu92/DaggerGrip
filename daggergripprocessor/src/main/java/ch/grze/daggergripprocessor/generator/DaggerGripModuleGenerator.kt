package ch.grze.daggergripprocessor.generator

import ch.grze.daggergripprocessor.Classes
import ch.grze.daggergripprocessor.Classes.Companion.ACTIVITIES_MODULE
import ch.grze.daggergripprocessor.Classes.Companion.ANDROID_INJECTION_MODULE
import ch.grze.daggergripprocessor.Classes.Companion.ANDROID_SUPPORT_INJECTION_MODULE
import ch.grze.daggergripprocessor.Classes.Companion.BINDS_MODULE
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec

class DaggerGripModuleGenerator : Generator() {

    companion object {
        const val CLASS_NAME = "DaggerGripModule"
    }

    override fun generate(): List<FileSpec> {
        val annotationSpec = AnnotationSpec.builder(Classes.MODULE).apply {

            listOf(ANDROID_INJECTION_MODULE, ANDROID_SUPPORT_INJECTION_MODULE, ACTIVITIES_MODULE, BINDS_MODULE)
                .toTypedArray()
                .let { it to (0 until it.size).map { "%T::class" } }
                .let { CodeBlock.of("includes = ${it.second}", *it.first) }
                .let { addMember(it) }
        }

        val typeBuilder = TypeSpec.interfaceBuilder(CLASS_NAME).apply {
            addAnnotation(annotationSpec.build())
        }

        return getFile(CLASS_NAME)
            .apply { addType(typeBuilder.build()) }
            .build()
            .let { listOf(it) }
    }
}