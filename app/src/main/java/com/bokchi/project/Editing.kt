package com.bokchi.project

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bokchi.project.databinding.ActivityEditingBinding

class Editing : AppCompatActivity() {
    lateinit var binding : ActivityEditingBinding//뷰바인딩
    lateinit var bitmap: Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageViewId.setImageBitmap(intent.getParcelableExtra<Bitmap>("bitmap_img"))
    }

}