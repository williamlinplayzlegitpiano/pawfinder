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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi

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
import androidx.compose.ui.text.style.TextAlign

import com.williamlin.petrescue.ui.theme.AppColors
import com.williamlin.petrescue.data.samplePets

@Composable
fun MarketplaceScreen() {
    var searchText by remember { mutableStateOf("") }
    var showFilters by remember { mutableStateOf(false) }

    var selectedSpecies by remember { mutableStateOf("Any") }
    var selectedAge by remember { mutableStateOf("Any") }
    var selectedLocation by remember { mutableStateOf("Any") }
    var selectedSize by remember { mutableStateOf("Any") }
    var selectedGender by remember { mutableStateOf("Any") }
    var selectedTimeListed by remember { mutableStateOf("Any Time") }

    val hasActiveFilters =
        selectedSpecies != "Any" ||
                selectedAge != "Any" ||
                selectedLocation != "Any" ||
                selectedSize != "Any" ||
                selectedGender != "Any" ||
                selectedTimeListed != "Any Time"

    val searchQuery = searchText.trim()

    val filteredPets = samplePets.filter { pet ->
        val matchesSearch =
            searchQuery.isBlank() ||
                    pet.name.contains(searchQuery, ignoreCase = true) ||
                    pet.breed.contains(searchQuery, ignoreCase = true) ||
                    pet.shelter.contains(searchQuery, ignoreCase = true) ||
                    pet.distance.contains(searchQuery, ignoreCase = true) ||
                    pet.urgency.contains(searchQuery, ignoreCase = true)

        val matchesSpecies =
            selectedSpecies == "Any" ||
                    pet.breed.contains(selectedSpecies, ignoreCase = true)

        matchesSearch && matchesSpecies
    }
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
                modifier = Modifier.fillMaxWidth(0.86f),
                onFilterClick = { showFilters = !showFilters }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (showFilters) {
            MarketplaceFilterPanel(
                selectedSpecies = selectedSpecies,
                onSpeciesSelected = { selectedSpecies = it },
                selectedAge = selectedAge,
                onAgeSelected = { selectedAge = it },
                selectedLocation = selectedLocation,
                onLocationSelected = { selectedLocation = it },
                selectedSize = selectedSize,
                onSizeSelected = { selectedSize = it },
                selectedGender = selectedGender,
                onGenderSelected = { selectedGender = it },
                selectedTimeListed = selectedTimeListed,
                onTimeListedSelected = { selectedTimeListed = it },
                hasActiveFilters = hasActiveFilters,
                onClearFilters = {
                    selectedSpecies = "Any"
                    selectedAge = "Any"
                    selectedLocation = "Any"
                    selectedSize = "Any"
                    selectedGender = "Any"
                    selectedTimeListed = "Any Time"
                }
            )

            Spacer(modifier = Modifier.height(18.dp))
        } else {
            Spacer(modifier = Modifier.height(20.dp))
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 28.dp,
                end = 28.dp,
                bottom = 130.dp
            ),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            items(filteredPets) { pet ->
                MarketplacePetCard(pet = pet)
            }

            item {
                PaginationSection()
            }
        }
    }
}

@Composable
fun MarketplaceFilterPanel(
    selectedSpecies: String,
    onSpeciesSelected: (String) -> Unit,
    selectedAge: String,
    onAgeSelected: (String) -> Unit,
    selectedLocation: String,
    onLocationSelected: (String) -> Unit,
    selectedSize: String,
    onSizeSelected: (String) -> Unit,
    selectedGender: String,
    onGenderSelected: (String) -> Unit,
    selectedTimeListed: String,
    onTimeListedSelected: (String) -> Unit,
    hasActiveFilters: Boolean,
    onClearFilters: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.SearchBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            FilterSection(
                title = "Species",
                options = listOf("Any", "Dogs", "Rodents", "Cats", "Rabbits", "Reptiles", "Birds", "Others"),
                selectedOption = selectedSpecies,
                onOptionSelected = onSpeciesSelected
            )

            FilterSection(
                title = "Age",
                options = listOf("Any", "Baby (0-1)", "Young (1-3)", "Adult (3-7)", "Senior (8+)"),
                selectedOption = selectedAge,
                onOptionSelected = onAgeSelected
            )

            FilterSection(
                title = "Location",
                options = listOf("10 mi", "25 mi", "50 mi", "100 mi", "Any"),
                selectedOption = selectedLocation,
                onOptionSelected = onLocationSelected
            )

            FilterSection(
                title = "Size",
                options = listOf("Any", "Small", "Medium", "Large", "X-Large"),
                selectedOption = selectedSize,
                onOptionSelected = onSizeSelected
            )

            FilterSection(
                title = "Gender",
                options = listOf("Any", "Male", "Female"),
                selectedOption = selectedGender,
                onOptionSelected = onGenderSelected
            )

            FilterSection(
                title = "Time Listed",
                options = listOf("New Today", "This Week", "This Month", "Any Time"),
                selectedOption = selectedTimeListed,
                onOptionSelected = onTimeListedSelected
            )

            if (hasActiveFilters) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(AppColors.FilterButtonBackground)
                            .clickable { onClearFilters() }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Clear filters",
                            style = MaterialTheme.typography.labelMedium,
                            color = AppColors.TextPrimary
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterSection(
    title: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        color = AppColors.TextPrimary
    )

    Spacer(modifier = Modifier.height(8.dp))

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { option ->
            FilterOptionChip(
                text = option,
                selected = selectedOption == option,
                onClick = { onOptionSelected(option) }
            )
        }
    }

    Spacer(modifier = Modifier.height(14.dp))
}

@Composable
fun FilterOptionChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(
                if (selected) {
                    AppColors.FilterButtonBackground
                } else {
                    AppColors.Background
                }
            )
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = AppColors.TextPrimary
        )
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
                        contentDescription = "Location",
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
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    )
}