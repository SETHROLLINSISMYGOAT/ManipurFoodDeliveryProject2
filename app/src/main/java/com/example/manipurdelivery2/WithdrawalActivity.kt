package com.example.manipurdelivery2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class WithdrawalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdrawal)

        // --- Initialize UI elements ---
        val backArrow: ImageView = findViewById(R.id.back_arrow_withdrawal)
        val withdrawalTitle: TextView = findViewById(R.id.withdrawal_title)
        val withdrawalTabLayout: TabLayout = findViewById(R.id.withdrawal_tab_layout)
        val withdrawalAmountInput: EditText = findViewById(R.id.withdrawal_amount_input)
        val reasonInput: EditText = findViewById(R.id.reason_input)
        val processWithdrawalButton: Button = findViewById(R.id.process_withdrawal_button)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar_withdrawal)
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add_withdrawal)


        withdrawalAmountInput.setText("500.00")
        reasonInput.setText("500.00")



        backArrow.setOnClickListener {
            finish()
        }

        withdrawalTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@WithdrawalActivity, "Selected tab: ${tab?.text}", Toast.LENGTH_SHORT).show()

            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        processWithdrawalButton.setOnClickListener {
            val amount = withdrawalAmountInput.text.toString()
            val reason = reasonInput.text.toString()

            if (amount.isBlank()) {
                Toast.makeText(this, "Please enter withdrawal amount", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Processing withdrawal of $amount. Reason: $reason", Toast.LENGTH_LONG).show()

            }
        }


        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, DASH::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_list -> {
                    val intent = Intent(this, DASH::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_notifications -> {
                    val intent = Intent(this, NotificationsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_profile -> {

                    val intent = Intent(this, SettingsActivity::class.java)
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
