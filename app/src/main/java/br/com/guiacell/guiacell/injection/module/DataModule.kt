package br.com.guiacell.guiacell.injection.module

import android.app.Application
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(ApiModule::class, DbModule::class))
class DataModule {

    @Provides
    @Singleton
    fun provideSheredPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("ribots", MODE_PRIVATE)
    }
}