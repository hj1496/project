package com.bokchi.project

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bokchi.project.databinding.ActivityMainBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding//뷰바인딩

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)//뷰바인딩 세팅
        setContentView(binding.root)

        setPermission() // 권한체크 메소드


        binding.camId.setOnClickListener {

            val intent: Intent = Intent(applicationContext, Camera::class.java)
            startActivity(intent)
        }


    }

    // 테트 퍼미션 설정
    private fun setPermission() {

        val permission = object : PermissionListener {
            override fun onPermissionGranted() { // 설정해놓은 위험권한들이 허용 되었을 경우 사용하는 부분
                Toast.makeText(this@MainActivity, "권한이 허용 되었습니다.", Toast.LENGTH_SHORT).show()

            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) { // 설정해놓은 위험 권한들을 거부 한 경우 사용할 부분
                Toast.makeText(this@MainActivity, "권한이 거부 되었습니다.", Toast.LENGTH_SHORT).show()
                setPermission()//권한 재요청
            }
        }

        TedPermission.create()
            .setPermissionListener(permission)
            .setRationaleMessage("카메라 앱을 사용하시려면 권한을 허용해주세요.")
            .setDeniedMessage("권한을 거부하셨습니다. [앱 설정] -> [권한] 항목에서 허용해주세요.")
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.CAMERA  )
            .check()

    }

}