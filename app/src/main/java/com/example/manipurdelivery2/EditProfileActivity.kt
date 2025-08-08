package com.example.manipurdelivery2

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)


        val backArrow: ImageView = findViewById(R.id.back_arrow_edit_profile)
        val editProfileTitle: TextView = findViewById(R.id.edit_profile_title)
        val profileImage: ImageView = findViewById(R.id.profile_image)
        val editProfileImageIcon: ImageView = findViewById(R.id.edit_profile_image_icon)

        val fullNameInput: EditText = findViewById(R.id.full_name_input)
        val emailInput: EditText = findViewById(R.id.email_input)
        val phoneNumberInput: EditText = findViewById(R.id.phone_number_input)
        val restaurantNameInput: EditText = findViewById(R.id.restaurant_name_input)
        val restaurantAddressInput: EditText = findViewById(R.id.restaurant_address_input)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar_edit_profile)
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add_edit_profile)



        fullNameInput.setText("Vishal Khadok")
        emailInput.setText("hello@halallab.co")
        phoneNumberInput.setText("408-841-0926")
        restaurantNameInput.setText("I love fast food")
        restaurantAddressInput.setText("6391 Elgin St. Celina, Delaware 10299")



        backArrow.setOnClickListener {
            finish()
        }

        editProfileImageIcon.setOnClickListener {
            Toast.makeText(this, "Edit Profile Image clicked!", Toast.LENGTH_SHORT).show()

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

                    Toast.makeText(this, "Already on Profile/Settings screen", Toast.LENGTH_SHORT).show()
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
