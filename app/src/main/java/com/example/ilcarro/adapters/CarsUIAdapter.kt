package com.example.ilcarro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ilcarro.data.dto.car.ui.ShowCarUI
import com.example.ilcarro.databinding.TopThreeRowBinding

class CarsUIAdapter : RecyclerView.Adapter<CarsUIAdapter.TopThreeCarsViewHolder>() {

    private var mCars: List<ShowCarUI> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopThreeCarsViewHolder {
        val binding = TopThreeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopThreeCarsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopThreeCarsViewHolder, position: Int) {
        holder.binding.car = mCars[position]
    }

    fun setCars(cars: List<ShowCarUI>) {
        mCars = cars
        notifyDataSetChanged()
    }

    override fun getItemCount() = mCars.size

    inner class TopThreeCarsViewHolder(val binding: TopThreeRowBinding) : RecyclerView.ViewHolder(binding.root)
}