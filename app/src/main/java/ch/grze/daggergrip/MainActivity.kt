package ch.grze.daggergrip

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ch.grze.daggergrip.binds.SampleInterface
import ch.grze.daggergrip.binds.SampleInterface2
import ch.grze.daggergripcommons.InjectInActivity
import javax.inject.Inject

@InjectInActivity
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sampleInterface: SampleInterface

    @Inject
    lateinit var sampleInterface2: SampleInterface2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sampleInterface.doSomething()
        sampleInterface2.doSomethingElse()
    }
}
