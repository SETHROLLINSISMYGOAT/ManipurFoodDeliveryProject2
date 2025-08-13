package com.example.manipurdelivery2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


data class ProfileMenuItem(
    val id: String,
    val iconResId: Int,
    val title: String,
    val subtitle: String? = null,
    val value: String? = null,
    val showArrow: Boolean = true
)

// RecyclerView Adapter for displaying profile menu items
class ProfileMenuAdapter(
    private val menuItems: List<ProfileMenuItem>,
    private val onItemClick: (ProfileMenuItem) -> Unit
) : RecyclerView.Adapter<ProfileMenuAdapter.ProfileMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile_menu, parent, false)
        return ProfileMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileMenuViewHolder, position: Int) {
        val item = menuItems[position]

        holder.icon.setImageResource(item.iconResId)
        holder.title.text = item.title

        holder.subtitle.visibility = if (item.subtitle != null) View.VISIBLE else View.GONE
        holder.subtitle.text = item.subtitle

        if (item.value != null) {
            holder.valueOrArrow.text = item.value
            holder.valueOrArrow.visibility = View.VISIBLE
            holder.arrow.visibility = View.GONE
        } else {
            holder.valueOrArrow.text = ""
            holder.valueOrArrow.visibility = View.GONE
            holder.arrow.visibility = if (item.showArrow) View.VISIBLE else View.GONE
        }

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = menuItems.size

    class ProfileMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.menu_item_icon)
        val title: TextView = itemView.findViewById(R.id.menu_item_title)
        val subtitle: TextView = itemView.findViewById(R.id.menu_item_subtitle)
        val valueOrArrow: TextView = itemView.findViewById(R.id.menu_item_value_or_arrow)
        val arrow: ImageView = itemView.findViewById(R.id.menu_item_arrow)
    }
}

class UserProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val profileImage: ImageView = findViewById(R.id.profile_image)
        val profileName: TextView = findViewById(R.id.profile_name)
        val profileDriverId: TextView = findViewById(R.id.profile_driver_id)
        val profilePhoneNumber: TextView = findViewById(R.id.profile_phone_number)
        val settingsIcon: ImageView = findViewById(R.id.settings_icon)

        val statsTotalOrders: TextView = findViewById(R.id.stats_total_orders)
        val statsCompletion: TextView = findViewById(R.id.stats_completion)
        val statsExperience: TextView = findViewById(R.id.stats_experience)

        val profileMenuRecyclerView: RecyclerView = findViewById(R.id.profile_menu_recycler_view)
        val profileAppSettingsRecyclerView: RecyclerView = findViewById(R.id.profile_app_settings_recycler_view)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar_profile)

        profileImage.setImageResource(R.drawable.ic_user_profile_placeholder)
        profileName.text = "Name- Sarah Johnson"
        profileDriverId.text = "Driver ID: DRV-12345"
        profilePhoneNumber.text = "Phone no. - (555) 123-4567"

        statsTotalOrders.text = "1,247"
        statsCompletion.text = "98.5%"
        statsExperience.text = "2.1 yrs"


        profileMenuRecyclerView.layoutManager = LinearLayoutManager(this)
        val generalMenuItems = listOf(
            ProfileMenuItem("vehicle_info", R.drawable.ic_vehicle, "Vehicle Information", "2020 Honda Civic / Silver • ABC-1234"),
            ProfileMenuItem("documents", R.drawable.ic_document, "Documents", "All verified"),
            ProfileMenuItem("payment_methods", R.drawable.ic_payment_method, "Payment Methods", value = "Razor pay"),
            ProfileMenuItem("earning_history", R.drawable.ic_earning_history, "Earning history", "View detail report")
        )
        val generalMenuAdapter = ProfileMenuAdapter(generalMenuItems) { item ->
            when (item.id) {
                "vehicle_info" -> Toast.makeText(this, "Vehicle Information clicked!", Toast.LENGTH_SHORT).show()
                "documents" -> Toast.makeText(this, "Documents clicked!", Toast.LENGTH_SHORT).show()
                "payment_methods" -> Toast.makeText(this, "Payment Methods clicked!", Toast.LENGTH_SHORT).show()
                "earning_history" -> {
                    val intent = Intent(this, EarningsDashboardActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        profileMenuRecyclerView.adapter = generalMenuAdapter

        profileAppSettingsRecyclerView.layoutManager = LinearLayoutManager(this)
        val appSettingsMenuItems = listOf(
            ProfileMenuItem("app_settings", R.drawable.ic_settings_grey, "App Settings", "Notification ,Preferences ,etc."),
            ProfileMenuItem("help_support", R.drawable.ic_help_support, "Help & Support", "Faq ,Support ,Contact ,etc."),
            ProfileMenuItem("about_app", R.drawable.ic_about_app, "About App", value = "Version 2.1.0", showArrow = false)
        )
        val appSettingsMenuAdapter = ProfileMenuAdapter(appSettingsMenuItems) { item ->
            when (item.id) {
                "app_settings" -> Toast.makeText(this, "App Settings clicked!", Toast.LENGTH_SHORT).show()
                "help_support" -> Toast.makeText(this, "Help & Support clicked!", Toast.LENGTH_SHORT).show()
                "about_app" -> Toast.makeText(this, "About App clicked!", Toast.LENGTH_SHORT).show()
            }
        }
        profileAppSettingsRecyclerView.adapter = appSettingsMenuAdapter


        settingsIcon.setOnClickListener {

            Toast.makeText(this, "Global Settings clicked!", Toast.LENGTH_SHORT).show()

        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home_earnings -> {
                    val intent = Intent(this, OrderDetailsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_earnings -> {
                    val intent = Intent(this, EarningsDashboardActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_profile_earnings -> {
                    Toast.makeText(this, "Already on Profile screen!", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        // Set the current selected item in bottom navigation
        bottomNavigationView.selectedItemId = R.id.nav_profile_earnings
    }
}
