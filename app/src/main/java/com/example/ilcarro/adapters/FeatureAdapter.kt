package com.example.ilcarro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ilcarro.databinding.TextViewRowBinding
import com.example.ilcarro.ui.viewModels.mainFlow.LetTheCarWorkCarDetailsLastViewModel

class FeatureAdapter (val viewModel: LetTheCarWorkCarDetailsLastViewModel): RecyclerView.Adapter<FeatureAdapter.FeaturesViewHolder>() {

    private var mFeatures: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturesViewHolder {
        val binding = TextViewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.feature = ""
        binding.viewModel = viewModel
        return FeaturesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeaturesViewHolder, position: Int) {
        holder.binding.feature = mFeatures[position]
    }

    fun setFeatures(features: List<String>) {
        mFeatures = features
        notifyDataSetChanged()
    }

    override fun getItemCount() = mFeatures.size

    inner class FeaturesViewHolder(val binding: TextViewRowBinding) : RecyclerView.ViewHolder(binding.root)
}