package com.application.revoluttestapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.revoluttestapp.R
import com.application.revoluttestapp.rates.Converter
import com.application.revoluttestapp.rates.Rate
import com.irozon.sneaker.Sneaker
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import presenter.MainPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Converter {

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //dependency inject for the activity
        AndroidInjection.inject(this)

        presenter.converterView = this
        presenter.initRates()

        adapter = MainAdapter(object : AmountChangeListener {
            override fun onAmountChanged(symbol: String, amount: Float) {
                presenter.checkRates(symbol, amount)
            }

        })
        recyclerViewContainer.setHasFixedSize(true)
        recyclerViewContainer.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerViewContainer.adapter = adapter

    }


    override fun updateRatesList(rates: ArrayList<Rate>) {
        adapter.updateRates(rates)
    }

    override fun updateAmount(amount: Float) {
        adapter.updateAmount(amount)
    }

    override fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            recyclerViewContainer.visibility = View.GONE
        } else {
            recyclerViewContainer.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }

    }

    override fun showErrorMessage() {
        Sneaker.with(this)
            .setTitle(resources.getString(R.string.error_title))
            .setMessage(resources.getString(R.string.error_message))
            .sneakError()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()

    }

}
