package com.fuyaojun.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.fuyaojun.myapplication.databinding.ActivityCalculatorBinding

class Calculator : AppCompatActivity() {
    private val currentText = StringBuilder()
    private lateinit var binding:ActivityCalculatorBinding

    companion object {
        val dotValidRegex = Regex("[.][0-9]*$")
        val plusRegex=Regex("[+]")
        val minusRegex=Regex("[-]")
        val multiplyRegex=Regex("[*]")
        val divideRegex=Regex("[/]")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            listOf(zero, one, two,three,four,five,six,seven,eight,nine).forEach {
                it.setOnClickListener(numberClickListener)
            }
        }

        binding.apply {
            plus.setOnClickListener { appendOperatorWithValidation("+") }
            minus.setOnClickListener { appendOperatorWithValidation("-") }
            multiplication.setOnClickListener { appendOperatorWithValidation("*") }
            fraction.setOnClickListener { appendOperatorWithValidation("/") }

            dot.setOnClickListener {
                if (checkDotAvailability()) {
                    appendInput(".")
                }
            }

            clear.setOnClickListener {
                clearCurrentText()
            }

            delete.setOnClickListener {
                when(currentText.length){
                    0->{}
                    1->{
                        clearCurrentText()
                    }
                    else->{
                        currentText.setLength(currentText.length-1)
                        updateTextView()
                    }
                }
            }
        }

        binding.equal.setOnClickListener{

        }
    }

    private fun clearCurrentText(){
        currentText.clear()
        binding.number.text = "0"
    }

    private val numberClickListener = View.OnClickListener {
        val buttonView = it as Button
        val buttonString = buttonView.text
        appendInput(buttonString.toString())
    }

    private fun isTextReachLimit(): Boolean {
        return currentText.length > 16
    }

    private val operatorList = listOf("+", "-", "*", "/")

    private fun updateTextView() {
        binding.number.text = currentText
                .replace(plusRegex,"+")
                .replace(minusRegex,"−")
                .replace(multiplyRegex,"×")
                .replace(divideRegex,"÷")
    }

    private fun appendInput(toAppend: String) {
        if (isTextReachLimit()) {
            return
        }
        currentText.append(toAppend)
        updateTextView()
    }

    private fun appendOperatorWithValidation(operator: String) {
        if (isTextReachLimit()) {
            return
        }
        if (currentText.isNotEmpty() && !operatorList.contains(currentText.last().toString())) {
            currentText.append(operator)
            updateTextView()
        }
    }

    private fun checkDotAvailability(): Boolean {
        return dotValidRegex.findAll(currentText.toString()).none()
    }
}