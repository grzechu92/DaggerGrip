package daggergrip

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class, ActivitiesModule::class, BindsModule::class])
interface DaggerGripModule
