package ch.grze.daggergripprocessor

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.asTypeName
import javax.lang.model.element.Element

interface Component {
    fun getFile(fileName: String, packageName: String = Processor.PACKAGE_NAME) =
        FileSpec.builder(packageName, fileName)

    fun elementAsClassName(element: Element) =
        ClassName.bestGuess(element.asType().asTypeName().toString())
}