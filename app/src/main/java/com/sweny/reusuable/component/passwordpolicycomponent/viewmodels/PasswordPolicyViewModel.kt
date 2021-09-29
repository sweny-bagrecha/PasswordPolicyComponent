package com.sweny.reusuable.component.passwordpolicycomponent.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sweny.reusuable.component.passwordpolicycomponent.domain.logic.PasswordInputUseCase

class PasswordPolicyViewModel : ViewModel()
{

    val newPassword1 = MutableLiveData<String>()
    val newPassword2 = MutableLiveData<String>()
    var numericPassed = MutableLiveData<Boolean>()
    var upperLowerPassed = MutableLiveData<Boolean>()
    var specialPassed = MutableLiveData<Boolean>()
    var minMaxPassed = MutableLiveData<Boolean>()
    var allPassed = MutableLiveData<Boolean>()
    private val passwordInputUseCase = PasswordInputUseCase()

    /**
     * Validate password across the password policy
     *
     */
    fun validate() {
        validatePasswordNumeric()
        validatePasswordUpperLowerCase()
        validatePasswordSpecialChar()
        validateMin8To15Char()
        checkAllPassed()
    }

    /**
     * Validate if the password contains a number
     *
     */
    private fun validatePasswordNumeric()  {
        numericPassed.value = passwordInputUseCase.validatePasswordNumeric(newPassword1.value ?: "")
    }

    /**
     * Validate if the password contains a lowercase character
     *
     */
    private fun validatePasswordUpperLowerCase()  {
        upperLowerPassed.value = passwordInputUseCase.validatePasswordUpperCase(newPassword1.value ?: "") && passwordInputUseCase.validatePasswordLowerCase(newPassword1.value ?: "")
    }

    /**
     * Validate if the password contains a special character
     *
     */
    private fun validatePasswordSpecialChar()  {
        specialPassed.value = passwordInputUseCase.validatePasswordSpecialChar(newPassword1.value ?: "")
    }

    /**
     * Validate if the password character size is between 8 to 15
     *
     */
    private fun validateMin8To15Char()  {
        minMaxPassed.value = passwordInputUseCase.validateMin8To15Char(newPassword1.value ?: "")
    }

    /**
     * Check if password matches the password criteria
     *
     */
    private fun checkAllPassed() {
        allPassed.value = numericPassed.value ?: false
                && upperLowerPassed.value ?: false
                && specialPassed.value ?: false
                && minMaxPassed.value ?: false
    }
}