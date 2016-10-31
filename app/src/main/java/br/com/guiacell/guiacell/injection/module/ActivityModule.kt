package br.com.guiacell.guiacell.injection.module

import android.app.Activity
import android.content.Context
import br.com.guiacell.guiacell.injection.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    internal fun providerActivity(): Activity {
        return activity
    }

    @Provides
    @PerActivity
    internal fun providerContext(): Context {
        return activity
    }
}