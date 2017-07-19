package com.dataunavailable.messagecentersync

import android.app.Notification
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import android.view.View
import android.widget.ImageView
import java.io.ByteArrayOutputStream


/**
 * Created by lordo on 7/19/2017.
 */
class SyncData(context: Context, val notification: Notification) {

    init {

    }

    val imageView: ImageView = ImageView(context).apply {
        isDrawingCacheEnabled = true
    }

    val smallIcon : String by lazy {
        val drawable = notification.smallIcon.loadDrawable(context)

        imageView.setImageDrawable(drawable)
        imageView.measure(-1, -1)
        val bitmap = Bitmap.createBitmap(imageView.measuredWidth, imageView.measuredHeight, Bitmap.Config.ARGB_8888)
        imageView.draw(Canvas(bitmap))
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.NO_WRAP or Base64.URL_SAFE)
    }


    val largeIcon : String by lazy {
        val drawable = notification.getLargeIcon().loadDrawable(context)

        imageView.setImageDrawable(drawable)
        imageView.measure(-1, -1)
        val bitmap = Bitmap.createBitmap(imageView.measuredWidth, imageView.measuredHeight, Bitmap.Config.ARGB_8888)
        imageView.draw(Canvas(bitmap))
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.NO_WRAP or Base64.URL_SAFE)
    }

    val extras = notification.extras
}
