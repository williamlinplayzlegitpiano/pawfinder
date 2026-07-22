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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

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
import androidx.compose.material3.TextButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import kotlinx.coroutines.delay

import com.williamlin.petrescue.ui.theme.AppColors
import com.williamlin.petrescue.ui.theme.PetRescueTheme
import com.williamlin.petrescue.data.samplePets

data class Pet(
    val name: String,
    val breed: String,
    val shelter: String,
    val distance: String,
    val urgency: String,
    val daysLeft: Int,
    val description: String,
    val imageRes: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PetRescueTheme {
                //PetRescueApp()
                PetRescueRoot()
            }
        }
    }
}

@Composable
fun PetRescueApp() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
//        DashboardScreen(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(bottom = 88.dp)
//        )

        MarketplaceScreen()

        FloatingBottomNavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 38.dp, vertical = 26.dp)
        )
    }
}

@Composable
fun PawfinderLogo(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.pawfinder_logo),
        contentDescription = "Pawfinder logo",
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .verticalScroll(rememberScrollState())
    ) {
        TopDashboardSection(
            searchText = searchText,
            onSearchTextChange = { searchText = it }
        )

        CommunitySection()
    }
}

@Composable
fun TopDashboardSection(
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.Background)
            .padding(start = 24.dp, end = 24.dp, top = 60.dp, bottom = 28.dp)
    ) {
        SearchSection(
            searchText = searchText,
            onSearchTextChange = onSearchTextChange
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Urgent Pets",
                style = MaterialTheme.typography.headlineLarge,
                color = AppColors.TextPrimary,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "See more",
                style = MaterialTheme.typography.labelLarge,
                color = AppColors.TextSecondary
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(samplePets) { pet ->
                PetCard(pet = pet)
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Column {
            PawfinderLogo(
                modifier = Modifier
                    .width(150.dp)
                    .height(40.dp)
            )

        Spacer(modifier = Modifier.height(0.2.dp))

    }
}

@Composable
fun SearchSection(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onFilterClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(58.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(AppColors.SearchBackground)
            .padding(start = 18.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.search_icon),
            contentDescription = "Search",
            modifier = Modifier.size(24.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.width(10.dp))

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            if (searchText.isEmpty()) {
                Text(
                    text = "Search...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.Background
                )
            }

            BasicTextField(
                value = searchText,
                onValueChange = onSearchTextChange,
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = AppColors.TextPrimary
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Box(
            modifier = Modifier
                .height(46.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(AppColors.FilterButtonBackground)
                .clickable { onFilterClick() }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Filters",
                style = MaterialTheme.typography.labelLarge,
                color = AppColors.TextPrimary
            )
        }
    }
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
        modifier = Modifier
            .width(278.dp)
            .height(275.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Image(
                painter = painterResource(id = pet.imageRes),
                contentDescription = pet.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clip(RoundedCornerShape(14.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = AppColors.TextPrimary,
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
                text = pet.description,
                style = MaterialTheme.typography.bodySmall,
                color = AppColors.TextPrimary,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "${pet.shelter} • ${pet.distance}",
                style = MaterialTheme.typography.bodySmall,
                color = AppColors.TextPrimary,
                maxLines = 1
            )
        }
    }
}

@Composable
fun UrgencyBadge(daysLeft: Int) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(AppColors.UrgencyBackground)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = "${daysLeft}d left",
            style = MaterialTheme.typography.labelMedium,
            color = AppColors.TextPrimary
        )
    }
}

@Composable
fun CommunitySection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(AppColors.CommunityBackground)
            .padding(start = 28.dp, end = 28.dp, top = 28.dp, bottom = 130.dp)
    ) {
        DashboardShortcutsSection()

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.paw_icon),
                contentDescription = "Paw icon",
                modifier = Modifier.size(24.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Community Spotlight",
                style = MaterialTheme.typography.headlineMedium,
                color = AppColors.TextOnDark
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = R.drawable.paw_icon),
                contentDescription = "Paw icon",
                modifier = Modifier.size(24.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(AppColors.Background)
        )

        Spacer(modifier = Modifier.height(20.dp))

        CommunityPostCard()
    }
}

