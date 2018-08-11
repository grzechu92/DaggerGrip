package ch.grze.daggergripprocessor

import com.squareup.kotlinpoet.ClassName.Companion.bestGuess

class Classes {
    companion object {
        val BINDS = bestGuess("dagger.Binds")
        val MODULE = bestGuess("dagger.Module")
        val INJECT = bestGuess("javax.inject.Inject")
        val CONTRIBUTES_ANDROID_INJECTOR = bestGuess("dagger.android.ContributesAndroidInjector")
        val ANDROID_INJECTION_MODULE = bestGuess("dagger.android.AndroidInjectionModule")
        val ANDROID_SUPPORT_INJECTION_MODULE = bestGuess("dagger.android.support.AndroidSupportInjectionModule")
        val ACTIVITIES_MODULE = bestGuess("daggergrip.ActivitiesModule")
        val BINDS_MODULE = bestGuess("daggergrip.BindsModule")
    }
}