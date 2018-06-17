package ch.grze.daggergripcommons

import kotlin.reflect.KClass

interface ActivityInjectionsMapProvider {
    val injections: Map<KClass<Any>, InjectInActivityMethod>
}