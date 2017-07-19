package com.dataunavailable.messagecentersync

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Base64
import android.util.Log
import com.google.firebase.database.FirebaseDatabase

/**
 * Created 7/19/2017.
 */
class NotificationListener : NotificationListenerService() {

    private val TAG = toString()

    private val database : FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        Log.d(TAG, "Post started")
        super.onNotificationPosted(sbn)
        sbn?.notification ?: return
        Log.d(TAG, String.format("Notification posted for %s:\nid:%s\nactions: %d", sbn.packageName, sbn.id, sbn.notification.actions?.size))
        Log.d(TAG, String.format("%s", sbn.notification.toString()))


        val syncData = SyncData(this, sbn.notification)

        val dbRef = database.getReference("notification").push()

        dbRef.child("smallIcon").setValue(syncData.smallIcon)
    }


    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        Log.d(TAG, String.format("Notification removed for %s: %s", sbn?.packageName, sbn?.id))
    }

}