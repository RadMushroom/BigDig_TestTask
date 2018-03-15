package com.example.radmushroom.bigdig_testtask_b

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Environment
import android.widget.Toast
import com.example.radmushroom.contentprovider.link.LinkBean
import com.example.radmushroom.contentprovider.link.LinkColumns
import com.example.radmushroom.contentprovider.link.LinkModel
import com.example.radmushroom.contentprovider.link.LinkSelection
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.File
import java.io.FileOutputStream

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        intent.extras?.let {
            if (it.containsKey(MainActivity.KEY_ID)) {
                val id = it.getLong(MainActivity.KEY_ID)
                val linkSelection = LinkSelection().id(id)
                val projection = arrayOf(LinkColumns._ID, LinkColumns.LINK, LinkColumns.TIME, LinkColumns.STATUS)
                val linkCursor = linkSelection.query(context, projection)
                var link: LinkModel? = null
                while (linkCursor.moveToNext()) {
                    link = LinkBean.newInstance(linkCursor.id, linkCursor.link, linkCursor.time, linkCursor.status)
                    break
                }
                link?.let {
                    saveToFile(context, it)
                }
                linkSelection.delete(context)
                context.sendBroadcast(Intent("com.example.radmushroom.bigdig_testtask_A.view.LINK_DELETED").apply {
                    putExtra("id", id)
                })
                Toast.makeText(context, "Table Row №$id was deleted!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveToFile(context: Context, linkModel: LinkModel) {
        val file = File("${Environment.getExternalStorageDirectory().path}/BIGDIG/test/B/${linkModel.id}_${System.currentTimeMillis()}.png")
        if (file.exists()) {
            file.delete()
        }
        file.parentFile.mkdirs()
        file.createNewFile()
        Picasso.with(context).load(linkModel.link).into(object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapFailed(errorDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom?) {
                FileOutputStream(file).use {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                }
            }

        })
    }
}