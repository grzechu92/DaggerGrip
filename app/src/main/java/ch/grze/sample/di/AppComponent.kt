package ch.grze.sample.di

import ch.grze.sample.SampleApplication
import dagger.Component
import daggergrip.DaggerGripModule

@Component(modules = [DaggerGripModule::class])
interface AppComponent {
    fun inject(application: SampleApplication)
}