package ch.grze.daggergrip

import ch.grze.daggergripcommons.InjectInActivityMethod
import ch.grze.daggergripcommons.InjectionsMap

class DaggerGrip(application: DaggerGripApplication,
                 activityInjectionsMap: InjectionsMap<InjectInActivityMethod>) {

    private val activityLifecycleManager = ActivityLifecycleManager(activityInjectionsMap)

    init {
        application.registerActivityLifecycleCallbacks(activityLifecycleManager)
    }
}