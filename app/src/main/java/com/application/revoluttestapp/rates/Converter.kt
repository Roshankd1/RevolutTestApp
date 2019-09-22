package com.application.revoluttestapp.rates

interface Converter {
    /**
     * Updates list of rates
     */
    fun updateRatesList(rates: ArrayList<Rate>)

    /**
     * Updates the amount in each EditText field
     */
    fun updateAmount(amount: Float)

    /**
     * Shows or hides the loader
     */
    fun showLoading(isLoading: Boolean)

    /**
     * Shows an error message
     */
    fun showErrorMessage()

}