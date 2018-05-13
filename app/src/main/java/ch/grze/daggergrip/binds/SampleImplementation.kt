package ch.grze.daggergrip.binds

import ch.grze.daggergripannotations.BindsTo

@BindsTo(SampleInterface::class)
class SampleImplementation : SampleInterface