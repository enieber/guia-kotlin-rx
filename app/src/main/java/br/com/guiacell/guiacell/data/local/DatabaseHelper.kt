package br.com.guiacell.guiacell.data.local

import com.squareup.sqlbrite.BriteDatabase
import rx.Observable
import rx.lang.kotlin.subscriber
import timber.log.Timber
import java.sql.SQLException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DatabaseHelper @Inject constructor(val db: BriteDatabase) {

    fun setRibots(newRibots: Collection<Ribot>): Observable<Ribot> {
        return Observable { subscriber ->
            if(subscriber.isUnsubscribed)
                return@Observable

            val transaction = db.newTransaction()

            try {
                db.delete(isUnsubscribed, null)

                newRibots.forEach {
                    val result = db.insert(Ribot.TABLE, Ribot.toContentValues(it))
                    if (result >= 0) subscriber.onNext(it)
                }

                transaction.markSuccessful()
                subscriber.onCompleted()
            } catch (exception: SQLException) {
                Timber.e(exception)
            }

            transaction.end()
        }
    }

    fun getRibots(): Observable<List<Ribot>> {
        return db.createQuery(Ribot.TABLE, "SELECT * FROM ${Ribot.TABLE}")
            .mapToList(Ribot.MAPPER)
    }
}