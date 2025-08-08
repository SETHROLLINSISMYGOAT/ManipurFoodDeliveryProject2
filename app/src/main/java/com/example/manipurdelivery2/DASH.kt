package com.example.manipurdelivery2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

data class FoodItem(
    val id: String,
    val name: String,
    val tag: String,
    val price: String,
    val imageUrl: String? = null
)


class FoodAdapter(private val foodItems: MutableList<FoodItem>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food_card, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = foodItems[position]

        holder.itemTag.text = foodItem.tag
        holder.itemName.text = foodItem.name
        holder.itemId.text = "ID: ${foodItem.id}"
        holder.itemPrice.text = foodItem.price


        holder.doneButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, "${foodItem.name} Done!", Toast.LENGTH_SHORT).show()
        }

        holder.cancelButton.setOnClickListener {
            val adapterPosition = holder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                foodItems.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                Toast.makeText(holder.itemView.context, "${foodItem.name} Removed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = foodItems.size

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.item_image)
        val itemTag: TextView = itemView.findViewById(R.id.item_tag)
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemId: TextView = itemView.findViewById(R.id.item_id)
        val itemPrice: TextView = itemView.findViewById(R.id.item_price)
        val doneButton: Button = itemView.findViewById(R.id.done_button)
        val cancelButton: Button = itemView.findViewById(R.id.cancel_button)
    }
}

class DASH : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard12)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@DASH, "Selected: ${tab?.text}", Toast.LENGTH_SHORT).show()

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


        val foodListRecyclerView: RecyclerView = findViewById(R.id.food_list_recycler_view)
        foodListRecyclerView.layoutManager = LinearLayoutManager(this)

        val sampleFoodItems = mutableListOf(
            FoodItem("001", "Chicken Thai Biriyani", "#Breakfast", "$60", null),
            FoodItem("002", "Spicy Noodles", "#Lunch", "$45", null),
            FoodItem("003", "Veggie Wrap", "#Dinner", "$30", null),
            FoodItem("004", "Fruit Salad", "#Breakfast", "$25", null),
            FoodItem("005", "Steak & Potatoes", "#Dinner", "$90", null),
            FoodItem("006", "Pancakes", "#Breakfast", "$15", null)
        )
        val foodAdapter = FoodAdapter(sampleFoodItems)
        foodListRecyclerView.adapter = foodAdapter


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)
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


        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add)
        fabAdd.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }
    }
}
