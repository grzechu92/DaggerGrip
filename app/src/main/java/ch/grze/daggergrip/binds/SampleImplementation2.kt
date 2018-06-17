package ch.grze.daggergrip.binds

import ch.grze.daggergripcommons.BindsTo
import javax.inject.Inject

@BindsTo(SampleInterface2::class)
class SampleImplementation2 @Inject constructor() : SampleInterface2 {
    override fun doSomethingElse() {
        //do something
    }
}

