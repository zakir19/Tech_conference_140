    package com.example.test

    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.isSystemInDarkTheme
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.LazyRow
    import androidx.compose.foundation.lazy.items
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Star
    import androidx.compose.material3.*
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp



    // MainActivity
    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                EventDetailsScreen()
            }
        }
    }

    // Define data class for Event
    data class Event(
        val time: String,
        val title: String
    )

    // Define data class for Review
    data class Review(
        val profileImage: Int,  // Resource ID for the profile image
        val userName: String,   // Name of the reviewer
        val comment: String,    // Review comment
        val rating: Int         // Star rating (1 to 5)
    )


    // Sample data for events and reviews
    val eventList = listOf(
        Event("10:00 AM", "Opening Ceremony"),
        Event("12:00 PM", "Tech Talk 1"),
        Event("02:00 PM", "Lunch Break"),
        Event("03:00 PM", "Panel Discussion")
    )

    val userReviews = listOf(
        Review(
            userName = "John Doe",
            comment = "Great event! Learned a lot.",
            profileImage = R.drawable.user,
            rating = 5
        ),
        Review(
            userName = "Jane Smith",
            comment = "The venue was fantastic.",
            profileImage = R.drawable.user,
            rating = 4
        ),
        Review(
            userName = "Alice Johnson",
            comment = "Could have been better organized.",
            profileImage = R.drawable.user,
            rating = 3
        )
    )


    // Composable function for EventDetailsScreen
    @Composable
    fun EventDetailsScreen() {
        val isDarkTheme = isSystemInDarkTheme()

        MaterialTheme(
            colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Event Image
                Image(
                    painter = painterResource(id = R.drawable.event),
                    contentDescription = "Event Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Event Title and Details
                Text(
                    text = "Tech Conference 2024",
                    style = MaterialTheme.typography.headlineMedium
                    font
                )
                Text(
                    text = "Mehsana, Gujarat | 2.5 km away",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "This is a detailed description of the event...",
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Event Schedule
                Text("Event Schedule", style = MaterialTheme.typography.headlineSmall)
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(eventList) { event ->
                        EventScheduleCard(event)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // User Reviews Section
                Text("User Reviews", style = MaterialTheme.typography.headlineSmall)
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(userReviews) { review ->
                        UserReviewCard(review)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Action Buttons (kept fixed if necessary)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { /* Handle Buy Tickets */ }) {
                        Text("Buy Tickets")
                    }
                    OutlinedButton(onClick = { /* Handle Add to Calendar */ }) {
                        Text("Add to Calendar")
                    }
                }
            }
        }
    }




    // EventScheduleCard composable
    @Composable
    fun EventScheduleCard(event: Event) {
        Card(
            modifier = Modifier
                .size(150.dp)
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = event.time,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold, // Makes the text bold
                        fontSize = 18.sp             // Increases the text size
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold, // Makes the text bold
                        fontSize = 16.sp             // Increases the text size
                    )
                )
            }
        }
    }


    // UserReviewCard composable
    @Composable
    fun UserReviewCard(review: Review) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Profile image
            Image(
                painter = painterResource(id = review.profileImage),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

            // User info and review
            Column {
                Text(
                    text = review.userName,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = review.comment,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))

                // Star Rating
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(review.rating) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star Icon",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    repeat(5 - review.rating) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Empty Star Icon",
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${review.rating}/5",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }

