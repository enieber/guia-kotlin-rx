package br.com.guiacell.guiacell.injection.component

import br.com.guiacell.guiacell.injection.ConfigPersistent
import br.com.guiacell.guiacell.injection.module.ActivityModule
import dagger.Component

@ConfigPersistent
@Component(dependencies = arrayOf(ApplicationComponet::class))
interface ConfigPersistentComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}