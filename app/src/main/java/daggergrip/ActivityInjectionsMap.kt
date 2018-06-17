package daggergrip

import ch.grze.daggergripcommons.ActivityInjectionsMapProvider
import ch.grze.daggergripcommons.BindsTo
import ch.grze.daggergripcommons.InjectInActivityMethod
import javax.inject.Inject
import kotlin.Any
import kotlin.collections.Map
import kotlin.reflect.KClass

@BindsTo(ActivityInjectionsMapProvider::class)
class ActivityInjectionsMap @Inject constructor() : ActivityInjectionsMapProvider {
    override val injections: Map<KClass<Any>, InjectInActivityMethod> =
            mapOf(ch.grze.daggergrip.MainActivity::class to ch.grze.daggergripcommons.InjectInActivityMethod.ON_CREATE) as Map<KClass<Any>, InjectInActivityMethod>
}
