package ch.grze.daggergrip

class DaggerGrip(application: DaggerGripApplication) {

    private val activityLifecycleManager = ActivityLifecycleManager(application.activityInjectionsMap())

    init {
        application.registerActivityLifecycleCallbacks(activityLifecycleManager)
    }
}