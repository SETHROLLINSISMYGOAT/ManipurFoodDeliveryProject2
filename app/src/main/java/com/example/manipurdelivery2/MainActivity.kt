package com.example.manipurdelivery2



import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signInButton: Button = findViewById(R.id.sign_in_button)

        val registerButton: Button = findViewById(R.id.register_button)
        signInButton.setOnClickListener {

            val intent = Intent(this, Join12Activity::class.java)

            startActivity(intent)
        }


        registerButton.setOnClickListener {

            val intent = Intent(this, JoinPlatformActivity::class.java)

            startActivity(intent)
        }
    }
}