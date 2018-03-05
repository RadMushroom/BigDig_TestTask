package com.example.radmushroom.bigdig_testtask_A.view


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.radmushroom.bigdig_testtask_A.R
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {

   lateinit var actionBar: ActionBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        editText.setText("https://images.unsplash.com/photo-1502147508239-bece272fc008?ixlib=rb-0.3.5&s=96d45b32a8dd031bebe8174dfa91b897&auto=format&fit=crop&w=811&q=80")
        startAppB.setOnClickListener {
            val inputText = editText.text.toString().trim()
            if (!TextUtils.isEmpty(inputText)) {
                val intent = Intent( "com.example.radmushroom.bigdig_testtask_b")
                intent.putExtra("link", inputText)
                startActivity(intent)
            } else Toast.makeText(context, "Please input the link!", Toast.LENGTH_SHORT).show()
        }

    }
}
