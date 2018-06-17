package ch.grze.daggergripcommons

import kotlin.reflect.KClass

interface InjectionsMap<T> {
    val injections: Map<KClass<Any>, T>
}