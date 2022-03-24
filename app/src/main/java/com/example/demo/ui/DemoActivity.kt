package com.example.demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.demo.databinding.ActivityDemoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDemoBinding

    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoadData.setOnClickListener {
            viewModel.loadCurrencyList()
        }

        binding.btnSortByName.setOnClickListener {
            viewModel.sortCurrencyList()
        }

        observeSelectedCurrency()
    }

    private fun observeSelectedCurrency() {
        viewModel.selectedCurrency.observe(this) { currencyInfo ->
            Toast.makeText(this, currencyInfo.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}