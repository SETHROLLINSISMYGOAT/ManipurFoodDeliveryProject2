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


data class NotificationItem(
    val profileImageUrl: String? = null,
    val mainText: String,
    val timestamp: String,
    val actionType: String
)


class NotificationAdapter(private val notifications: MutableList<NotificationItem>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notification_card, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]


        holder.mainText.text = notification.mainText
        holder.timestamp.text = notification.timestamp



        holder.itemView.setOnClickListener {

            Toast.makeText(holder.itemView.context, "Notification: ${notification.mainText}", Toast.LENGTH_SHORT).show()

        }
    }

    override fun getItemCount(): Int = notifications.size

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.notification_profile_image)
        val mainText: TextView = itemView.findViewById(R.id.notification_main_text)
        val timestamp: TextView = itemView.findViewById(R.id.notification_timestamp)
        val actionPlaceholder: View = itemView.findViewById(R.id.notification_action_placeholder)
    }
}


class NotificationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        val backArrow: ImageView = findViewById(R.id.back_arrow_notifications)
        val notificationsTitle: TextView = findViewById(R.id.notifications_title)

        val notificationsRecyclerView: RecyclerView = findViewById(R.id.notifications_recycler_view)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar_notifications)
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add_notifications)


        backArrow.setOnClickListener {
            finish()
        }

        notificationsRecyclerView.layoutManager = LinearLayoutManager(this)

        val sampleNotifications = mutableListOf(
            NotificationItem(mainText = "Tanbir Ahmed Placed a new order", timestamp = "20 min ago", actionType = "order"),
            NotificationItem(mainText = "Salim Smith left a 5 star review", timestamp = "20 min ago", actionType = "review"),
            NotificationItem(mainText = "Royal Bengol agreed to cancel", timestamp = "20 min ago", actionType = "cancellation"),
            NotificationItem(mainText = "Pabel Vuiya Placed a new order", timestamp = "20 min ago", actionType = "order")
        )

        val notificationAdapter = NotificationAdapter(sampleNotifications)
        notificationsRecyclerView.adapter = notificationAdapter


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

                    Toast.makeText(this, "Already on Notifications", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_profile -> {

                    Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show()
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
