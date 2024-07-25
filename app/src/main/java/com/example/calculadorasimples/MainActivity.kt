package com.example.calculadorasimples

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var lastOperator: String? = null
    private var lastValue: Double? = null
    private var isNewInput = true
    private var isLastButtonOperator = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val numberButtons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.buttonDot
        )
        numberButtons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onNumberClick((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.buttonAdd).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.buttonSubtract).setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener { onOperatorClick("*") }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { onOperatorClick("/") }
        findViewById<Button>(R.id.buttonPower).setOnClickListener { onOperatorClick("^") }
        findViewById<Button>(R.id.buttonSqrt).setOnClickListener { onSqrtClick() }
        findViewById<Button>(R.id.buttonEqual).setOnClickListener { onEqualClick() }
        findViewById<Button>(R.id.buttonDelete).setOnClickListener { onDeleteClick() }
        findViewById<Button>(R.id.buttonC).setOnClickListener { onClearClick() }
    }

    private fun onNumberClick(number: String) {
        if (isNewInput) {
            display.text = number
            isNewInput = false
        } else {
            display.append(number)
        }
        isLastButtonOperator = false
    }

    private fun onOperatorClick(operator: String) {
        if (isNewInput) {
            // Do nothing if the input is new and operator is clicked.
            return
        }

        val currentValue = display.text.toString().toDoubleOrNull()
        if (currentValue != null) {
            if (lastValue != null && lastOperator != null && !isLastButtonOperator) {
                val result = calculate(lastValue!!, currentValue, lastOperator!!)
                display.text = result.toString()
                lastValue = result
            } else {
                lastValue = currentValue
            }
            lastOperator = operator
            isNewInput = true
            isLastButtonOperator = true
        }
    }

    private fun onSqrtClick() {
        val currentValue = display.text.toString().toDoubleOrNull() ?: 0.0
        if (currentValue >= 0) {
            val result = sqrt(currentValue)
            display.text = result.toString()
            lastValue = result
            lastOperator = null
            isNewInput = true
        } else {
            display.text = "Error" // Handle negative number case
        }
    }

    private fun onEqualClick() {
        val currentValue = display.text.toString().toDoubleOrNull() ?: 0.0
        if (lastValue != null && lastOperator != null) {
            val result = calculate(lastValue!!, currentValue, lastOperator!!)
            display.text = result.toString()
            lastValue = result
            lastOperator = null
        } else {
            // If no previous operator, just update lastValue with current input
            lastValue = currentValue
        }
        isNewInput = true
    }

    private fun onDeleteClick() {
        val text = display.text.toString()
        if (text.isNotEmpty()) {
            display.text = text.dropLast(1)
            if (display.text.isEmpty()) {
                display.text = "0"
                isNewInput = true
            }
        }
    }

    private fun onClearClick() {
        display.text = "0"
        lastValue = null
        lastOperator = null
        isNewInput = true
        isLastButtonOperator = false
    }

    private fun calculate(value1: Double, value2: Double, operator: String): Double {
        return when (operator) {
            "+" -> value1 + value2
            "-" -> value1 - value2
            "*" -> value1 * value2
            "/" -> if (value2 != 0.0) value1 / value2 else Double.NaN // Avoid division by zero
            "^" -> value1.pow(value2) // Power function
            else -> 0.0
        }
    }
}
