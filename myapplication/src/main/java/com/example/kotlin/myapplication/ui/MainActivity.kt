package com.example.kotlin.myapplication.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.api.model.ResultsItem
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), Observer<List<ResultsItem>> {

    val viewModel: MainActivityViewModel by viewModel()
    val adapter: BestSellerAdapter by inject { mapOf("activity" to this) }
    private lateinit var floatingButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        floatingButton = fab
        fab.setOnClickListener { view ->
            showSnackBar("Replace with your own action$viewModel")
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        viewModel.resultsListLiveData.observe(this, this)
        viewModel.doStuff()
    }

    override fun onChanged(resultData: List<ResultsItem>?) {
        adapter.refreshData(resultData!!)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(floatingButton, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    override fun onDestroy() {
        viewModel.resultsListLiveData.removeObservers(this)
        super.onDestroy()
    }
}
