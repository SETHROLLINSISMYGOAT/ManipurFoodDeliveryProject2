package com.example.manipurdelivery2

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class JoinPlatformActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_platform)


        val registerDriver = findViewById<EditText>(R.id.register_driver_edittext)

        registerDriver.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        val registerFoodPartner = findViewById<EditText>(R.id.register_food_partner_edittext)
        registerFoodPartner.setOnClickListener {
            val intent = Intent(this, RegisterActivity12::class.java)
            startActivity(intent)
        }
    }
}
