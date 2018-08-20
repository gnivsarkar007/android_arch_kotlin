package com.example.kotlin.myapplication.ui.bestseller

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.domain.ViewState
import com.example.kotlin.myapplication.ui.bestseller.viewmodel.BestSellerViewModel
import com.example.kotlin.myapplication.utils.Constants
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_main.fab
import kotlinx.android.synthetic.main.content_main.progress
import kotlinx.android.synthetic.main.content_main.recycler
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), Observer<ViewState<List<BestSellerViewModel>>> {
    private val viewModel: MainActivityViewModel by viewModel()
    private val adapter: BestSellerAdapter by inject { mapOf("activity" to this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

      fab.setOnClickListener { _ ->
          retry()
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        viewModel.resultsListLiveData.observe(this, this)
        viewModel.doStuff()
    }

    override fun onChanged(resultData: ViewState<List<BestSellerViewModel>>?) {
        when (resultData?.let { resultData.state }) {
            Constants.STATE_LOADING -> showLoading(true)
            Constants.STATE_FAILURE, Constants.STATE_SUCCESS -> {
                showLoading(false)
                when {
                  resultData.data != null -> adapter.refreshData(resultData.data)
                  else -> showSnackBar(resultData.error?.message.orEmpty())
                }
            }
        }

    }

    private fun retry() {
        if (viewModel.currentState == Constants.STATE_FAILURE) {
            viewModel.doStuff()
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
