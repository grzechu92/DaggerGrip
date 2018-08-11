package daggergrip

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import daggergrip_.ActivitiesModule
import daggergrip_.BindsModule

@Module(
    includes = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivitiesModule::class,
        BindsModule::class
    ])
interface DaggerGripModule