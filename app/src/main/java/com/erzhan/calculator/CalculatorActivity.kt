package com.erzhan.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.ArithmeticException
import java.util.*

class CalculatorActivity : AppCompatActivity() {
    private lateinit var calculator: Calculator
    private lateinit var inputEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculator = Calculator()
        inputEditText = findViewById(R.id.inputEditTextId)


    }

    fun onNumericButtonClick(view: View) {
        val text = (view as Button).text.toString()
        calculator.addDigit(text.toInt())
        updateInputField()
    }

    fun onBinaryOperationClick(view: View) {
        val text = view.tag.toString().toUpperCase(Locale.getDefault())
        try {
            calculator.performBinaryOperation(Calculator.Operation.valueOf(text))
        }catch (exception: ArithmeticException){
            reportError("Cannot divide by zero")
        }
        updateInputField()
    }

    fun onCalculateResultButtonClick(view: View) {
        try {
            calculator.calculate()
        }catch (exception: ArithmeticException){
            reportError("Cannot divide by zero")
        }
        updateInputField()
    }

    fun onClearButtonClick(view: View?) {
        calculator.reset()
        updateInputField()
    }

    fun onChangeSignButtonClick(view: View?) {
        calculator.negate()
        updateInputField()
    }

    fun onDecimalPointButtonClick(view: View?) {
        calculator.addDecimalPoint()
        updateInputField()
    }

    private fun reportError(errorMessage: CharSequence) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun updateInputField() {
        inputEditText.setText(calculator.currentValue.toPlainString())
    }

}