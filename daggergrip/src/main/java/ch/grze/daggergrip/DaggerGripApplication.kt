package ch.grze.daggergrip

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.support.v4.app.Fragment
import ch.grze.daggergripcommons.ActivityInjectionsMapProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

interface DaggerGripApplication : HasActivityInjector, HasFragmentInjector, HasSupportFragmentInjector {
    companion object {
        private lateinit var activityInjector: DispatchingAndroidInjector<Activity>
        private lateinit var fragmentInjector: DispatchingAndroidInjector<android.app.Fragment>
        private lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
        private lateinit var activityInjectionsMap: ActivityInjectionsMapProvider
    }

    override fun activityInjector() = activityInjector

    override fun fragmentInjector() = fragmentInjector

    override fun supportFragmentInjector() = supportFragmentInjector

    @Inject
    fun setActivityDispatchingAndroidInjector(injector: DispatchingAndroidInjector<Activity>) {
        activityInjector = injector
    }

    @Inject
    fun setFragmentDispatchingAndroidInjector(injector: DispatchingAndroidInjector<android.app.Fragment>) {
        fragmentInjector = injector
    }

    @Inject
    fun setSupportFragmentDispatchingAndroidInjector(injector: DispatchingAndroidInjector<Fragment>) {
        supportFragmentInjector = injector
    }

    @Inject
    fun setActivityInjectionsMap(map: ActivityInjectionsMapProvider) {
        activityInjectionsMap = map
    }

    fun activityInjectionsMap() = activityInjectionsMap

    fun registerActivityLifecycleCallbacks(callback: ActivityLifecycleCallbacks)
}