package com.example.tipcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import com.example.tipcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bill: Double? = null

        binding.billValueEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(s: Editable?) {
                binding.billValueTv.text = "Bill Value: $$s"

                bill = s.toString().toDoubleOrNull()

                changeInfo(bill, binding.seekBar.progress)
            }
        })

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val tip = progress.toString()
                binding.percentTv.text = "Tip: $tip%"

                changeInfo(bill, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    @SuppressLint("SetTextI18n")
    fun changeInfo(bill: Double?, progress: Int) {
        if (bill != null) {
            val (tipAmount, finalAmount) = countAmount(bill, progress)

            binding.tipAmountTv.text = getString(R.string.tip_amount, tipAmount.toString())
            binding.finalAmount.text = getString(R.string.final_amount, finalAmount.toString())
        } else {
            binding.tipAmountTv.text = getString(R.string.tip_amount, "")
            binding.finalAmount.text = getString(R.string.final_amount, "")
        }
    }

    private fun countAmount(bill: Double, tipPercent: Int): Pair<String, String> {
        val tipAmount = bill * tipPercent / 100
        val finalAmount = bill + tipAmount

        val tipAmountString = String.format("%.2f", tipAmount)
        val finalAmountString = String.format("%.2f", finalAmount)

        return Pair(tipAmountString, finalAmountString)
    }
}















