package org.d3if0057.calculator

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import org.d3if0057.calculator.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switch : Switch = findViewById(R.id.switch1)

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        binding.button0.setOnClickListener{
            binding.input.text = addToInputText("0")
        }
        binding.button1.setOnClickListener{
            binding.input.text = addToInputText("1")
        }
        binding.button2.setOnClickListener{
            binding.input.text = addToInputText("2")
        }
        binding.button3.setOnClickListener{
            binding.input.text = addToInputText("3")
        }
        binding.button4.setOnClickListener{
            binding.input.text = addToInputText("4")
        }
        binding.button5.setOnClickListener{
            binding.input.text = addToInputText("5")
        }
        binding.button6.setOnClickListener{
            binding.input.text = addToInputText("6")
        }
        binding.button7.setOnClickListener{
            binding.input.text = addToInputText("7")
        }
        binding.button8.setOnClickListener{
            binding.input.text = addToInputText("8")
        }
        binding.button9.setOnClickListener{
            binding.input.text = addToInputText("9")
        }

        binding.buttonBracketLeft.setOnClickListener{
            binding.input.text = addToInputText("(")
        }
        binding.buttonBracketRight.setOnClickListener{
            binding.input.text = addToInputText(")")
        }

        binding.buttonAddition.setOnClickListener{
            binding.input.text = addToInputText("+")
        }
        binding.buttonDivision.setOnClickListener{
            binding.input.text = addToInputText("÷")
        }
        binding.buttonMultiply.setOnClickListener{
            binding.input.text = addToInputText("×")
        }
        binding.buttonSubtraction.setOnClickListener{
            binding.input.text = addToInputText("-")
        }
        binding.buttonDot.setOnClickListener{
            binding.input.text = addToInputText(".")
        }

        binding.buttonClear.setOnClickListener{
            binding.input.text = ""
            binding.output.text = ""
        }

        binding.buttonEquals.setOnClickListener {
            showResult()
        }


    }

    private fun addToInputText(buttonValue: String): String {
        return "${binding.input.text}$buttonValue"
    }

    private fun getInputExpression(): String {
        var expression = binding.input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
                binding.output.text = "Error"
                binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                // Show Result
                binding.output.text = result.toString()
                binding.output.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
        } catch (e: Exception) {
            // Show Error Message
            binding.output.text = "Error"
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }
}