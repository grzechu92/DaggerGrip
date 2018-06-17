package ch.grze.daggergrip

import android.app.Activity
import android.app.Application
import android.app.Fragment
import ch.grze.daggergrip.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import daggergrip.ActivityInjectionsMap
import javax.inject.Inject

class SampleApplication : Application(), HasActivityInjector, HasFragmentInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().build().inject(this)
        DaggerGrip(this, ActivityInjectionsMap())
    }

    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun fragmentInjector() = fragmentDispatchingAndroidInjector
}