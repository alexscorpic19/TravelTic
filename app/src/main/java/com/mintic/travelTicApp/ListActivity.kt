package com.mintic.travelTicApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException
import java.util.ArrayList
import org.json.JSONArray
import org.json.JSONException

class ListActivity : AppCompatActivity() {

    private lateinit var mSites: ArrayList<Sites>
    private lateinit var mAdapter: customAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.site_list)
        setupRecyclerView()
        initDataFromFile()
    }

    /**
     * Sets up the RecyclerView: empty data set, item dividers, swipe to delete.
     */
    private fun setupRecyclerView() {
        mSites = arrayListOf()
        recycler.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = customAdapter(mSites, this) { site ->
            siteOnClick(site)
        }

        recycler.adapter = mAdapter
    }

    /* RecyclerView item is clicked. */
    private fun siteOnClick(sites: Sites?) {
        Log.d(TAG, "Click on: $sites")
        sites?.let {
            navigateToDetail(it)
        }
    }

    private fun navigateToDetail(sites: Sites) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(KEY_NAME, sites.name)
            putExtra(KEY_DESCRIPTION, sites.description)
            putExtra(KEY_POINTS, sites.points)
        }

        startActivity(intent)
    }

    /**
     * Generates mock contact data to populate the UI from a JSON file in the
     * assets directory, called from the options menu.
     */
    private fun initDataFromFile() {
        val sitesString = readSiteJsonFile()
        try {
            val sitesJson = JSONArray(sitesString)
            for (i in 0 until sitesJson.length()) {
                val sitesJson = sitesJson.getJSONObject(i)
                val sites = Sites(
                    sitesJson.getString("name"),
                    sitesJson.getString("description"),
                    sitesJson.getString("points"),
                    sitesJson.getString("image")
                )
                Log.d(TAG, "generateSites: $sites")
                mSites.add(sites)
            }

            mAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    /**
     * Reads a file from the assets directory and returns it as a string.
     *
     * @return The resulting string.
     */
    private fun readSiteJsonFile(): String? {
        var sitesString: String? = null
        try {
            val inputStream = assets.open("sites_list.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            sitesString = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return sitesString
    }

    private fun createMockContacts(): ArrayList<Sites> {
        return arrayListOf(
            Sites("site1", "descripcion", "0", ""),
            Sites("site2", "descripcion", "0", ""),
            Sites("site3", "descripcion", "0", ""),
            Sites("site4", "descripcion", "0", "")
        )
    }

    companion object {
        private val TAG = ListActivity::class.java.simpleName
        const val KEY_NAME = "site_extra_name"
        const val KEY_DESCRIPTION = "site_extra_description"
        const val KEY_POINTS = "site_extra_points"
    }
}