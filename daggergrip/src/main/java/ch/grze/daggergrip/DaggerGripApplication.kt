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

    override fun activityInjector() = activityInjector

    override fun fragmentInjector() = fragmentInjector

    fun activityInjectionsMap() = activityInjectionsMap

    fun registerActivityLifecycleCallbacks(callback: ActivityLifecycleCallbacks)
}