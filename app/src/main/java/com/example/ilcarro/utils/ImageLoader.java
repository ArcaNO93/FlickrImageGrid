package com.example.ilcarro.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.ilcarro.R;
import com.squareup.picasso.Picasso;

public class ImageLoader {

    @BindingAdapter({"android:src", "app:errorImage"})
    public static void loadImage(ImageView view, String url, Drawable errorImage) {
        Picasso.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.half_transparent_shape)
                .error(errorImage)
                .into(view);
    }
}