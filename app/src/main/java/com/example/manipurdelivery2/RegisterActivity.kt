package com.example.manipurdelivery2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.manipurdelivery2.databinding.ActivityRegisterBinding
import kotlin.random.Random

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var generatedOtp: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendOtpButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()

            if (name.isEmpty()) {
                binding.nameEditText.error = "Name is required"
                return@setOnClickListener
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailEditText.error = "Invalid email address"
                return@setOnClickListener
            }

            generatedOtp = (1000..9999).random().toString()

            Toast.makeText(this, "OTP sent to email: $generatedOtp", Toast.LENGTH_LONG).show()


            val intent = Intent(this, OtpVerifyActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("otp", generatedOtp)
            startActivity(intent)
        }
    }
}
