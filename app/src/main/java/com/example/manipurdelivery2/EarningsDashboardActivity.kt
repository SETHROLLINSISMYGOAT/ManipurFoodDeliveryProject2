package com.example.manipurdelivery2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

data class RecentOrderItem(
    val orderId: String,
    val route: String,
    val amount: String
)

class RecentOrdersAdapter(private val recentOrders: MutableList<RecentOrderItem>) :
    RecyclerView.Adapter<RecentOrdersAdapter.RecentOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentOrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recent_order, parent, false)
        return RecentOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentOrderViewHolder, position: Int) {
        val order = recentOrders[position]
        holder.orderId.text = order.orderId
        holder.route.text = order.route
        holder.amount.text = order.amount

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Order ${order.orderId} clicked!", Toast.LENGTH_SHORT).show()

            val intent = Intent(holder.itemView.context, OrderDetailsActivity::class.java)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = recentOrders.size

    class RecentOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val orderId: TextView = itemView.findViewById(R.id.recent_order_id)
        val route: TextView = itemView.findViewById(R.id.recent_order_route)
        val amount: TextView = itemView.findViewById(R.id.recent_order_amount)
    }
}

class EarningsDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earnings_dashboard)

        val totalEarningsAmount: TextView = findViewById(R.id.total_earnings_amount)
        val deliveriesCompletedText: TextView = findViewById(R.id.deliveries_completed_text)
        val ratingValue: TextView = findViewById(R.id.rating_value)
        val timeValue: TextView = findViewById(R.id.time_value)
        val perHourValue: TextView = findViewById(R.id.per_hour_value)
        val requestWithdrawalButton: Button = findViewById(R.id.request_withdrawal_button)
        val recentOrdersRecyclerView: RecyclerView = findViewById(R.id.recent_orders_recycler_view)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar_earnings_dashboard)
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add_earnings_dashboard)

        totalEarningsAmount.text = "$156.80"
        deliveriesCompletedText.text = "8 deliveries completed"
        ratingValue.text = "4.9"
        timeValue.text = "2h 45m"
        perHourValue.text = "$19.60"

        recentOrdersRecyclerView.layoutManager = LinearLayoutManager(this)
        val sampleRecentOrders = mutableListOf(
            RecentOrderItem("#ORD-1", "Pizza Palace → Oak Ave", "$12.50"),
            RecentOrderItem("#ORD-2", "Burger Joint → Elm St", "$10.00"),
            RecentOrderItem("#ORD-3", "Sushi Spot → Pine Rd", "$15.75"),
            RecentOrderItem("#ORD-4", "Cafe Delight → Maple Av", "$8.20")
        )
        val recentOrdersAdapter = RecentOrdersAdapter(sampleRecentOrders)
        recentOrdersRecyclerView.adapter = recentOrdersAdapter


        requestWithdrawalButton.setOnClickListener {
            val intent = Intent(this, WithdrawalRequestActivity::class.java)
            startActivity(intent)
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home_earnings -> {
                    Toast.makeText(this, "Home (Earnings) selected", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, OrderDetailsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_earnings -> {
                    Toast.makeText(this, "Already on Earnings Dashboard", Toast.LENGTH_SHORT).show()
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
