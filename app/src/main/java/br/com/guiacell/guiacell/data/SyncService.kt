package br.com.guiacell.guiacell.data

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import rx.Subscription
import rx.lang.kotlin.FunctionSubscriber
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class SyncService: Service() {

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, SyncService::class.java)
        }
    }

    @Inject
    lateinit var dataManager: DataManager

    var subscription: Subscription? = null

    override fun onCreate() {
        super.onCreate()
        getApplicationComponent().inject(this)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Timber.i("Starting sync...")

        if (!isNetWorkConnected()) {
            Timber.i("Sinc canceled, connection not available")
            toggeAndroidCompoent(SyncOnConnectionAvaliable::class.java, true)
            stopSelf()
            return Service.START_NOT_STICKY
        }

        subscription?.let { if (!it.isUnsubscribed) it.unsubscribe() }

        subscription = dataManager.syncRibots()
                       .subscribeOn(Schedulers.io())
                       .subscribe(FunctionSubscriber<Ribot>()
                       .onCompleted {
                            Timber.i("Synced successfully!")
                            stopSelf(startId)
                        }

                       .onError {
                           Timber.w(it, "Error syncing.")
                           stopSelf(startId)
                       })

        return Service.START_STICKY
    }

    override fun onDestroy() {
        subscription?.unsubscribe()
        subscription = null
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder {
      return null
    }

    class SyncOnConnectionAvailable : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == CONNECTIVITY_ACTION && context.isNetworkConnected()) {
                Timber.i("Connection is now available, triggering sync...")
                context.toggleAndroidComponent(SyncOnConnectionAvailable::class.java, false)
                context.startService(getStartIntent(context))
            }
        }
    }
}