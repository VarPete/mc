package com.dataunavailable.messagecenter

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.dataunavailable.messagecenter.databinding.ActivityMainBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * Skeleton of an Android Things activity.
 *
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * val service = PeripheralManagerService()
 * val mLedGpio = service.openGpio("BCM6")
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
 * mLedGpio.value = true
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 *
 */
class MainActivity : Activity() {

    val notifications: DatabaseReference  by lazy { FirebaseDatabase.getInstance().reference.child("notification") }

    var mAdapter : NotificationEntryAdapter? = null

    val binding : ActivityMainBinding by lazy {
        DataBindingUtil.inflate<ActivityMainBinding>(layoutInflater, R.layout.activity_main, null, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        mAdapter = NotificationEntryAdapter(this, notifications)
        binding.root.recyclerView.adapter = mAdapter
    }

    override fun onStop() {
        super.onStop()
        mAdapter?.cleanup()
        mAdapter = null
        binding.root.recyclerView.adapter = null
    }

}
