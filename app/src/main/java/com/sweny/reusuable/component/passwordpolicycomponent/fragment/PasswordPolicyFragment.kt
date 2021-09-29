package com.sweny.reusuable.component.passwordpolicycomponent.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sweny.reusuable.component.passwordpolicycomponent.R
import com.sweny.reusuable.component.passwordpolicycomponent.databinding.FragmentPasswordPolicyBinding
import com.sweny.reusuable.component.passwordpolicycomponent.viewmodels.PasswordPolicyViewModel

class PasswordPolicyFragment : Fragment() {

    private lateinit var binding: FragmentPasswordPolicyBinding
    private val viewModel: PasswordPolicyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPasswordPolicyBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setUpObservers()
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setUpObservers() {
        viewModel.numericPassed.observe(viewLifecycleOwner, {
            with(binding.passwordPolicyComponent) {
                setPasswordPolicyDetails(
                    it,
                    binding.passwordPolicyComponent.findViewById(R.id.one_number),
                    binding.passwordPolicyComponent.findViewById(R.id.checkbox_one_number),
                    getString(R.string.criteria_one_number)
                )
            }
        })
        viewModel.upperLowerPassed.observe(viewLifecycleOwner, {
            with(binding.passwordPolicyComponent) {
                setPasswordPolicyDetails(
                    it,
                    binding.passwordPolicyComponent.findViewById(R.id.lower_upper),
                    binding.passwordPolicyComponent.findViewById(R.id.checkbox_lower_upper),
                    getString(R.string.criteria_upper_lower)
                )
            }
        })
        viewModel.specialPassed.observe(viewLifecycleOwner, {
            with(binding.passwordPolicyComponent) {
                setPasswordPolicyDetails(
                    it,
                    binding.passwordPolicyComponent.findViewById(R.id.one_special_char),
                    binding.passwordPolicyComponent.findViewById(R.id.checkbox_one_special_char),
                    getString(R.string.criteria_at_least_1_special)
                )
            }
        })
        viewModel.minMaxPassed.observe(viewLifecycleOwner, {
            with(binding.passwordPolicyComponent) {
                setPasswordPolicyDetails(
                    it,
                    binding.passwordPolicyComponent.findViewById(R.id.min_15_char),
                    binding.passwordPolicyComponent.findViewById(R.id.checkbox_min_15_char),
                    getString(R.string.criteria_min_8_15_len)
                )
            }
        })
        viewModel.newPassword1.observe(viewLifecycleOwner, {
            if (it?.length == 0) {
                with(binding.passwordPolicyComponent) {
                    setDefaultColor(
                        binding.passwordPolicyComponent.findViewById(R.id.min_15_char),
                        binding.passwordPolicyComponent.findViewById(R.id.checkbox_min_15_char),
                        getString(R.string.criteria_min_8_15_len)
                    )
                    setDefaultColor(
                        binding.passwordPolicyComponent.findViewById(R.id.one_special_char),
                        binding.passwordPolicyComponent.findViewById(R.id.checkbox_one_special_char),
                        getString(R.string.criteria_at_least_1_special)
                    )
                    setDefaultColor(
                        binding.passwordPolicyComponent.findViewById(R.id.lower_upper),
                        binding.passwordPolicyComponent.findViewById(R.id.checkbox_lower_upper),
                        getString(R.string.criteria_upper_lower)
                    )
                    setDefaultColor(
                        binding.passwordPolicyComponent.findViewById(R.id.one_number),
                        binding.passwordPolicyComponent.findViewById(R.id.checkbox_one_number),
                        getString(R.string.criteria_one_number)
                    )
                }
            } else {
                viewModel.validate()
            }
            enableNextButton(viewModel.newPassword2.value == viewModel.newPassword1.value)
        })
        viewModel.newPassword2.observe(viewLifecycleOwner, {
            checkConfirmation()
            with(binding.passwordPolicyComponent) {
                showConfirmPassword(true)
                showPasswordPolicy(false)
            }
            enableNextButton(viewModel.newPassword2.value == viewModel.newPassword1.value && viewModel.allPassed.value ?: false)
        })
        viewModel.allPassed.observe(viewLifecycleOwner, {
            enableNextButton(it)
            binding.editTextConfirmPassword.isEnabled = it
            binding.tilConfirmPassword.isEnabled = it
        })
    }

    /**
     * Check if new password and confirm password match and
     * set respective colours
     *
     */
    private fun checkConfirmation() {
        val confirmPassword = binding.editTextConfirmPassword.text.toString()
        val newPassword = binding.etNewPassword.text.toString()

        if (confirmPassword.isNotEmpty()) {
            if (confirmPassword == newPassword) {

                with(binding.passwordPolicyComponent) {
                    checkConfirmPassword(true, getString(R.string.passwords_match))
                }

                if (viewModel.allPassed.value ?: false)
                    enableNextButton(true)
            } else {

                with(binding.passwordPolicyComponent) {
                    checkConfirmPassword(false, getString(R.string.password_confirm_same))
                }
                enableNextButton(false)
            }
        } else {
            with(binding.passwordPolicyComponent) {
                setDefaultColor(
                    binding.passwordPolicyComponent.findViewById(R.id.matchTextView),
                    binding.passwordPolicyComponent.findViewById(R.id.checkbox_match),
                    getString(R.string.password_confirm_same)
                )
            }
        }
        viewModel.validate()
        clearFields()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enableNextButton(false)

        with(binding.passwordPolicyComponent) {
            showPasswordPolicy(false)
            showConfirmPassword(false)
        }

        setUpObservers()

        //listeners
        setUpListeners()
    }

    /**
     * listeners
     *
     * @param email
     */
    private fun setUpListeners() {

        binding.etNewPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                with(binding.passwordPolicyComponent) {
                    showConfirmPassword(false)
                    showPasswordPolicy(true)
                }
            }
        }
        binding.editTextConfirmPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                checkConfirmation()
                with(binding.passwordPolicyComponent) {
                    showConfirmPassword(true)
                    showPasswordPolicy(false)
                }
                view?.invalidate()
            }
        }
        //scroll up to the maximum when the edit text filed is focused and the keyboard is showing
        binding.clSubmain.viewTreeObserver.addOnGlobalLayoutListener {
            val heightDiff = (binding.clSubmain.height) + 200 - binding.clSubmain.height

            if (heightDiff > 100) { // Value should be less than keyboard's height
                binding.nestedScrollView2.postDelayed({
                    binding.nestedScrollView2.scrollBy(
                        0,
                        binding.clSubmain.height + 300
                    )
                }, 100)
            } else {
                binding.nestedScrollView2.postDelayed(
                    { binding.nestedScrollView2.scrollBy(0, 0) },
                    100
                )
            }
        }
    }

    private fun clearFields() {
        if (viewModel.allPassed.value == true) {
            with(binding.passwordPolicyComponent) {
                binding.passwordPolicyComponent.findViewById<ConstraintLayout>(R.id.checkPasswordPolicy).visibility = GONE
                binding.passwordPolicyComponent.findViewById<ConstraintLayout>(R.id.confirmPassword).visibility = VISIBLE
            }
        }
    }

    private fun enableNextButton(enabled: Boolean) {
        val drawable = if (enabled) R.drawable.lightpurple_rectangle
        else R.drawable.lightgrey_rectangle
        binding.saveButton.setBackgroundResource(drawable)
        binding.saveButton.isEnabled = enabled
    }
}
