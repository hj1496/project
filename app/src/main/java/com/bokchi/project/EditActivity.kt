package com.bokchi.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.bokchi.project.databinding.ActivityEdit2Binding

class EditActivity : AppCompatActivity() {
    // EditiActivity.kt와 activity_edit2.xml이 바인딩 되어 있습니다
    // 헷갈리거나 필요하다면 추후에 수정하겠습니다
    private lateinit var binding: ActivityEdit2Binding
    lateinit var style1Id: ImageButton
    lateinit var style2Id: ImageButton
    lateinit var style3Id: ImageButton
    lateinit var style4Id: ImageButton

    lateinit var RelativeLayoutPic1: RelativeLayout
    lateinit var textViewYear1: TextView
    lateinit var textViewMonth1: TextView
    lateinit var textViewDate1: TextView
    lateinit var textViewHour1: TextView
    lateinit var textViewMinute1: TextView
    lateinit var textViewMeridiem1: TextView

    lateinit var RelativeLayoutPic2: RelativeLayout
    lateinit var textViewYear2: TextView
    lateinit var textViewMonth2: TextView
    lateinit var textViewDate2: TextView

    lateinit var RelativeLayoutPic3: RelativeLayout
    lateinit var textViewHour3: TextView
    lateinit var textViewMinute3: TextView
    lateinit var textViewMonth3: TextView
    lateinit var textViewDate3: TextView

    lateinit var RelativeLayoutPic4: RelativeLayout
    lateinit var textViewYear4: TextView
    lateinit var textViewMonth4: TextView
    lateinit var textViewDate4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEdit2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val yearmsg  = intent.getStringExtra("current-year")
        val monthmsg = intent.getStringExtra("current-month")
        val datemsg = intent.getStringExtra("current-date")
        val hourmsg = intent.getStringExtra("current-hour")
        val minutemsg = intent.getStringExtra("current-minute")
        val Meridiemmsg = intent.getStringExtra("current-Meridiem")

        binding.style1Id
        binding.style2Id
        binding.style3Id
        binding.style4Id

        RelativeLayoutPic1.visibility = View.INVISIBLE
//        binding.textViewYear1
//        binding.textViewMonth1
//        binding.textViewDate1
//        binding.textViewHour1
//        binding.textViewMinute1
//        binding.textViewMeridiem1

        RelativeLayoutPic2.visibility = View.INVISIBLE
        //binding.textViewYear2
        //binding.textViewMonth2
        //binding.textViewDate2

        RelativeLayoutPic3.visibility = View.INVISIBLE
//        binding.textViewHour3
//        binding.textViewMinute3
//        binding.textViewMonth3
//        binding.textViewDate3

        RelativeLayoutPic4.visibility = View.INVISIBLE
        //binding.textViewYear4
        //binding.textViewMonth4
        //binding.textViewDate4

        textViewYear1.text = yearmsg
        textViewMonth1.text = "." + monthmsg
        textViewDate1.text = "." + datemsg
        textViewHour1.text = hourmsg+":"
        textViewMinute1.text = minutemsg
        textViewMeridiem1.text = Meridiemmsg

        textViewYear2.text = yearmsg!!.substring(3,4)
        textViewMonth2.text = "/" + monthmsg
        textViewDate2.text = "/" + datemsg

        textViewHour3.text = hourmsg+":"
        textViewMinute3.text = minutemsg
        textViewMonth3.text = monthmsg+"/"
        textViewDate3.text = datemsg

        textViewYear4.text = yearmsg
        textViewMonth4.text = monthmsg+"월"
        textViewDate4.text = datemsg

        style1Id.setOnClickListener{
            if(style1Id.isPressed) {
                RelativeLayoutPic1.visibility = View.VISIBLE
//                textViewYear1.visibility = View.VISIBLE
//                textViewMonth1.visibility = View.VISIBLE
//                textViewDate1.visibility = View.VISIBLE
//                textViewHour1.visibility = View.VISIBLE
//                textViewMinute1.visibility = View.VISIBLE
//                textViewMeridiem1.visibility = View.VISIBLE
            }
            else{
//                textViewYear1.visibility = View.INVISIBLE
//                textViewMonth1.visibility = View.INVISIBLE
//                textViewDate1.visibility = View.INVISIBLE
//                textViewHour1.visibility = View.INVISIBLE
//                textViewMinute1.visibility = View.INVISIBLE
//                textViewMeridiem1.visibility = View.INVISIBLE
            }
        }

        style2Id.setOnClickListener{
            if(style2Id.isPressed) {
                RelativeLayoutPic2.visibility = View.VISIBLE
                //textViewYear2.visibility = View.VISIBLE
                //textViewMonth2.visibility = View.VISIBLE
                //textViewDate2.visibility = View.VISIBLE
            }
            else{
//                textViewYear2.visibility = View.INVISIBLE
//                textViewMonth2.visibility = View.INVISIBLE
//                textViewDate2.visibility = View.INVISIBLE
            }
        }

        style3Id.setOnClickListener{
            if(style3Id.isPressed) {
                RelativeLayoutPic3.visibility = View.VISIBLE
//                textViewHour3.visibility = View.VISIBLE
//                textViewMinute3.visibility = View.VISIBLE
//                textViewMonth3.visibility = View.VISIBLE
//                textViewDate3.visibility = View.VISIBLE
            }
            else{
//                textViewHour3.visibility = View.INVISIBLE
//                textViewMinute3.visibility = View.INVISIBLE
//                textViewMonth3.visibility = View.INVISIBLE
//                textViewDate3.visibility = View.INVISIBLE
            }
        }

        style4Id.setOnClickListener{
            if(style4Id.isPressed) {
                RelativeLayoutPic4.visibility = View.VISIBLE
                //textViewYear4.visibility = View.VISIBLE
                //textViewMonth4.visibility = View.VISIBLE
                //textViewDate4.visibility = View.VISIBLE
            }
            else{
//                textViewYear4.visibility = View.INVISIBLE
//                textViewMonth4.visibility = View.INVISIBLE
//                textViewDate4.visibility = View.INVISIBLE
            }
        }


    }
}