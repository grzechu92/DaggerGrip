package ch.grze.sample

import android.app.Application
import ch.grze.daggergrip.DaggerGrip
import ch.grze.daggergrip.DaggerGripApplication
import ch.grze.sample.di.DaggerAppComponent

class SampleApplication : Application(), DaggerGripApplication {

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .build()
            .inject(this)

        DaggerGrip(this)
    }
}