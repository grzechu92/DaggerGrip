package ch.grze.daggergrip

import android.app.Application
import ch.grze.daggergripcommons.InjectInActivityMethod
import ch.grze.daggergripcommons.InjectionsMap

class DaggerGrip(application: Application,
                 activityInjectionsMap: InjectionsMap<InjectInActivityMethod>) {

    private val activityLifecycleManager = ActivityLifecycleManager(activityInjectionsMap)

    init {
        application.registerActivityLifecycleCallbacks(activityLifecycleManager)
    }
}