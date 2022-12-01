package com.bokchi.project

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.bokchi.project.databinding.ActivityCameraBinding
import com.bokchi.project.databinding.ActivityMainBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

class Camera : AppCompatActivity() {


    lateinit var binding : ActivityCameraBinding//뷰바인딩
    lateinit var bitmap: Bitmap
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPermission() // 권한체크 메소드

        imageView = binding.imageViewId
        val picBtn: Button = binding.takeAPictureId

        //촬영 이벤트
        picBtn.setOnClickListener {
            val intent: Intent = Intent(MediaStore. ACTION_IMAGE_CAPTURE)
            activiteResult.launch(intent)
        }


        //이미지 클릭시 다음 editing 화면 이동
        imageView.setOnClickListener{
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

    // 테트 퍼미션 설정
    private fun setPermission() {

        val permission = object : PermissionListener {
            override fun onPermissionGranted() { // 설정해놓은 위험권한들이 허용 되었을 경우 사용하는 부분
                Toast.makeText(this@Camera, "권한이 허용 되었습니다.", Toast.LENGTH_SHORT).show()

            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) { // 설정해놓은 위험 권한들을 거부 한 경우 사용할 부분
                Toast.makeText(this@Camera, "권한이 거부 되었습니다.", Toast.LENGTH_SHORT).show()

            }
        }

        TedPermission.create()
            .setPermissionListener(permission)
            .setRationaleMessage("카메라 앱을 사용하시려면 권한을 허용해주세요.")
            .setDeniedMessage("권한을 거부하셨습니다. [앱 설정] -> [권한] 항목에서 허용해주세요.")
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
            .check()

    }
}