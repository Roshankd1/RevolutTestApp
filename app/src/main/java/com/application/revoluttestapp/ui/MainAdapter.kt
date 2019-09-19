package com.application.revoluttestapp.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.application.revoluttestapp.R
import com.application.revoluttestapp.rates.Rate
import com.application.revoluttestapp.util.format
import com.application.revoluttestapp.util.getCurrencyFlagId
import com.application.revoluttestapp.util.getCurrencyNameResId
import com.application.revoluttestapp.util.toFloat
import kotlinx.android.synthetic.main.item_currency_converter.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainAdapter(private val amountChangeListener: AmountChangeListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val symbolPosition = ArrayList<String>()
    private val symbolRate = HashMap<String, Rate>()
    private var amount = 1.0F

    //updates the rate of each currency
    fun updateRates(rates: ArrayList<Rate>) {
        if (symbolPosition.isEmpty()) {
            symbolPosition.addAll(rates.map { it.symbol })
        }
        for (rate in rates) {
            symbolRate[rate.symbol] = rate
        }
        notifyItemRangeChanged(0, symbolPosition.size - 1, amount)
    }

    fun updateAmount(amount: Float) {
        this.amount = amount

        notifyItemRangeChanged(0, symbolPosition.size - 1, amount)
    }

    private fun rateAtPosition(pos: Int): Rate {
        return symbolRate[symbolPosition[pos]]!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_currency_converter, parent, false)
        )
    }

    override fun getItemCount(): Int = symbolPosition.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RateViewHolder).bind(rateAtPosition(position))
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (!payloads.isNullOrEmpty()) {
            (holder as RateViewHolder).bind(rateAtPosition(position))
        } else {

            super.onBindViewHolder(holder, position, payloads)
        }
    }

    inner class RateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currencyFlag: AppCompatImageView = itemView.icCurrencyFlag
        var currencySymbol: AppCompatTextView = itemView.lblCurrencySymbol
        var currencyName: AppCompatTextView = itemView.lblCurrencyName
        var currencyAmount: AppCompatEditText? = itemView.txtCurrencyAmount
        var symbol: String = ""


        fun bind(rate: Rate) {
            if (symbol != rate.symbol) {
                initView(rate)
                this.symbol = rate.symbol
            }

            if (!currencyAmount!!.isFocused) {
                currencyAmount?.setText((rate.rate * amount).format())
            }
        }

        private fun initView(rate: Rate) {
            val symbol = rate.symbol.toLowerCase(Locale.getDefault())
            val nameId = getCurrencyNameResId(itemView.context, symbol)
            val flagId = getCurrencyFlagId(itemView.context, symbol)

            currencySymbol.text = rate.symbol
            currencyName.text = itemView.context.getString(nameId)
            currencyFlag.setImageResource(flagId)

            currencyAmount?.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    return@OnFocusChangeListener
                }
                layoutPosition.takeIf { it > 0 }?.also { currentPosition ->
                    symbolPosition.removeAt(currentPosition).also { symbolPosition.add(0, it) }
                    notifyItemMoved(currentPosition, 0)
                }
            }

            currencyAmount?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString().isNotEmpty() && currencyAmount!!.isFocused) {
                        if (s!!.length==1 && s == ".") {
                            //does nothing to avoid parse exception
                            return
                        }else{
                            amountChangeListener.onAmountChanged(symbol, s.toString().toFloat())
                        }
                    }
                }

            }
            )
        }


    }

}