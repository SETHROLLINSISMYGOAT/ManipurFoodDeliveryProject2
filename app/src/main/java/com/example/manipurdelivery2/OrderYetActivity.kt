package com.example.manipurdelivery2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class OrderYetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.no_order_yet_view)


        val refreshButton: Button = findViewById(R.id.btn_refresh_orders)


        refreshButton.setOnClickListener {
            Toast.makeText(this, "Refreshing orders...", Toast.LENGTH_SHORT).show()

        }


    }
}
