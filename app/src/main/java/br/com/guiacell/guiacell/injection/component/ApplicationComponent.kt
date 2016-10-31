package br.com.guiacell.guiacell.injection.component

import android.app.Application
import android.content.Context
import br.com.guiacell.guiacell.data.SyncService
import br.com.guiacell.guiacell.injection.ApplicationContext
import br.com.guiacell.guiacell.injection.module.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplcationModule::class, DataModule::class))
interface ApplicationComponent {
    fun inject(syncService: SyncService)

    @ApplicationContext fun context(): Context
    fun application(): Application
    fun ribotsService(): RibotsService
    fun databaseHelper(): DatabaseHelper
    fun dataManager(): DataManager
}