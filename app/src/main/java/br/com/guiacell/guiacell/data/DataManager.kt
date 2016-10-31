package br.com.guiacell.guiacell.data

import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataManager @Inject constructor(private val ribotsService: RibotsService,
                                      private val databaseHelper: DataBaseHelper){

    fun syncRibots(): Observable<Ribot> {
        return ribotsService.getRibots()
                .concatMap { databaseHelper.setRibots(it) }
    }

    fun getRibots(): Observable<List<Ribot>> {
        return databaseHelper.getRibots().distinct()
    }

}