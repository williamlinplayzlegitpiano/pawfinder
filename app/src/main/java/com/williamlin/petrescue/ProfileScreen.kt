package com.williamlin.petrescue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import com.williamlin.petrescue.ui.theme.AppColors

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileHeaderSection()

        Spacer(modifier = Modifier.height(18.dp))

        ProfileStatsSection()

        Spacer(modifier = Modifier.height(18.dp))

        ProfileDivider()

        Spacer(modifier = Modifier.height(24.dp))

        ProfileContentSection()
    }
}

@Composable
fun ProfileHeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(310.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 180.dp,
                        bottomEnd = 180.dp
                    )
                )
                .background(AppColors.TextSecondary)
        )

        Box(
            modifier = Modifier
                .size(132.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-18).dp)
                .clip(CircleShape)
                .background(AppColors.CardBackground)
        )
    }

    Text(
        text = "USER",
        style = MaterialTheme.typography.headlineMedium,
        color = AppColors.TextPrimary
    )

    Text(
        text = "USERNAME",
        style = MaterialTheme.typography.bodyMedium,
        color = AppColors.TextSecondary
    )
}

@Composable
fun ProfileStatsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 86.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileStatItem(
            value = "0",
            label = "Friends"
        )

        ProfileStatItem(
            value = "0",
            label = "Karma"
        )
    }
}

@Composable
fun ProfileStatItem(
    value: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            color = AppColors.TextPrimary
        )

        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = AppColors.TextPrimary
        )
    }
}

@Composable
fun ProfileContentSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp)
    ) {
        ProfilePlaceholderSection(
            title = "profile statement",
            height = 82.dp
        )

        Spacer(modifier = Modifier.height(18.dp))

        ProfileBadgesSection()

        Spacer(modifier = Modifier.height(18.dp))

        ProfileDivider()

        Spacer(modifier = Modifier.height(20.dp))

        ProfilePlaceholderSection(
            title = "Forum Posts",
            height = 178.dp
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "See more",
            style = MaterialTheme.typography.bodyMedium,
            color = AppColors.TextPrimary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun ProfilePlaceholderSection(
    title: String,
    height: Dp
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.TextSecondary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = AppColors.TextPrimary,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
fun ProfileBadgesSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(104.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.TextSecondary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "Badges",
                style = MaterialTheme.typography.labelLarge,
                color = AppColors.TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BadgePlaceholder()
                BadgePlaceholder()
                BadgePlaceholder()
            }
        }
    }
}

@Composable
fun BadgePlaceholder() {
    Box(
        modifier = Modifier
            .size(width = 48.dp, height = 58.dp)
            .background(AppColors.PlaceholderGray)
    )
}

@Composable
fun ProfileDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp)
            .height(1.dp)
            .background(AppColors.TextSecondary)
    )
}