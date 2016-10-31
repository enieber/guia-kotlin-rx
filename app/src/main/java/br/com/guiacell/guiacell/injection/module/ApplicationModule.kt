package br.com.guiacell.guiacell.injection.module

import android.app.Application
import android.content.Context
import br.com.guiacell.guiacell.injection.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: Application) {

    @Provides
    @Singleton
    internal fun providerAplication(): Application {
        return application
    }

    @Provides
    @Singleton
    @ApplicationContext
    internal fun providerContext(): Context {
        return application
    }
}