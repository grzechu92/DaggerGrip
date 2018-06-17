package daggergrip

import ch.grze.daggergrip.binds.SampleImplementation
import ch.grze.daggergrip.binds.SampleImplementation2
import ch.grze.daggergrip.binds.SampleInterface
import ch.grze.daggergrip.binds.SampleInterface2
import ch.grze.daggergripcommons.ActivityInjectionsMapProvider
import dagger.Binds
import dagger.Module

@Module
abstract class BindsModule {
    @Binds
    abstract fun bindsSampleInterface2ToSampleImplementation2(binds: SampleImplementation2): SampleInterface2

    @Binds
    abstract fun bindsSampleInterfaceToSampleImplementation(binds: SampleImplementation): SampleInterface

    @Binds
    abstract fun bindsActivityInjectionsMapProviderToActivityInjectionsMap(binds: ActivityInjectionsMap): ActivityInjectionsMapProvider
}
