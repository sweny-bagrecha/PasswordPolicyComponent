package com.sweny.reusuable.component.passwordpolicycomponent.domain.logic

import com.sweny.reusuable.component.passwordpolicycomponent.utils.PasswordUtils

interface IPasswordInputUseCase {
    fun validatePasswordNumeric(password: String): Boolean
    fun validatePasswordUpperCase(password: String): Boolean
    fun validatePasswordLowerCase(password: String): Boolean
    fun validatePasswordSpecialChar(password:String): Boolean
    fun validateMin8To15Char(password: String): Boolean
}

class PasswordInputUseCase : IPasswordInputUseCase {

    /*
     * validate passwords Numeric
     * @params password
     * Numbers: "(.*[0-9].*)"
     *
     * @returns validation success condition
     */

    override fun validatePasswordNumeric(password:String) = PasswordUtils.validatePasswordNumeric(password)

    /*
     * validate password Upper Lower case
     * @params password
     * Uppercase: "(.*[A-Z].*)"
     *
     * @returns validation success condition
     */

    override fun validatePasswordLowerCase(password:String) = PasswordUtils.validatePasswordLowerCase(password)

    /*
     * validate password Upper Lower case
     * @params password
     * Uppercase: "(.*[A-Z].*)"
     *
     * @returns validation success condition
     */

    override fun validatePasswordUpperCase(password:String) = PasswordUtils.validatePasswordUpperCase(password)

    /*
     * validate password Special Characters
     * @params password
     * Special characters: "^(?=.*[_.()$&@]).*$"
     *
     * @returns  validation success condition
     */

    override fun validatePasswordSpecialChar(password:String) = PasswordUtils.validatePasswordSpecialChar(password)

    /*
     * validate password Special Characters
     * @params password
     * password >= 8 <= 15
     *
     * @returns validation success condition
     */

    override fun validateMin8To15Char(password: String) = PasswordUtils.validateMin8To15Char(password)

}