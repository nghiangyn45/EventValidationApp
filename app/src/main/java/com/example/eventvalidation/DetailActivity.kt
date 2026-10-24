package com.example.eventvalidation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eventvalidation.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("EXTRA_NAME") ?: ""
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: ""
        val msv = intent.getStringExtra("EXTRA_MSV") ?: ""
        val clazz = intent.getStringExtra("EXTRA_CLASS") ?: ""

        val resultText = """
            - Họ và tên: $name
            - Email: $email
            - Số điện thoại: $phone
            - Mã sinh viên: $msv
            - Lớp: $clazz
        """.trimIndent()

        binding.tvResult.text = resultText

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}