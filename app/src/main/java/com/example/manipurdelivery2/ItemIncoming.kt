package com.example.manipurdelivery2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ItemIncoming : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.item_incoming_order)


        val incomingOrderRoute: TextView = findViewById(R.id.incoming_order_route)
        val incomingOrderAmount: TextView = findViewById(R.id.incoming_order_amount)
        val locationIcon: ImageView = findViewById(R.id.location_icon)
        val incomingOrderDistance: TextView = findViewById(R.id.incoming_order_distance)
        val acceptOrderLabel: TextView = findViewById(R.id.accept_order_label)
        val btnAcceptYes: Button = findViewById(R.id.btn_accept_order_yes)
        val btnAcceptNo: Button = findViewById(R.id.btn_accept_order_no)


        incomingOrderRoute.text = "Pizza Palace → Oak Ave"
        incomingOrderAmount.text = "$12.50"
        incomingOrderDistance.text = "1.2 km"


        btnAcceptYes.setOnClickListener {

            Toast.makeText(this, "Order Accepted! Navigating to dashboard.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, OrderYetActivity::class.java).apply {

                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()
        }

        btnAcceptNo.setOnClickListener {

            Toast.makeText(this, "Order Declined. Returning to dashboard.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, OrderYetActivity::class.java).apply {

                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()
        }
    }
}
