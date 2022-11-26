package com.bokchi.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bokchi.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding//뷰바인딩

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)//뷰바인딩 세팅
        setContentView(binding.root)

        binding.camId.setOnClickListener {
            val intent = Intent(this, Camera::class.java)
            startActivity(intent)
        }

    }
}