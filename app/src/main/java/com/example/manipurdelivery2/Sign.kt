package com.example.manipurdelivery2



import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Sign : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var sendOtpButton: Button
    private var generatedOtp: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_restaurant)


        emailEditText = findViewById(R.id.phone_number_edittext)
        sendOtpButton = findViewById(R.id.send_otp_button)

        sendOtpButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()


            if (email.isEmpty()) {
                emailEditText.error = "Please enter email address"
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.error = "Invalid email address"
                return@setOnClickListener
            }


            generatedOtp = (1000..9999).random().toString()


            Toast.makeText(this, "OTP sent to email: $generatedOtp", Toast.LENGTH_LONG).show()


            val intent = Intent(this, OtpActivity12::class.java)
            intent.putExtra("email", email)
            intent.putExtra("otp", generatedOtp)
            startActivity(intent)
        }
    }
}
