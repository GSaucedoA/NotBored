package dev.cynomys.notbored

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _isValidStart = MutableLiveData(false)
    val isValidStart: LiveData<Boolean>
        get() = _isValidStart

    private fun isParticipantsValid(participantsNumber: Int): Boolean {
        return participantsNumber >= 1
    }

    private fun isPriceValid(price: Double): Boolean {
        return price in 0.0..1.0
    }

    fun canStart(stringNumber: String, priceString: String, checked: Boolean) {
        val number: Int = if (stringNumber.isBlank()) 0 else stringNumber.toInt()
        val price: Double = if (priceString.isNullOrBlank()) -1.0 else priceString.toDouble()

        _isValidStart.value =
            isParticipantsValid(number) && isPriceValid(price) && checked
    }
}