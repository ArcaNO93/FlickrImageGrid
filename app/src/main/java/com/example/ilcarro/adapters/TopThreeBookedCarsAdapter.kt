package com.example.ilcarro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ilcarro.data.dto.car.ui.TopCarUI
import com.example.ilcarro.databinding.TopThreeRowBinding

class TopThreeBookedCarsAdapter : RecyclerView.Adapter<TopThreeBookedCarsAdapter.TopThreeCarsViewHolder>() {

    private var mCars: List<TopCarUI> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopThreeCarsViewHolder {
        val binding = TopThreeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopThreeCarsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopThreeCarsViewHolder, position: Int) {
        holder.binding.car = mCars[position]
    }

    fun setCars(cars: List<TopCarUI>) {
        mCars = cars
        notifyDataSetChanged()
    }

    override fun getItemCount() =
        mCars.size

    inner class TopThreeCarsViewHolder(val binding: TopThreeRowBinding) : RecyclerView.ViewHolder(binding.root)
}