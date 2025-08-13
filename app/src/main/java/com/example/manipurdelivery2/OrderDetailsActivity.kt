package com.example.manipurdelivery2

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.manipurdelivery2.Constants

class OrderDetailsActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val backArrow: ImageView = findViewById(R.id.back_arrow_order_details)
        val goOnlineButton: TextView = findViewById(R.id.go_online_button)

        val activeOrderAmount: TextView = findViewById(R.id.active_order_amount)
        val activeOrderTime: TextView = findViewById(R.id.active_order_time)
        val activeOrderId: TextView = findViewById(R.id.active_order_id)

        val customerName: TextView = findViewById(R.id.customer_name)
        val customerPhone: TextView = findViewById(R.id.customer_phone)
        val callCustomerButton: ImageView = findViewById(R.id.call_customer_button)
        val messageCustomerButton: ImageView = findViewById(R.id.message_customer_button)

        val pickupAddress: TextView = findViewById(R.id.pickup_address)
        val deliveryAddress: TextView = findViewById(R.id.delivery_address)





        val orderItemDetails1: TextView = findViewById(R.id.order_item_details_1)
        val orderItemDetails2: TextView = findViewById(R.id.order_item_details_2)

        val deliveredButton: Button = findViewById(R.id.delivered_button)
        val reportIssueButton: Button = findViewById(R.id.report_issue_button)
        val rateReviewButton: Button = findViewById(R.id.rate_review_button)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar_order_details)
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add_order_details)

        activeOrderAmount.text = "$32.50"
        activeOrderTime.text = "25 mins"
        activeOrderId.text = "#ORD-2025-001"

        customerName.text = "Sarah Johnson"
        customerPhone.text = "+1 (555) 123-4567"

        pickupAddress.text = "Pizza Palace, 123 Main St"
        deliveryAddress.text = "456 Oak Ave, Apt 2B"

        orderItemDetails1.text = "2x Margherita Pizza"
        orderItemDetails2.text = "1x Garlic Bread"

        backArrow.setOnClickListener {
            finish()
        }

        goOnlineButton.setOnClickListener {
            Toast.makeText(this, "Go Online clicked!", Toast.LENGTH_SHORT).show()
        }

        callCustomerButton.setOnClickListener {
            val phoneNumber = customerPhone.text.toString()
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(intent)
        }

        messageCustomerButton.setOnClickListener {
            val phoneNumber = customerPhone.text.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$phoneNumber"))
            startActivity(intent)
        }

        pickupAddress.setOnClickListener {
            Toast.makeText(this, "Navigating to incoming order view!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,ItemIncoming::class.java).apply {
                putExtra(Constants.EXTRA_ACTION, Constants.ACTION_SHOW_INCOMING_ORDER)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
        }



        deliveredButton.setOnClickListener {
            Toast.makeText(this, "Order marked as Delivered!", Toast.LENGTH_SHORT).show()
        }

        reportIssueButton.setOnClickListener {
            Toast.makeText(this, "Report Issue clicked!", Toast.LENGTH_SHORT).show()
        }

        rateReviewButton.setOnClickListener {
            Toast.makeText(this, "Rate and review clicked!", Toast.LENGTH_SHORT).show()
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_earnings -> {
                    val intent = Intent(this, EarningsDashboardActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_profile_earnings -> {
                    val intent = Intent(this, UserProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        fabAdd.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }
    }
}
