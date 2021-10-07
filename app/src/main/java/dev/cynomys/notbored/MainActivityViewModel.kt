package dev.cynomys.notbored

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _isValidNumber = MutableLiveData<Boolean>()
    val isValidNumber: LiveData<Boolean>
        get() = _isValidNumber

    fun canStart(stringNumber: String) {
        val number: Int = if (stringNumber.isBlank()) {
            0
        } else {
            stringNumber.toInt()
        }
        _isValidNumber.value = number >= 1
    }
}