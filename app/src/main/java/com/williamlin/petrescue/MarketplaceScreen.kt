package com.williamlin.petrescue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import com.williamlin.petrescue.ui.theme.AppColors
import com.williamlin.petrescue.data.samplePets

@Composable
fun MarketplaceScreen() {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(top = 52.dp)
    ) {

        Spacer(modifier = Modifier.height(18.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            SearchSection(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                modifier = Modifier.fillMaxWidth(0.86f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 28.dp,
                end = 28.dp,
                bottom = 130.dp
            ),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            items(samplePets) { pet ->
                MarketplacePetCard(pet = pet)
            }

            item {
                PaginationSection()
            }
        }
    }
}

@Composable
fun MarketplacePetCard(pet: Pet) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(174.dp),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = pet.imageRes),
                contentDescription = pet.name,
                modifier = Modifier
                    .width(116.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(14.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(AppColors.UrgencyBackground)
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = pet.urgency,
                            style = MaterialTheme.typography.labelMedium,
                            color = AppColors.TextPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = AppColors.TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = pet.breed,
                    style = MaterialTheme.typography.bodySmall,
                    color = AppColors.TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(AppColors.TextSecondary)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.location_icon),
                        contentDescription = "Distance",
                        modifier = Modifier.size(18.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "${pet.shelter} • ${pet.distance}",
                        style = MaterialTheme.typography.bodySmall,
                        color = AppColors.TextPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.clock_icon),
                        contentDescription = "Days left",
                        modifier = Modifier.size(18.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "${pet.daysLeft} days left",
                        style = MaterialTheme.typography.bodySmall,
                        color = AppColors.TextPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun PaginationSection() {
    Text(
        text = "1   2   3   4   5   . . .   10   next >",
        style = MaterialTheme.typography.bodyMedium,
        color = AppColors.TextPrimary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    )
}