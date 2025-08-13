package com.example.manipurdelivery2

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
import android.widget.RadioButton
import android.widget.RadioGroup
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

data class WithdrawalHistoryItem1(
    val restaurantName: String,
    val ownerName: String,
    val orderId: String,
    val note: String,
    val status: String,
    val amount: String,
    val dateTime: String,
    val byUser: String
)


class WithdrawalHistoryAdapter1(private val historyItems: MutableList<WithdrawalHistoryItem1>) :
    RecyclerView.Adapter<WithdrawalHistoryAdapter1.HistoryViewHolder>() {

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
            else -> holder.itemView.context.getColor(R.color.status_default_grey)
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

class WithdrawalRequestActivity : AppCompatActivity() {

    private lateinit var availableBalanceAmount: TextView
    private lateinit var withdrawalAmountInput: EditText
    private lateinit var btnAmount10: Button
    private lateinit var btnAmount20: Button
    private lateinit var btnAmount40: Button
    private lateinit var radioRazorpay: RadioButton
    private lateinit var cancelWithdrawalButton: Button
    private lateinit var requestWithdrawalButton: Button


    private lateinit var historyContentLayout: androidx.constraintlayout.widget.ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdrawal_request)

        val backArrow: ImageView = findViewById(R.id.back_arrow_withdrawal)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar_withdrawal)
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add_withdrawal)

        availableBalanceAmount = findViewById(R.id.available_balance_amount)
        withdrawalAmountInput = findViewById(R.id.withdrawal_amount_input)
        btnAmount10 = findViewById(R.id.btn_amount_10)
        btnAmount20 = findViewById(R.id.btn_amount_20)
        btnAmount40 = findViewById(R.id.btn_amount_40)
        radioRazorpay = findViewById(R.id.radio_razorpay)
        cancelWithdrawalButton = findViewById(R.id.cancel_withdrawal_button)
        requestWithdrawalButton = findViewById(R.id.request_withdrawal_button)

        historyContentLayout = findViewById(R.id.history_content_layout)


        availableBalanceAmount.text = "$245.80"
        withdrawalAmountInput.setText("")
        radioRazorpay.isChecked = true



        backArrow.setOnClickListener {
            finish()
        }


        btnAmount10.setOnClickListener { withdrawalAmountInput.setText("10.00") }
        btnAmount20.setOnClickListener { withdrawalAmountInput.setText("20.00") }
        btnAmount40.setOnClickListener { withdrawalAmountInput.setText("40.00") }

        cancelWithdrawalButton.setOnClickListener {
            Toast.makeText(this, "Withdrawal cancelled.", Toast.LENGTH_SHORT).show()
            finish()
        }

        requestWithdrawalButton.setOnClickListener {
            val amount = withdrawalAmountInput.text.toString()
            if (amount.isBlank() || amount.toDoubleOrNull() == 0.0) {
                Toast.makeText(this, "Please enter a valid withdrawal amount", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Requesting withdrawal of $$amount via Razor pay", Toast.LENGTH_LONG).show()

            }
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
