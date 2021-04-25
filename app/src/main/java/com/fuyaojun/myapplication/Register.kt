package com.fuyaojun.myapplication


import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.fuyaojun.myapplication.databinding.ActivityRegisterBinding


class Register : AppCompatActivity() {
    private  lateinit var binding:ActivityRegisterBinding
    private fun checkLoginAvailable(): Boolean {
        return !binding.userNumber.text.isNullOrBlank() and !binding.password.text.isNullOrBlank() and !binding.passwordAgain.text.isNullOrBlank()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            userNumber.addTextChangedListener{
                register.isEnabled = checkLoginAvailable()
            }
            password.addTextChangedListener {
                if (password.text.isEmpty()) {
                    eye.visibility = View.INVISIBLE

                } else {
                    eye.visibility = View.VISIBLE
                }
                register.isEnabled = checkLoginAvailable()
            }
            passwordAgain.addTextChangedListener {
                if (passwordAgain.text.isEmpty()) {
                    eyes.visibility = View.INVISIBLE

                } else {
                    eyes.visibility = View.VISIBLE
                }
                register.isEnabled = checkLoginAvailable()
            }
            eye.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    password.inputType = InputType.TYPE_CLASS_TEXT
                } else {
                    password.inputType =
                            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                }

            }
            eyes.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    passwordAgain.inputType = InputType.TYPE_CLASS_TEXT
                } else {
                    passwordAgain.inputType =
                            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                }

            }
        }
    }
}