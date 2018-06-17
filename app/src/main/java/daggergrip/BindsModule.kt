package daggergrip

import ch.grze.daggergripcommons.ActivityInjectionsMapProvider
import ch.grze.sample.binds.SampleImplementation
import ch.grze.sample.binds.SampleImplementation2
import ch.grze.sample.binds.SampleInterface
import ch.grze.sample.binds.SampleInterface2
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
