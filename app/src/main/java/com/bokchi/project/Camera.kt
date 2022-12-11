package com.bokchi.project

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bokchi.project.databinding.ActivityCameraBinding


class Camera : AppCompatActivity() {


    lateinit var binding : ActivityCameraBinding//뷰바인딩
    lateinit var bitmap: Bitmap
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)



        imageView = binding.imageViewId
        val picBtn= binding.takeAPictureId

        //촬영 이벤트
        picBtn.setOnClickListener {
            val intent: Intent = Intent(MediaStore. ACTION_IMAGE_CAPTURE)
            activiteResult.launch(intent)
        }


        //버튼 클릭시 다음 editing 화면 이동
        binding.nextId.setOnClickListener{
            val intent: Intent = Intent(applicationContext, Editing::class.java)
            intent.apply { this.putExtra("bitmap_img", bitmap) }

            startActivity(intent)
        }

    }

    //결과 가져오기
    private val activiteResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){

        if(it.resultCode == RESULT_OK && it.data != null){
            //값 담기
            val extras = it.data!!.extras
            bitmap = extras?.get("data") as Bitmap
            imageView.setImageBitmap(bitmap)
        }

    }


}