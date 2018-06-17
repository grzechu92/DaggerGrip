package ch.grze.daggergrip

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import ch.grze.daggergripcommons.InjectInActivityMethod
import ch.grze.daggergripcommons.InjectInActivityMethod.*
import ch.grze.daggergripcommons.InjectionsMap
import dagger.android.AndroidInjection

class ActivityLifecycleManager(private val injectionsMap: InjectionsMap<InjectInActivityMethod>) : ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        injectIfMethodMatching(activity, ON_CREATE)
    }

    override fun onActivityStarted(activity: Activity) {
        injectIfMethodMatching(activity, ON_START)
    }

    override fun onActivityResumed(activity: Activity) {
        injectIfMethodMatching(activity, ON_RESUME)
    }

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityDestroyed(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {}

    override fun onActivityStopped(activity: Activity) {}

    private fun injectIfMethodMatching(activity: Activity, method: InjectInActivityMethod) {
        injectionsMap.injections
            .filterKeys { it == activity::class }
            .filterValues { it == method }
            .map { AndroidInjection.inject(activity) }
    }
}