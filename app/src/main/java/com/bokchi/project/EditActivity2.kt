package com.bokchi.project

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import com.bokchi.project.databinding.ActivityEdit2Binding
import com.bokchi.project.databinding.ActivityEditActivtiyBinding
import com.bokchi.project.databinding.ActivityMainBinding
import java.util.*

class EditActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityEditActivtiyBinding
    lateinit var cutId: ImageButton

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditActivtiyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cutId
        cutId.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                getTime()
            }
        }

    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun getTime() {
        // 현재 시간을 밀리초 단위로 받아옴
        val currentMills = System.currentTimeMillis()
        // UI 편집 위해 변수 연월일 시분 각각 생성
        // 설정 국가(한국)의 현재 시간 받아오기
        val sdfYear = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("yyyy", Locale.KOREAN)
        } else {
            // API 21 이상 버전 요구
            TODO("VERSION.SDK_INT < N")
        }
        val sdfMonth = SimpleDateFormat("MM", Locale.KOREAN)
        val sdfDate = SimpleDateFormat("dd", Locale.KOREAN)
        val sdfHour = SimpleDateFormat("HH", Locale.KOREAN)
        val sdfMin = SimpleDateFormat("mm", Locale.KOREAN)
        val sdfMid = SimpleDateFormat("aa", Locale.KOREAN)
        // 초는 필요한 경우 추가
        //val sdfSec = SimpleDateFormat("dd", Locale.KOREAN)
        // 받아온 시간(long)을 String으로 format
        val displayTextY = sdfYear.format(currentMills)
        val displayTextM = sdfMonth.format(currentMills)
        val displayTextD = sdfDate.format(currentMills)
        val displayTextH = sdfHour.format(currentMills)
        val displayTextMin = sdfMin.format(currentMills)
        val displayTextMid = sdfMid.format(currentMills)
        //val displayTextS = sdfSec.format(currentMills)
        // EditActivity로 넘겨주기
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("current-year", displayTextY)
        intent.putExtra("current-month", displayTextM)
        intent.putExtra("current-date", displayTextD)
        intent.putExtra("current-hour", displayTextH)
        intent.putExtra("current-minute", displayTextMin)
        intent.putExtra("current-Meridiem", displayTextMid)
        //intent.putExtra("current-second", displayTextS)
        startActivity(intent)
    }
}