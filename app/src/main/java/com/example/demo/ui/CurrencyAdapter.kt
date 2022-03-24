package com.example.demo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.data.CurrencyInfo
import com.example.demo.databinding.ItemCurrencyBinding

class CurrencyAdapter constructor(
    private val onItemSelected: (CurrencyInfo) -> Unit
) : ListAdapter<CurrencyInfo, CurrencyViewHolder>(
    CurrencyAdapterDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CurrencyViewHolder(
            ItemCurrencyBinding.inflate(inflater, parent, false),
            onItemSelected
        )
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CurrencyViewHolder constructor(
    private val binding: ItemCurrencyBinding,
    private val onItemSelected: (CurrencyInfo) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(currencyInfo: CurrencyInfo) {
        currencyInfo.name.firstOrNull()?.let { firstChar ->
            binding.tvCode.text = firstChar.toString()
        }

        binding.tvName.text = currencyInfo.name
        binding.tvSymbol.text = currencyInfo.symbol

        binding.root.setOnClickListener {
            onItemSelected(currencyInfo)
        }
    }
}

private class CurrencyAdapterDiffUtil : DiffUtil.ItemCallback<CurrencyInfo>() {

    override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
        return oldItem.javaClass == newItem.javaClass
    }

    override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
        return oldItem == newItem
    }
}