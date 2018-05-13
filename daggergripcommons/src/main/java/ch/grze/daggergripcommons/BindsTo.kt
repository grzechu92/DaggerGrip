package ch.grze.daggergripcommons

import kotlin.reflect.KClass

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class BindsTo(
    val clazz: KClass<*>
)