package com.example.eventvalidation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.eventvalidation.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRealtimeValidation()

        binding.btnRegister.setOnClickListener {
            validateAndSubmit()
        }
    }

    private fun setupRealtimeValidation() {
        binding.etName.doAfterTextChanged { binding.tilName.error = null }
        binding.etEmail.doAfterTextChanged { binding.tilEmail.error = null }
        binding.etPhone.doAfterTextChanged { binding.tilPhone.error = null }
        binding.etMsv.doAfterTextChanged { binding.tilMsv.error = null }
        binding.etClass.doAfterTextChanged { binding.tilClass.error = null }
    }

    private fun validateAndSubmit() {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val msv = binding.etMsv.text.toString().trim()
        val clazz = binding.etClass.text.toString().trim()

        var isValid = true

        if (name.isBlank()) {
            binding.tilName.error = "Họ tên không được để trống"
            isValid = false
        }

        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Email không hợp lệ"
            isValid = false
        }

        val phoneRegex = "^(03|05|07|08|09)\\d{8}$".toRegex()
        if (phone.isBlank() || !phone.matches(phoneRegex)) {
            binding.tilPhone.error = "Số điện thoại phải có 10 chữ số hợp lệ"
            isValid = false
        }

        if (msv.isBlank()) {
            binding.tilMsv.error = "Mã sinh viên không được để trống"
            isValid = false
        }

        if (clazz.isBlank()) {
            binding.tilClass.error = "Lớp không được để trống"
            isValid = false
        }

        if (isValid) {
            Snackbar.make(binding.root, "Đăng ký thành công!", Snackbar.LENGTH_LONG)
                .setAction("XEM CHI TIẾT") {
                    val intent = Intent(this, DetailActivity::class.java).apply {
                        putExtra("EXTRA_NAME", name)
                        putExtra("EXTRA_EMAIL", email)
                        putExtra("EXTRA_PHONE", phone)
                        putExtra("EXTRA_MSV", msv)
                        putExtra("EXTRA_CLASS", clazz)
                    }
                    startActivity(intent)
                }
                .show()
        } else {
            Snackbar.make(binding.root, "Vui lòng kiểm tra lại các lỗi trên form", Snackbar.LENGTH_SHORT).show()
        }
    }
}