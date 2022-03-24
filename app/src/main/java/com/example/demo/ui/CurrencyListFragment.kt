package com.example.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.demo.databinding.FragmentCurrencyListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    private val viewModel: CurrencyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCurrencyListBinding.inflate(inflater, container, false)

        val currencyAdapter = CurrencyAdapter(viewModel::selectCurrency)
        binding.rvCurrencyList.adapter = currencyAdapter
        viewModel.currencyList.observe(viewLifecycleOwner) { currencyList ->
            binding.tvEmptyList.visibility = View.GONE
            currencyAdapter.submitList(currencyList)
        }

        return binding.root
    }
}