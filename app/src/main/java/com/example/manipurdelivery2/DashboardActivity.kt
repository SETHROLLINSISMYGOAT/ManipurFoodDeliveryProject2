package com.example.manipurdelivery2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior


data class Order(
    val id: String,
    val name: String,
    val tag: String,
    val price: String,
    val imageUrl: String? = null
)


class OrderAdapter(private val orders: MutableList<Order>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_order_item, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]

        holder.itemId.text = "ID: ${order.id}"
        holder.itemTag.text = order.tag
        holder.itemName.text = order.name
        holder.itemPrice.text = order.price



        holder.doneButton.setOnClickListener {

            val intent = Intent(holder.itemView.context, DASH::class.java)
            holder.itemView.context.startActivity(intent)
        }

        holder.cancelButton.setOnClickListener {

            val adapterPosition = holder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                orders.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)

            }
        }
    }

    override fun getItemCount(): Int = orders.size


    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.item_image)
        val itemTag: TextView = itemView.findViewById(R.id.item_tag)
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemId: TextView = itemView.findViewById(R.id.item_id)
        val itemPrice: TextView = itemView.findViewById(R.id.item_price)
        val doneButton: Button = itemView.findViewById(R.id.done_button)
        val cancelButton: Button = itemView.findViewById(R.id.cancel_button)
    }
}

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val mainHeaderLayout: View? = findViewById(R.id.main_header)
        mainHeaderLayout?.apply {
            findViewById<TextView>(R.id.header_location_label)?.text = "LOCATION"
            findViewById<TextView>(R.id.header_location_name)?.text = "Halal Lab office"
            findViewById<ImageView>(R.id.header_user_profile_image)?.setImageResource(R.drawable.ic_user_profile)
        }

        val dashboardMetricsLayout: View? = findViewById(R.id.dashboard_metrics)
        dashboardMetricsLayout?.apply {
            findViewById<TextView>(R.id.running_orders_count)?.text = "20"
            findViewById<TextView>(R.id.running_orders_label)?.text = "Running Orders"
            findViewById<TextView>(R.id.restaurant_status_text)?.text = "Open"
            findViewById<TextView>(R.id.restaurant_status_label)?.text = "Restaurant Status"
        }

        val revenueChartLayout: View? = findViewById(R.id.revenue_chart_section)
        revenueChartLayout?.apply {
            findViewById<TextView>(R.id.text_revenue_label)?.text = "Total Revenue"
            findViewById<TextView>(R.id.text_revenue_amount)?.text = "$2,241"
            findViewById<TextView>(R.id.text_daily_dropdown)?.text = "Daily"
            findViewById<TextView>(R.id.text_see_details)?.text = "See Details"
            findViewById<ImageView>(R.id.chart_view_placeholder)?.setImageResource(R.drawable.chart_placeholder)
        }

        val bottomSheetLayout: LinearLayout? = findViewById(R.id.running_orders_sheet)

        bottomSheetLayout?.let {
            val bottomSheetBehavior = BottomSheetBehavior.from(it)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

            val runningOrdersRecyclerView: RecyclerView? = it.findViewById(R.id.running_orders_recycler_view)


            val sampleOrders = mutableListOf(
                Order("001", "Chicken Thai Biriyani", "#Breakfast", "$60", "image_url_1"),
                Order("002", "Spicy Noodles", "#Lunch", "$45", "image_url_2"),
                Order("003", "Veggie Wrap", "#Dinner", "$30", "image_url_3"),
                Order("004", "Fruit Salad", "#Breakfast", "$25", "image_url_4"),
                Order("005", "Steak & Potatoes", "#Dinner", "$90", "image_url_5")
            )

            runningOrdersRecyclerView?.apply {
                layoutManager = LinearLayoutManager(this@DashboardActivity)
                adapter = OrderAdapter(sampleOrders)
            }

            it.findViewById<TextView>(R.id.running_orders_title_text)?.text = "${sampleOrders.size} Running Orders"
        }
    }
}
