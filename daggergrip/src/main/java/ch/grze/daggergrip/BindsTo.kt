package ch.grze.daggergrip

import kotlin.reflect.KClass

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class BindsTo(
    val clazz: KClass<*>
)