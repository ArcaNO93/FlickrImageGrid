package com.example.ilcarro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ilcarro.databinding.PhotosListRowBinding
import com.example.ilcarro.ui.viewModels.mainFlow.LetTheCarWorkCarDetailsLastViewModel

class PhotosListAdapter (val viewModel: LetTheCarWorkCarDetailsLastViewModel): RecyclerView.Adapter<PhotosListAdapter.PhotosListViewHolder>() {

    private var mImages: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosListViewHolder {
        val binding = PhotosListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.viewModel = viewModel
        return PhotosListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosListViewHolder, position: Int) {
    }

    fun addImage(image: String) {
        mImages.add(image)
        notifyItemInserted(mImages.size - 1)
    }

    fun removeImage(image: String) {
        mImages.remove(image)
        notifyItemRemoved(mImages.size - 1)
    }

    fun clear() {
        mImages = mutableListOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = mImages.size

    inner class PhotosListViewHolder(val binding: PhotosListRowBinding) : RecyclerView.ViewHolder(binding.root)
}