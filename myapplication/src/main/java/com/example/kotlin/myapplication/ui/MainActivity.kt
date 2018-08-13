package com.example.kotlin.myapplication.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.api.model.Response
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_main.fab
import kotlinx.android.synthetic.main.content_main.recycler
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), Observer<Response> {

    val viewModel: MainActivityViewModel by viewModel()
    val adapter: BestSellerAdapter by inject { mapOf("activity" to this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            showSnackBar("Replace with your own action$viewModel")
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        viewModel.resultsListLiveData.observe(this, Observer { t: Response? -> onChanged(t) })
        viewModel.doStuff()
    }

    override fun onChanged(resultData: Response?) {
        adapter.refreshData(resultData?.results!!)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(fab, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    override fun onDestroy() {
        viewModel.resultsListLiveData.removeObservers(this)
        super.onDestroy()
    }
}
