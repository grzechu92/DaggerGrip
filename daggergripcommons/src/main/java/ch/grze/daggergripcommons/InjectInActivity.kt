package ch.grze.daggergripcommons

import ch.grze.daggergripcommons.InjectInActivityMethod.ON_CREATE

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class InjectInActivity(val activityMethod: InjectInActivityMethod = ON_CREATE)