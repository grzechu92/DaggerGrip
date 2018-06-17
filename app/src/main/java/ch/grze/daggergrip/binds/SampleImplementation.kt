package ch.grze.daggergrip.binds

import ch.grze.daggergripcommons.BindsTo
import javax.inject.Inject

@BindsTo(SampleInterface::class)
class SampleImplementation @Inject constructor() : SampleInterface {
    override fun doSomething() {
        //do something
    }
}