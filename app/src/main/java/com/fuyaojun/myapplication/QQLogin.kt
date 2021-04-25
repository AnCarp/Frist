package com.fuyaojun.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.fuyaojun.myapplication.databinding.ActivityQqLoginBinding

class QQLogin : AppCompatActivity() {
    private lateinit var binding: ActivityQqLoginBinding

    private fun checkLoginAvailable(): Boolean {
        return !binding.idNumber.text.isNullOrBlank() and !binding.qqPwd.text.isNullOrBlank()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQqLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            idNumber.addTextChangedListener {
                if (idNumber.text.isEmpty()) {
                    delete.visibility = View.INVISIBLE

                } else {
                    delete.visibility = View.VISIBLE
                }
                login.isEnabled = checkLoginAvailable()
            }
            qqPwd.addTextChangedListener {
                if (qqPwd.text.isEmpty()) {
                    eye.visibility = View.INVISIBLE

                } else {
                    eye.visibility = View.VISIBLE
                }
                login.isEnabled = checkLoginAvailable()
            }
            delete.setOnClickListener {
                idNumber.text.clear()
            }

            eye.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    qqPwd.inputType = InputType.TYPE_CLASS_TEXT
                } else {
                    qqPwd.inputType =
                            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
            }

            qqRegister.setOnClickListener {
                val intent = Intent(this@QQLogin, Register::class.java)
                startActivity(intent)
            }
        }
    }
}