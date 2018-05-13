package ch.grze.daggergripprocessor

import com.squareup.kotlinpoet.ClassName

class DaggerClasses {
    companion object {
        val BINDS = ClassName.bestGuess("dagger.Binds")
        val MODULE = ClassName.bestGuess("dagger.Module")
        val CONTRIBUTES_ANDROID_INJECTOR = ClassName.bestGuess("dagger.android.ContributesAndroidInjector")
    }
}