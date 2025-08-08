package com.example.manipurdelivery2



import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Join12Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_12)


        val driverSigninInput = findViewById<EditText>(R.id.driver_signin_input)
        val foodPartnerSigninInput = findViewById<EditText>(R.id.food_partner_signin_input)

        driverSigninInput.setOnClickListener {
            val intent = Intent(this, SignInDriverActivity::class.java)
            startActivity(intent)
        }


        foodPartnerSigninInput.setOnClickListener {
            val intent = Intent(this, Sign::class.java)
            startActivity(intent)
        }
    }
}
