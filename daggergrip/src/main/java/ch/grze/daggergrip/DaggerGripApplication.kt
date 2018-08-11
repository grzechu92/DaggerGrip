package ch.grze.daggergrip

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.app.Fragment
import ch.grze.daggergripcommons.ActivityInjectionsMapProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import javax.inject.Inject

interface DaggerGripApplication : HasActivityInjector, HasFragmentInjector {
    companion object {
        private lateinit var activityInjector: DispatchingAndroidInjector<Activity>
        private lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
        private lateinit var activityInjectionsMap: ActivityInjectionsMapProvider
    }

    override fun activityInjector() = activityInjector

    override fun fragmentInjector() = fragmentInjector

    @Inject
    fun setActivityDispatchingAndroidInjector(injector: DispatchingAndroidInjector<Activity>) {
        activityInjector = injector
    }

    @Inject
    fun setFragmentDispatchingAndroidInjector(injector: DispatchingAndroidInjector<Fragment>) {
        fragmentInjector = injector
    }

    @Inject
    fun setActivityInjectionsMap(map: ActivityInjectionsMapProvider) {
        activityInjectionsMap = map
    }

    fun activityInjectionsMap() = activityInjectionsMap

    fun registerActivityLifecycleCallbacks(callback: ActivityLifecycleCallbacks)
}