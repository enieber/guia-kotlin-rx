package br.com.guiacell.guiacell

import android.app.Application
import br.com.guiacell.guiacell.injection.component.ApplicationComponent
import br.com.guiacell.guiacell.injection.module.ApplicationModule
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

/**
 * Created by enieer on 30/10/16.
 */
class GuiaCellApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()


        if (LeakCanary.isInAnalyzerProcess(this))
            return

        LeakCanary.install(this)

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}