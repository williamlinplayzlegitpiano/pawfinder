package com.williamlin.petrescue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.williamlin.petrescue.ui.theme.AppColors
import com.williamlin.petrescue.ui.theme.PetRescueTheme

data class Pet(
    val name: String,
    val breed: String,
    val shelter: String,
    val distance: String,
    val urgency: String,
    val daysLeft: Int,
    val imageRes: Int
)

val samplePets = listOf(
    Pet(
        name = "Alvin",
        breed = "Golden Retriever",
        shelter = "Vancouver Animal Shelter",
        distance = "2.4 km",
        urgency = "Critical",
        daysLeft = 2,
        imageRes = R.drawable.dog_alvin
    ),
    Pet(
        name = "William",
        breed = "Rabbit",
        shelter = "SD Animal Shelter",
        distance = "8.8 km",
        urgency = "High",
        daysLeft = 4,
        imageRes = R.drawable.rabbit_william
    ),
    Pet(
        name = "Joshna",
        breed = "Fox",
        shelter = "Richmond Animal Protection",
        distance = "4.4 km",
        urgency = "Moderate",
        daysLeft = 6,
        imageRes = R.drawable.fox_joshna
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PetRescueTheme {
                PetRescueApp()
            }
        }
    }
}

@Composable
fun PetRescueApp() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        DashboardScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 88.dp)
        )

        FloatingBottomNavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 14.dp)
        )
    }
}

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = AppColors.Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            HeaderSection()

            SearchSection(
                searchText = searchText,
                onSearchTextChange = { searchText = it }
            )

            UrgentPetsSection()

            DashboardShortcutsSection()

            RecentPostsSection()
        }
    }
}

@Composable
fun HeaderSection() {
    Column {
        Text(
            text = "HomeBound",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = AppColors.TextPrimary
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Adopt, foster, or support pets that need help near you",
            style = MaterialTheme.typography.bodyMedium,
            color = AppColors.TextSecondary
        )
    }
}

@Composable
fun SearchSection(
    searchText: String,
    onSearchTextChange: (String) -> Unit
)  {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text("Search pets, shelters, breeds...")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    // behaviour for later
                }
            ) {
                Text(
                    text = "☰",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )
            }
        },
        shape = RoundedCornerShape(18.dp),
        singleLine = true
    )
}

@Composable
fun UrgentPetsSection() {
    Column {
        Text(
            text = "Urgent Pets",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(samplePets) { pet ->
                PetCard(pet = pet)
            }
        }
    }
}

@Composable
fun PetCard(pet: Pet) {
    Card(
        modifier = Modifier.width(230.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {

            Image(
                painter = painterResource(id = pet.imageRes),
                contentDescription = pet.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clip(RoundedCornerShape(18.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                UrgencyBadge(daysLeft = pet.daysLeft)
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = pet.breed,
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.TextSecondary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${pet.shelter} • ${pet.distance}",
                style = MaterialTheme.typography.bodySmall,
                color = AppColors.TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = pet.urgency,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextSecondary
            )
        }
    }
}

@Composable
fun UrgencyBadge(daysLeft: Int) {
    Box(
        modifier = Modifier
            .background(
                color = AppColors.PetImageBackground,
                shape = CircleShape
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = "${daysLeft}d left",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = AppColors.TextPrimary
        )
    }
}

@Composable
fun DashboardShortcutsSection() {
    Column {
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ShortcutCard(
                title = "Forums",
                emoji = "💬",
                modifier = Modifier.weight(1f)
            )

            ShortcutCard(
                title = "Saved",
                emoji = "🔖",
                modifier = Modifier.weight(1f)
            )

            ShortcutCard(
                title = "Resources",
                emoji = "🤝",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ShortcutCard(
    title: String,
    emoji: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.BrightOrange
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = emoji,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextPrimary
            )
        }
    }
}

@Composable
fun RecentPostsSection() {
    Column {
        Text(
            text = "Recent Pet Owner Posts",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = AppColors.CardBackground
            )
        ) {
            Text(
                text = "Someone nearby is giving away extra puppy food.",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = "A rescued cat needs temporary foster care this weekend.",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun FloatingBottomNavigationBar(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(74.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FloatingNavItem(
                label = "Home",
                icon = "\uD83C\uDFE0\uFE0E",
                selected = true,
                onClick = {}
            )

            FloatingNavItem(
                label = "Marketplace",
                icon = "🐾",
                selected = false,
                onClick = {}
            )

            FloatingNavItem(
                label = "Profile",
                icon = "\uD83D\uDC64",
                selected = false,
                onClick = {}
            )
        }
    }
}

@Composable
fun FloatingNavItem(
    label: String,
    icon: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(96.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(32.dp)
                .width(64.dp)
                .background(
                    color = if (selected) AppColors.PrimarySoft else AppColors.CardBackground,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = icon,
                fontSize = 20.sp,
                color = AppColors.TextPrimary
            )
        }

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
            color = AppColors.TextPrimary
        )
    }
}