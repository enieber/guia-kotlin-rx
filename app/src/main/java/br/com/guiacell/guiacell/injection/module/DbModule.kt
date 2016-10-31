package br.com.guiacell.guiacell.injection.module

import android.app.Application
import com.squareup.sqlbrite.BriteDatabase
import com.squareup.sqlbrite.SqlBrite
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideOpenHelper(application: Application): DbOpenHelper {
        return DbOpenHelper(application)
    }

    @Provides
    @Singleton
    fun providerSqlBrite(): SqlBrite {
        return SqlBrite.create(SqlBrite.Logger { message -> Timber.tag("Database").v(message) })
    }

    @Provides
    @Singleton
    fun providerDatabase(sqlBrite: SqlBrite, helper: DbOpenHelper): BriteDatabase {
        val db = sqlBrite.wrapDatabaseHelper(helper)
        db.setLoggingEnabled(true)
        return db
    }
}
