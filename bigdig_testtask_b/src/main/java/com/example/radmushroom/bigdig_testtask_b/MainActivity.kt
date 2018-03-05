package com.example.radmushroom.bigdig_testtask_b

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.ActivityCompat
import android.support.v4.content.PermissionChecker.PERMISSION_GRANTED
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.radmushroom.contentprovider.link.LinkContentValues
import com.example.radmushroom.contentprovider.link.LinkSelection
import com.example.radmushroom.contentprovider.link.Status
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val dateFormatter = SimpleDateFormat("hh:mm:ss dd-MM-yyyy", Locale.getDefault())
    private var toast: Toast? = null
    private var cdt: CountDownTimer? = null

    companion object {
         const val REQUEST_CODE = 1001
         const val KEY_ID = "id"
         const val KEY_LINK = "link"
         const val KEY_STATUS = "status"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent.extras?.let {
            if (it.containsKey(KEY_LINK)) {
                val id = if (!it.containsKey(KEY_ID)) {
                    val uri = insertLinkModel(it.getString(KEY_LINK))
                    uri.lastPathSegment.toLong()
                } else {
                    it.getLong(KEY_ID)
                }

                if (Status.STATUS_1 == it.get(KEY_STATUS)) {
                    if (PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this,
                                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        //AlarmService delete record with status 1
                        scheduleLinkDeleting(id)
                    } else {
                        ActivityCompat.requestPermissions(this,
                                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                REQUEST_CODE)
                    }
                }

                Picasso.with(applicationContext)
                        .load(it.getString(KEY_LINK))
                        .into(imageView, object : Callback {
                            override fun onSuccess() {
                                updateLinkModel(id, it.getString(KEY_LINK), Status.STATUS_1)
                            }

                            override fun onError() {
                                updateLinkModel(id, it.getString(KEY_LINK), Status.STATUS_2)
                            }

                        })
            } else {
                showTimer()
            }
        } ?: showTimer()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (REQUEST_CODE == requestCode && permissions.isNotEmpty()) {
            (0 until permissions.size)
                    .any {
                        Manifest.permission.WRITE_EXTERNAL_STORAGE == permissions[it] &&
                                PERMISSION_GRANTED == grantResults[it]
                    }
                    .let {
                        scheduleLinkDeleting(intent.extras.getLong(KEY_ID))
                    }
        }
    }

    fun insertLinkModel(link: String): Uri {
        val linkCV = LinkContentValues()
        linkCV.putLink(link)
        linkCV.putTime(dateFormatter.format(Date()))
        linkCV.putStatus(Status.STATUS_3)
        return linkCV.insert(applicationContext)
    }

    fun updateLinkModel(id: Long, link: String, status: Status) {
        val linkCV = LinkContentValues()
        linkCV.putLink(link)
        linkCV.putTime(dateFormatter.format(Date()))
        linkCV.putStatus(status)
        val linkSelection = LinkSelection()
        linkSelection.id(id)
        linkCV.update(this, linkSelection)
    }

    override fun onDestroy() {
        super.onDestroy()
        cdt?.cancel()
    }

    private fun showTimer() {
        cdt?.cancel()
        cdt = object : CountDownTimer(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS.toMillis(1)) {
            override fun onTick(millisUntilFinished: Long) {
                toast?.cancel()
                toast = Toast.makeText(this@MainActivity, "This application is not standalone! \n${TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)} seconds left", Toast.LENGTH_SHORT)
                        .apply {
                            show()
                        }
            }

            override fun onFinish() {
                finish()
            }
        }.start()
    }

    private fun scheduleLinkDeleting(id: Long) {
        val time: Long = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15)
        val intentAlarm = Intent(this, AlarmReceiver::class.java).apply {
            putExtra(KEY_ID, id)
            action = "com.example.radmushroom.bigdig_testtask_b.DELETE_LINK"
        }
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT))
    }
}
