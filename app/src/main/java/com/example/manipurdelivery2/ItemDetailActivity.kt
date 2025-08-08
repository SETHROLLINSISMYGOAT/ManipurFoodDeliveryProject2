package com.example.manipurdelivery2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)


        val backArrow: ImageView = findViewById(R.id.back_arrow_item_detail)
        val titleTextView: TextView = findViewById(R.id.item_detail_title)
        val editButton: TextView = findViewById(R.id.edit_item_button)

        val itemImage: ImageView = findViewById(R.id.item_detail_image)
        val imageDotsIndicator: LinearLayout = findViewById(R.id.image_dots_indicator)

        val itemNameInput: EditText = findViewById(R.id.item_detail_name_input)
        val itemLocationInput: EditText = findViewById(R.id.item_detail_location_input)
        val priceLabel: TextView = findViewById(R.id.price_label)
        val itemPriceInput: EditText = findViewById(R.id.item_detail_price_input)
        val descriptionLabel: TextView = findViewById(R.id.description_label)
        val itemDescriptionInput: EditText = findViewById(R.id.item_detail_description_input)

        val updateButton: Button = findViewById(R.id.update_item_button)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar_item_detail)

        itemNameInput.setText("Chicken Thai Biriyani")
        itemLocationInput.setText("Kentucky 39495")
        itemPriceInput.setText("$60")
        itemDescriptionInput.setText("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Bibendum in vel, mattis et amet dui mauris turpis.")



        backArrow.setOnClickListener {
            finish()
        }

        editButton.setOnClickListener {

            Toast.makeText(this, "EDIT clicked! (Functionality to be implemented)", Toast.LENGTH_SHORT).show()

        }

        updateButton.setOnClickListener {

            val updatedName = itemNameInput.text.toString()
            val updatedLocation = itemLocationInput.text.toString()
            val updatedPrice = itemPriceInput.text.toString()
            val updatedDescription = itemDescriptionInput.text.toString()


            Toast.makeText(this, "Item Updated: $updatedName, $updatedPrice", Toast.LENGTH_LONG).show()

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

                    Toast.makeText(this, "Notifications selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_profile -> {

                    Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}
