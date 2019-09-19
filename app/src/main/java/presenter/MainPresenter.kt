package presenter

import com.application.revoluttestapp.domain.RateUseCase
import com.application.revoluttestapp.rates.Converter
import com.application.revoluttestapp.rates.Rate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.collections.ArrayList


class MainPresenter @Inject constructor(private val rateUseCase: RateUseCase) {

    companion object {
        const val DEFAULT_SYMBOL = "EUR"
    }

    lateinit var converterView: Converter
    private lateinit var disposable: Disposable

    private var currentBase: String = ""
    private var viewStopped = false
    private var isLoaded = false

    /**
     * method that updates the rates with resepct to the base value of currency
     */
    fun checkRates(base: String, amount: Float) {
        if (base.equals(currentBase, ignoreCase = true)) {
            converterView.updateAmount(amount)
        } else {
            currentBase = base.toUpperCase(Locale.getDefault())
            disposable = rateUseCase.getRates(base)
                .doOnSubscribe {
                    if (!isLoaded) {
                        converterView.showLoading(true)
                    }
                }
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatUntil { viewStopped || !base.equals(currentBase, ignoreCase = true) }
                .subscribe({ it ->
                    val rates = ArrayList<Rate>()
                    rates.add(Rate(it.base, 1.0F))
                    rates.addAll(it.rates.map { Rate(it.key, it.value) })

                    converterView.updateRatesList(rates)
                    if (!isLoaded) {
                        converterView.showLoading(false)
                    }
                    isLoaded = true
                }, {
                    converterView.showErrorMessage()
                })
        }
    }

    fun initRates() {
        checkRates(DEFAULT_SYMBOL, 1F)
    }

    fun onDestroy() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        viewStopped = true
    }
}