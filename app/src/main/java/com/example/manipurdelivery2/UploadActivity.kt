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


class UploadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_upload)



        val backArrow: ImageView = findViewById(R.id.back_arrow)
        val titleTextView: TextView = findViewById(R.id.title)
        val resetButton: TextView = findViewById(R.id.reset_button)
        val itemNameInput: EditText = findViewById(R.id.item_name_input)
        val priceInput: EditText = findViewById(R.id.price_input)
        val detailsInput: EditText = findViewById(R.id.details_input)
        val saveButton: Button = findViewById(R.id.save_button)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)



        backArrow.setOnClickListener {

            finish()
        }

        resetButton.setOnClickListener {

            itemNameInput.text.clear()
            priceInput.text.clear()
            detailsInput.text.clear()

            Toast.makeText(this, "Fields reset!", Toast.LENGTH_SHORT).show()
        }


        saveButton.setOnClickListener {

            val itemName = itemNameInput.text.toString()
            val itemPrice = priceInput.text.toString()
            val itemDetails = detailsInput.text.toString()

            if (itemName.isBlank() || itemPrice.isBlank() || itemDetails.isBlank()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this, "Saving item: $itemName, $itemPrice", Toast.LENGTH_LONG).show()


                val intent = Intent(this, ItemDetailActivity::class.java)
                startActivity(intent)
                finish()
            }
        }



        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {

                    Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_list -> {

                    Toast.makeText(this, "List selected", Toast.LENGTH_SHORT).show()
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
