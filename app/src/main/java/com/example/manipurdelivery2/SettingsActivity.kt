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
import com.google.android.material.floatingactionbutton.FloatingActionButton


data class SettingMenuItem(
    val id: String,
    val iconResId: Int,
    val title: String,
    val value: String? = null,
    val showArrow: Boolean = true
)


class SettingMenuAdapter(
    private val menuItems: List<SettingMenuItem>,
    private val onItemClick: (SettingMenuItem) -> Unit
) : RecyclerView.Adapter<SettingMenuAdapter.SettingMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_setting_menu, parent, false)
        return SettingMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingMenuViewHolder, position: Int) {
        val item = menuItems[position]

        holder.icon.setImageResource(item.iconResId)
        holder.title.text = item.title

        if (item.value != null) {
            holder.valueOrArrow.text = item.value
            holder.arrow.visibility = View.GONE
        } else {
            holder.valueOrArrow.text = ""
            holder.arrow.visibility = if (item.showArrow) View.VISIBLE else View.GONE
        }

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = menuItems.size

    class SettingMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.menu_item_icon)
        val title: TextView = itemView.findViewById(R.id.menu_item_title)
        val valueOrArrow: TextView = itemView.findViewById(R.id.menu_item_value_or_arrow)
        val arrow: ImageView = itemView.findViewById(R.id.menu_item_arrow)
    }
}

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        val backArrow: ImageView = findViewById(R.id.back_arrow_settings)
        val settingsRecyclerView: RecyclerView = findViewById(R.id.settings_menu_recycler_view)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar_settings)
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add_settings)


        backArrow.setOnClickListener {
            finish()
        }


        settingsRecyclerView.layoutManager = LinearLayoutManager(this)

        val settingsMenuItems = listOf(
            SettingMenuItem("personal_info", R.drawable.ic_person, "Personal Info"),
            SettingMenuItem("settings_app", R.drawable.ic_settings, "Settings"),
            SettingMenuItem("withdrawal", R.drawable.ic_withdrawal, "Withdrawal"),
            SettingMenuItem("number_of_orders", R.drawable.ic_orders, "Number of Orders", "29K"),
            SettingMenuItem("user_reviews", R.drawable.ic_reviews, "User Reviews"),
            SettingMenuItem("log_out", R.drawable.ic_logout, "Log Out", showArrow = true)
        )

        val adapter = SettingMenuAdapter(settingsMenuItems) { item ->
            when (item.id) {
                "user_reviews" -> {

                    val intent = Intent(this, UserReviewActivity::class.java)
                    startActivity(intent)
                }
                "log_out" -> {
                    Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show()

                }
                "personal_info" -> {
                    val intent = Intent(this, EditProfileActivity::class.java)
                    startActivity(intent)
                }
                "withdrawal" -> {
                    val intent = Intent(this, WithdrawalActivity::class.java)
                    startActivity(intent)
                }

                else -> {
                    Toast.makeText(this, "${item.title} clicked!", Toast.LENGTH_SHORT).show()

                }
            }
        }
        settingsRecyclerView.adapter = adapter


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

                    Toast.makeText(this, "Already on Profile/Settings related screen", Toast.LENGTH_SHORT).show()
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
