package br.com.guiacell.guiacell.injection.component

import br.com.guiacell.guiacell.injection.PerActivity
import br.com.guiacell.guiacell.injection.module.ActivityModule
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}