@Composable
fun DashboardShortcutsSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ShortcutCard(
            title = "Forums",
            iconRes = R.drawable.forum_icon
        )

        ShortcutCard(
            title = "Saved",
            iconRes = R.drawable.saved_icon
        )

        ShortcutCard(
            title = "Resources",
            iconRes = R.drawable.resources_icon
        )
    }
}

@Composable
fun ShortcutCard(
    title: String,
    iconRes: Int
) {
    Card(
        modifier = Modifier
            .width(104.dp)
            .height(104.dp),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.TextPrimary
            )
        }
    }
}

@Composable
fun CommunityPostCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.PostBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(AppColors.CardBackground),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_icon),
                        contentDescription = "Profile icon",
                        modifier = Modifier.size(52.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Cathy Bloberg",
                        style = MaterialTheme.typography.headlineMedium,
                        color = AppColors.TextPrimary
                    )

                    Text(
                        text = "10 hr ago",
                        style = MaterialTheme.typography.bodyMedium,
                        color = AppColors.TextSecondary
                    )
                }

                Text(
                    text = "•••",
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(AppColors.PlaceholderGray)
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "OMG I FOUND SOMETHING COOL lets see what it is, beifalkjdha s;od aksdalskdjla od as d... more",
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.TextPrimary
            )

            Spacer(modifier = Modifier.height(22.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(AppColors.TextSecondary)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.saved_icon),
                    contentDescription = "Save post",
                    modifier = Modifier.size(26.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "♡ 5",
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.width(24.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.forum_icon),
                        contentDescription = "Comments",
                        modifier = Modifier.size(22.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "15",
                        style = MaterialTheme.typography.bodyMedium,
                        color = AppColors.TextPrimary
                    )
                }
            }
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
            .height(72.dp),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.NavBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FloatingNavItem(
                iconRes = R.drawable.home_icon,
                selected = false,
                contentDescription = "Home",
                modifier = Modifier.weight(1f),
                onClick = {}
            )

            FloatingNavItem(
                iconRes = R.drawable.paw_icon,
                selected = true,
                contentDescription = "Marketplace",
                modifier = Modifier.weight(1f),
                onClick = {}
            )

            FloatingNavItem(
                iconRes = R.drawable.chat_icon,
                selected = false,
                contentDescription = "Chat",
                modifier = Modifier.weight(1f),
                onClick = {}
            )

            FloatingNavItem(
                iconRes = R.drawable.profile_icon,
                selected = false,
                contentDescription = "Profile",
                modifier = Modifier.weight(1f),
                onClick = {}
            )
        }
    }
}

@Composable
fun FloatingNavItem(
    iconRes: Int,
    selected: Boolean,
    contentDescription: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(if (selected) 96.dp else 58.dp)
                .height(52.dp)
                .clip(RoundedCornerShape(26.dp))
                .background(
                    color = if (selected) {
                        AppColors.NavSelected
                    } else {
                        Color.Transparent
                    }
                )
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = contentDescription,
                modifier = Modifier.size(36.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PawfinderLogo(
                modifier = Modifier
                    .width(200.dp)
                    .height(40.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(5.dp)
                    .clip(RoundedCornerShape(50))
                    .background(AppColors.TextSecondary.copy(alpha = 0.35f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(72.dp)
                        .clip(RoundedCornerShape(50))
                        .background(AppColors.TextSecondary)
                )
            }
        }
    }
}

@Composable
fun PetRescueRoot() {
    var showLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000)
        showLoading = false
    }

    if (showLoading) {
        LoadingScreen()
    } else {
        PetRescueApp()
    }
}