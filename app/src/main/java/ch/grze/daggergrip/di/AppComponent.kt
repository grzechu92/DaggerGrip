package ch.grze.daggergrip.di

import ch.grze.daggergrip.SampleApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import daggergrip.ActivitiesModule
import daggergrip.BindsModule

@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ActivitiesModule::class,
    BindsModule::class
])
interface AppComponent {
    fun inject(application: SampleApplication)
}