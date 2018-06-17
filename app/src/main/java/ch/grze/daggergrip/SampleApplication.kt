package ch.grze.daggergrip

import android.app.Application
import ch.grze.daggergrip.di.DaggerAppComponent
import daggergrip.ActivityInjectionsMap

class SampleApplication : Application(), DaggerGripApplication {

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .build()
            .inject(this)

        DaggerGrip(this, ActivityInjectionsMap())
    }
}