package com.example.manipurdelivery2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import android.widget.ScrollView


data class WithdrawalHistoryItem(
    val restaurantName: String,
    val ownerName: String,
    val orderId: String,
    val note: String,
    val status: String,
    val amount: String,
    val dateTime: String,
    val byUser: String
)


class WithdrawalHistoryAdapter(private val historyItems: MutableList<WithdrawalHistoryItem>) :
    RecyclerView.Adapter<WithdrawalHistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_withdrawal_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyItems[position]

        holder.restaurantName.text = item.restaurantName
        holder.ownerName.text = "Owner: ${item.ownerName}"
        holder.orderId.text = "Order ID: ${item.orderId}"
        holder.note.text = "Note: ${item.note}"
        holder.amount.text = item.amount
        holder.dateTime.text = item.dateTime
        holder.byUser.text = "By: ${item.byUser}"
        holder.statusTag.text = item.status


        val statusColor = when (item.status) {
            "Completed" -> holder.itemView.context.getColor(R.color.status_completed_green)
            "Pending" -> holder.itemView.context.getColor(R.color.status_pending_yellow)
            "Failed" -> holder.itemView.context.getColor(R.color.status_failed_red)
            else -> holder.itemView.context.getColor(R.color.status_default_grey) // Default color
        }
        holder.statusTag.backgroundTintList = android.content.res.ColorStateList.valueOf(statusColor)

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "History Item: ${item.restaurantName} - ${item.status}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = historyItems.size

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantName: TextView = itemView.findViewById(R.id.history_restaurant_name)
        val ownerName: TextView = itemView.findViewById(R.id.history_owner_name)
        val orderId: TextView = itemView.findViewById(R.id.history_order_id)
        val note: TextView = itemView.findViewById(R.id.history_note)
        val statusTag: TextView = itemView.findViewById(R.id.history_status_tag)
        val amount: TextView = itemView.findViewById(R.id.history_amount)
        val dateTime: TextView = itemView.findViewById(R.id.history_datetime)
        val byUser: TextView = itemView.findViewById(R.id.history_by_admin)
    }
}


class WithdrawalActivity : AppCompatActivity() {

    private lateinit var requestWithdrawalScrollView: ScrollView
    private lateinit var historyContentLayout: androidx.constraintlayout.widget.ConstraintLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdrawal)


        val backArrow: ImageView = findViewById(R.id.back_arrow_withdrawal)
        val withdrawalTabLayout: TabLayout = findViewById(R.id.withdrawal_tab_layout)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar_withdrawal)
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add_withdrawal)

        requestWithdrawalScrollView = findViewById(R.id.request_withdrawal_scroll_view)
        historyContentLayout = findViewById(R.id.history_content_layout)


        val withdrawalAmountInput: EditText = findViewById(R.id.withdrawal_amount_input)
        val reasonInput: EditText = findViewById(R.id.reason_input)
        val processWithdrawalButton: Button = findViewById(R.id.process_withdrawal_button)

        val historyRecyclerView: RecyclerView = findViewById(R.id.withdrawal_history_recycler_view)
        val historySearchInput: EditText = findViewById(R.id.history_search_input)
        val historyStatusSpinner: Spinner = findViewById(R.id.history_status_spinner)



        withdrawalAmountInput.setText("500.00")
        reasonInput.setText("Monthly withdrawal")


        historyRecyclerView.layoutManager = LinearLayoutManager(this)

        val sampleHistoryItems = mutableListOf(
            WithdrawalHistoryItem("Pizza Palace", "John Smith", "TXN001", "Monthly withdrawal", "Completed", "$500.00", "2025-07-24 at 14:30", "Admin"),
            WithdrawalHistoryItem("Pizza Palace", "John Smith", "TXN002", "Weekly payout", "Pending", "$500.00", "2025-07-23 at 10:00", "Admin"),
            WithdrawalHistoryItem("Pizza Palace", "John Smith", "TXN003", "Emergency fund", "Failed", "$500.00", "2025-07-22 at 18:45", "Admin")
        )
        val historyAdapter = WithdrawalHistoryAdapter(sampleHistoryItems)
        historyRecyclerView.adapter = historyAdapter


        ArrayAdapter.createFromResource(
            this,
            R.array.withdrawal_status_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            historyStatusSpinner.adapter = adapter
        }



        backArrow.setOnClickListener {
            finish()
        }

        withdrawalTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        requestWithdrawalScrollView.visibility = View.VISIBLE
                        historyContentLayout.visibility = View.GONE
                        Toast.makeText(this@WithdrawalActivity, "Request withdrawal selected", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        requestWithdrawalScrollView.visibility = View.GONE
                        historyContentLayout.visibility = View.VISIBLE
                        Toast.makeText(this@WithdrawalActivity, "History selected", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


        withdrawalTabLayout.getTabAt(0)?.select()


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
