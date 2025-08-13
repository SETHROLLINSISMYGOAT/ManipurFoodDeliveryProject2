package com.example.manipurdelivery2



import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.manipurdelivery2.databinding.ActivityOtpVerifyBinding

class OtpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpVerifyBinding
    private var expectedOtp: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        expectedOtp = intent.getStringExtra("otp") ?: ""

        setupOtpInput()

        binding.signInButton.setOnClickListener {
            val enteredOtp = getEnteredOtp()
            if (enteredOtp.length != 4) {
                Toast.makeText(this, "Please enter the 4-digit OTP", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (enteredOtp == expectedOtp) {
                Toast.makeText(this, "SignIn Successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, OrderDetailsActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun setupOtpInput() {
        moveFocus(binding.otpInput1, binding.otpInput2)
        moveFocus(binding.otpInput2, binding.otpInput3)
        moveFocus(binding.otpInput3, binding.otpInput4)
    }

    private fun moveFocus(current: EditText, next: EditText) {
        current.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) next.requestFocus()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun getEnteredOtp(): String {
        return binding.otpInput1.text.toString() +
                binding.otpInput2.text.toString() +
                binding.otpInput3.text.toString() +
                binding.otpInput4.text.toString()
    }
}
