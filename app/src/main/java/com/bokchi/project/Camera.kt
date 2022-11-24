package com.bokchi.project

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.Toast
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

    val REQUEST_IMAGE_CAPTURE = 1 //카메라 촬영 요청 코드
    lateinit var currentPhotoPath: String //문자열 형태 사진 경로 값
    lateinit var binding : ActivityCameraBinding//뷰바인딩
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        setPermission() // 권한체크 메소드

        binding.takeAPictureId.setOnClickListener { 
            takeCapture() // 기본 카메라 앱을 실행하여 사진 촬영
        }
    }

    // 사진 촬영
    private fun takeCapture() {
        // 기본 카메라 앱 실행
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also{
                val photoFile: File? = try{
                    createImageFile()
                }catch (ex: IOException){
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.bokchi.project.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    //startActivityForResult가 deprecated 되어 registerForActivityResult를 사용해야한다...
                }
            }
        }
    }

    // 이미지 파일 생성
    private fun createImageFile(): File {
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", storageDir)
            .apply { currentPhotoPath = absolutePath }
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
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            .check()

    }
}