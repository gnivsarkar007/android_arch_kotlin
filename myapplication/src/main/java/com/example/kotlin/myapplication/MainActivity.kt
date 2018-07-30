package com.example.kotlin.myapplication

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.kotlin.myapplication.api.ApiInterface
import com.example.kotlin.myapplication.prefs.IntPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val intPref: IntPreference by inject("key_int_pref")
    private val apiInterface: ApiInterface by inject()
    private val apiKey: String by inject("apiKey")
    private lateinit var floatingButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        floatingButton = fab
        fab.setOnClickListener { view ->
            showSnackBar("Replace with your own action" + intPref.get())
        }

        apiInterface
                .getBestSellerList(apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t -> showSnackBar("No of results:  " + t!!.numResults) },
                        { err -> err.printStackTrace() })
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(floatingButton, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }
}
