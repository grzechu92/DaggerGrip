package daggergrip

import ch.grze.daggergripcommons.InjectInActivityMethod
import ch.grze.daggergripcommons.InjectionsMap
import kotlin.Any
import kotlin.collections.Map
import kotlin.reflect.KClass

class ActivityInjectionsMap : InjectionsMap<InjectInActivityMethod> {
    override val injections: Map<KClass<Any>, InjectInActivityMethod> =
            mapOf(ch.grze.daggergrip.MainActivity::class to ch.grze.daggergripcommons.InjectInActivityMethod.ON_CREATE) as Map<KClass<Any>, InjectInActivityMethod>
}
