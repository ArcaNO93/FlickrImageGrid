package com.example.ilcarro.adapters

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ilcarro.databinding.PhotosListRowBinding

class PhotosListAdapter : RecyclerView.Adapter<PhotosListAdapter.PhotosListViewHolder>() {

    private var mImages: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosListViewHolder {
        val binding = PhotosListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosListViewHolder, position: Int) {
        holder.binding.image.setImageURI(Uri.parse(mImages[position]))
    }

    fun addImage(image: String) {
        mImages.add(image)
        Log.d("tag", mImages.toString())
        notifyDataSetChanged()
    }

    fun removeImage(image: String) {
        mImages.remove(image)
        notifyDataSetChanged()
    }

    override fun getItemCount() = mImages.size

    inner class PhotosListViewHolder(val binding: PhotosListRowBinding) : RecyclerView.ViewHolder(binding.root)
}