package com.example.manipurdelivery2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

// Data class to represent a single User Review item
data class UserReviewItem(
    val reviewerProfileImageUrl: String? = null, // Optional: for actual profile images
    val reviewDate: String,
    val reviewTitle: String,
    val rating: Float, // e.g., 4.5 for 4.5 stars
    val reviewBody: String
)

// RecyclerView Adapter for displaying a list of User Review items
class UserReviewAdapter(private val reviews: MutableList<UserReviewItem>) :
    RecyclerView.Adapter<UserReviewAdapter.UserReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_review, parent, false)
        return UserReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserReviewViewHolder, position: Int) {
        val review = reviews[position]

        // Load reviewer profile image (e.g., using Glide/Picasso if reviewerProfileImageUrl is used)
        // For now, using a placeholder drawable directly from XML: holder.reviewerProfileImage.setImageResource(R.drawable.ic_person)
        holder.reviewDate.text = review.reviewDate
        holder.reviewTitle.text = review.reviewTitle
        holder.reviewBody.text = review.reviewBody

        // Dynamically set stars based on rating
        setStarRating(holder.starRatingContainer, review.rating)

        holder.optionsIcon.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Options for ${review.reviewTitle}", Toast.LENGTH_SHORT).show()
            // Implement menu or actions for the review
        }
    }

    private fun setStarRating(container: LinearLayout, rating: Float) {
        container.removeAllViews() // Clear existing stars
        val fullStars = rating.toInt()
        val hasHalfStar = (rating - fullStars) >= 0.5f

        for (i in 0 until fullStars) {
            val star = ImageView(container.context)
            star.layoutParams = LinearLayout.LayoutParams(
                dpToPx(container.context, 16), // 16dp width
                dpToPx(container.context, 16)  // 16dp height
            )
            star.setImageResource(R.drawable.ic_star) // Full star icon
            star.setColorFilter(container.context.resources.getColor(R.color.star_active_color, null)) // Tint with active star color
            container.addView(star)
        }

        if (hasHalfStar) {
            val halfStar = ImageView(container.context)
            halfStar.layoutParams = LinearLayout.LayoutParams(
                dpToPx(container.context, 16),
                dpToPx(container.context, 16)
            )
            halfStar.setImageResource(R.drawable.ic_star_half) // Half star icon
            halfStar.setColorFilter(container.context.resources.getColor(R.color.star_active_color, null))
            container.addView(halfStar)
        }

        // Add empty stars to fill up to 5
        val totalStarsDisplayed = fullStars + (if (hasHalfStar) 1 else 0)
        for (i in totalStarsDisplayed until 5) {
            val emptyStar = ImageView(container.context)
            emptyStar.layoutParams = LinearLayout.LayoutParams(
                dpToPx(container.context, 16),
                dpToPx(container.context, 16)
            )
            emptyStar.setImageResource(R.drawable.ic_star_empty) // Empty star icon (if you want distinct empty star)
            // Or just tint full star with inactive color:
            emptyStar.setColorFilter(container.context.resources.getColor(R.color.star_inactive_color, null)) // Tint with inactive star color
            container.addView(emptyStar)
        }
    }

    private fun dpToPx(context: android.content.Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }


    override fun getItemCount(): Int = reviews.size

    // ViewHolder for holding the views of a single user review card
    class UserReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val reviewerProfileImage: ImageView = itemView.findViewById(R.id.reviewer_profile_image)
        val reviewDate: TextView = itemView.findViewById(R.id.review_date)
        val reviewTitle: TextView = itemView.findViewById(R.id.review_title)
        val starRatingContainer: LinearLayout = itemView.findViewById(R.id.star_rating_container)
        val optionsIcon: ImageView = itemView.findViewById(R.id.review_options_icon)
        val reviewBody: TextView = itemView.findViewById(R.id.review_body)
    }
}

class UserReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_reviews)

        // --- Initialize UI elements ---
        val backArrow: ImageView = findViewById(R.id.back_arrow_user_reviews)
        val userReviewsRecyclerView: RecyclerView = findViewById(R.id.user_reviews_recycler_view)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar_user_reviews)
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add_user_reviews)

        // --- Set up Listeners ---
        backArrow.setOnClickListener {
            finish() // Go back to the previous activity
        }

        // --- Setup RecyclerView ---
        userReviewsRecyclerView.layoutManager = LinearLayoutManager(this)

        val sampleReviews = mutableListOf(
            UserReviewItem(reviewDate = "20/12/2020", reviewTitle = "Great Food and Service", rating = 4.5f,
                reviewBody = "This Food so tasty & delicious. Breakfast so fast Delivered in my place. Chef is very friendly. I'm really like chef for Home Food Order. Thanks."),
            UserReviewItem(reviewDate = "20/12/2020", reviewTitle = "Awesome and Nice", rating = 4.0f,
                reviewBody = "This Food so tasty & delicious. Breakfast so fast Delivered in my place."),
            UserReviewItem(reviewDate = "20/12/2020", reviewTitle = "Awesome and Nice", rating = 3.5f,
                reviewBody = "This Food so tasty & delicious. Breakfast so fast Delivered in my place."),
            UserReviewItem(reviewDate = "20/12/2020", reviewTitle = "Awesome and Nice", rating = 5.0f,
                reviewBody = "This Food so tasty & delicious. Breakfast so fast Delivered in my place.")
        )

        val adapter = UserReviewAdapter(sampleReviews)
        userReviewsRecyclerView.adapter = adapter

        // --- Set up BottomNavigationView item selection listener ---
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
                    // Assuming SettingsActivity is your main profile/settings screen
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
