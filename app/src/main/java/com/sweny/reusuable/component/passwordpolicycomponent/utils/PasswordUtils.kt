package com.sweny.reusuable.component.passwordpolicycomponent.utils


import java.util.regex.Pattern

class PasswordUtils  {

    companion object {
    /*
     * validate passwords Numeric
     * @params
     * Numbers: "(.*[0-9].*)"
     *
     * @returns
     */

         fun validatePasswordNumeric(password: String) : Boolean {
             val expression = "(.*[0-9].*)"
             val p = Pattern.compile(expression, Pattern.CASE_INSENSITIVE) // pattern=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
             val m = p.matcher(password)
             return m.matches() && password.trim { it <= ' ' }.length > 0
         }

    /*
     * validate password Upper Lower case
     * @params
     * Uppercase: "(.*[A-Z].*)"
     *
     * @returns
     */

         fun validatePasswordLowerCase(password: String): Boolean {
            val expression = "(.*[a-z].*)"
            val p = Pattern.compile(expression, Pattern.DOTALL)
            val m = p.matcher(password)
            return m.matches() && password.trim { it <= ' ' }.length > 0
        }

        /*
     * validate password Upper Lower case
     * @params
     * Uppercase: "(.*[A-Z].*)"
     *
     * @returns
     */

         fun validatePasswordUpperCase(password: String): Boolean {
            val expression = "(.*[A-Z].*)"
            val p = Pattern.compile(expression, Pattern.DOTALL)
            val m = p.matcher(password)
            return m.matches() && password.trim { it <= ' ' }.length > 0
        }

        /*
     * validate password Special Characters
     * @params
     * Special characters: "^(?=.*[_.()$&@]).*$"
     *
     * @returns
     */

         fun validatePasswordSpecialChar(password: String): Boolean {
            val expression = "^(?=.*[_.()\$&@]).*\$"
            val p = Pattern.compile(expression, Pattern.CASE_INSENSITIVE) // pattern=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
            val m = p.matcher(password)
            return m.matches() && password.trim { it <= ' ' }.length > 0
        }

         fun validateMin8To15Char(password: String) = (password.length >= 8 && password.length <= 15)
    }

}