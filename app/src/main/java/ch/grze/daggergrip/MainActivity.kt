package ch.grze.daggergrip

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ch.grze.daggergrip.binds.SampleInterface
import dagger.android.AndroidInjection

@InjectInActivity
class MainActivity : AppCompatActivity() {

    lateinit var sampleInterface: SampleInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)
    }
}
