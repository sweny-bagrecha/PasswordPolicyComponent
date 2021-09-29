package com.sweny.reusuable.component.passwordpolicycomponent.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sweny.reusuable.component.passwordpolicycomponent.R
import com.sweny.reusuable.component.passwordpolicycomponent.databinding.WidgetPasswordPolicyBinding

class PasswordPolicyComponent  @JvmOverloads
constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defineStyleAttribute: Int = 0
) :
    ConstraintLayout(context, attributeSet, defineStyleAttribute) {

    private val binding = WidgetPasswordPolicyBinding.inflate(LayoutInflater.from(context),this)


    /**
     * this method changes text and color for textview and imageview depending on if the password matches
     *
     * @param isValid : flag which responds depending on if password matches
     * @param text : what text needs to displayed when password doesn't match and match
     */
    fun checkConfirmPassword(isValid: Boolean, text: String){
        if (isValid) {
            setText(binding.matchTextView, text)
            setColor(isValid, binding.matchTextView, binding.checkboxMatch)
        } else {
            setText(binding.matchTextView, text)
            setColor(isValid, binding.matchTextView, binding.checkboxMatch)
        }
    }

    /**
     * this method sets default colors to text and checkbox for confirm Password scenario
     * as well password policy check scenario
     *
     * @param policyText : textview bind
     * @param policyCheckBox : imageview bind
     * @param text : the text needs to be displayed
     */
    fun setDefaultColor(policyText: TextView, policyCheckBox: ImageView, text: String) {
        //set text
        setText(policyText, text)

        //set color
        policyText.setTextColor( resources.getColor(R.color.white,null) )
        policyCheckBox.setImageResource(R.drawable.ic_check_box_outline_blank_white_24dp)
    }

    /**
     * this method set text and checkbox color depending on flag value is the password meets the conditions
     *
     * @param isValid : flag indicating if the entered password meets the condition
     * @param policyText : textview bind
     * @param policyCheckBox : imageview bind
     * @param text : the text needs to be displayed
     */
    fun setPasswordPolicyDetails(isValid: Boolean, policyText: TextView, policyCheckBox: ImageView, text: String) {
        //set text
        setText(policyText, text)

        //set color
        setColor(isValid, policyText,policyCheckBox)
    }

    /**
     * this method is used for visibility check
     *
     * @param showPasswordPolicy : boolean indicates when to visible/ invisible password policy layout
     */
    fun showPasswordPolicy(showPasswordPolicy: Boolean) {

        //lets test widget
        binding.checkPasswordPolicy.visibility = if (showPasswordPolicy) View.VISIBLE else View.GONE
    }

    /**
     * this method is used for visibility check
     *
     * @param showConfirmPassword : boolean indicates when to visible/ invisible confirm password layout
     */
    fun showConfirmPassword(showConfirmPassword: Boolean){
        //lets test widget
        binding.confirmPassword.visibility = if (showConfirmPassword) View.VISIBLE else View.GONE
    }

    /**
     * this private function will set color on textview and imageview
     *
     * @param isValid : this boolean will indicate when the individual password policy has meet the requirement
     * @param policyText : textview bind
     * @param policyCheckBox : imageview bind
     */
    private fun setColor(isValid: Boolean, policyText: TextView, policyCheckBox: ImageView){

        if( isValid ) {
            policyText.setTextColor( resources.getColor(R.color.passwordCheckGreenColor,null) )
            policyCheckBox.setImageResource(R.drawable.ic_check_box_green_24dp)
        }
        else {
            policyText.setTextColor( resources.getColor(R.color.colorError,null) )
            policyCheckBox.setImageResource(R.drawable.ic_check_box_outline_blank_red_24dp)
        }
    }

    /**
     * this private function will set text
     *
     * @param policyText : textview bind
     * @param passwordPolicyText : the text to be displayed on textview
     */
    private fun setText( policyText: TextView, passwordPolicyText: String){
        policyText.text = passwordPolicyText
    }

}