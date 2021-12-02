package com.mintic.travelTicApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Log.d(TAG, "onCreate")
        val name = intent.getStringExtra(ListActivity.KEY_NAME)
        val description = intent.getStringExtra(ListActivity.KEY_DESCRIPTION)
        val points = intent.getParcelableExtra<Sites>(ListActivity.KEY_POINTS)

        Log.d(TAG, "Name: $name")
        Log.d(TAG, "Last Name: $description")
        Log.d(TAG, "Contact: $points")
    }

    companion object {
        private val TAG = DetailActivity::class.java.simpleName
    }
}