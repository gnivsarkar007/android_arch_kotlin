package com.example.kotlin.myapplication.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.api.model.Response
import com.example.kotlin.myapplication.domain.ViewModelResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), Observer<ViewModelResponse<Response>> {
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
        viewModel.resultsListLiveData.observe(this, this)
        viewModel.doStuff()
    }

    override fun onChanged(resultData: ViewModelResponse<Response>?) {
        when (resultData!!.state) {
            -1 -> showLoading(true)
            0, 1 -> {
                showLoading(false)
                when {
                    resultData.data != null -> adapter.refreshData(resultData.data?.results!!)
                    resultData.error != null -> showSnackBar(resultData.error?.message!!)
                }
            }
        }

    }

    private fun showLoading(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
        recycler.visibility = if (show) View.GONE else View.VISIBLE
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
