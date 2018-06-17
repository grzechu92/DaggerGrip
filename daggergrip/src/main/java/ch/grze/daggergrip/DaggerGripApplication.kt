package ch.grze.daggergrip

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import javax.inject.Inject

interface DaggerGripApplication : HasActivityInjector, HasFragmentInjector {
    companion object {
        private var activityInjector: DispatchingAndroidInjector<Activity>? = null
        private var fragmentInjector: DispatchingAndroidInjector<Fragment>? = null
    }

    @Inject
    fun setActivityDispatchingAndroidInjector(injector: DispatchingAndroidInjector<Activity>) {
        activityInjector = injector
    }

    @Inject
    fun setFragmentDispatchingAndroidInjector(injector: DispatchingAndroidInjector<Fragment>) {
        fragmentInjector = injector
    }

    override fun activityInjector() = activityInjector!!

    override fun fragmentInjector() = fragmentInjector!!

    fun registerActivityLifecycleCallbacks(callback: ActivityLifecycleCallbacks)
}