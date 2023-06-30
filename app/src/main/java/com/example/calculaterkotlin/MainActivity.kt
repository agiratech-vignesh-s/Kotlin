package com.example.calculaterkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.calculaterkotlin.R.id.textView

class MainActivity : AppCompatActivity() {
    private var textInput: TextView? = null
    var lastNumber: Boolean = true
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textInput = findViewById(R.id.textView)
    }

    fun onClick(view: View) {
        textInput?.append((view as Button).text)
//        Toast.makeText(this,"Button Clicked",Toast.LENGTH_LONG).show()
    }

    fun clearBtn(view: View) {
        textInput?.text = ""
    }

    fun onDotClicked(view: View) {
        if (lastNumber && !lastDot) {
            textInput?.append(".")
            lastNumber = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        textInput?.text.let {
            if (lastNumber && !onOperatorAdded(it.toString())) {
                textInput?.append((view as Button).text)
                lastNumber = true
                lastDot = false
            }
        }
    }

    fun onOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("+") || value.contains("-") || value.contains("*")
        }
    }

    private fun removeZero(value: String): String {
        var result = value
        if (result.contains(".0"))
            result = result.substring(0, result.length - 2)
        return result
    }

    fun onEqual(view: View) {
        if (lastNumber) {
            var value = textInput?.text.toString()
            var prefix: String = ""
            try {
                if (value.startsWith("-")) {
                    prefix = "-"
                    value = value.substring(1)
                }
                if (value.contains("-")) {
                    val splitValur = value.split("-")
                    var first = splitValur[0]
                    var second = splitValur[1]
                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    textInput?.text = (first.toDouble() - second.toDouble()).toString()
                } else if (value.contains("+")) {
                    val splitValur = value.split("+")
                    var first = splitValur[0]
                    var second = splitValur[1]
                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    textInput?.text = (first.toDouble() + second.toDouble()).toString()
                } else if (value.contains("*")) {
                    val splitValur = value.split("*")
                    var first = splitValur[0]
                    var second = splitValur[1]
                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    textInput?.text = (first.toDouble() * second.toDouble()).toString()
                } else if (value.contains("/")) {
                    val splitValur = value.split("/")
                    var first = splitValur[0]
                    var second = splitValur[1]
                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    textInput?.text = removeZero((first.toDouble() / second.toDouble()).toString())
                }


            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }

    }
}