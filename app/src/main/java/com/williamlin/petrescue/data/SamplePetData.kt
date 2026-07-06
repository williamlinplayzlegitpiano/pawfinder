package com.williamlin.petrescue.data

import com.williamlin.petrescue.Pet
import com.williamlin.petrescue.R

val samplePets = listOf(
    Pet(
        name = "Alvin",
        breed = "Golden Retriever",
        shelter = "Vancouver Animal Shelter",
        distance = "2.4 km",
        urgency = "Critical",
        daysLeft = 2,
        description = "Gentle, playful, and looking for a calm home.",
        imageRes = R.drawable.dog_alvin
    ),
    Pet(
        name = "William",
        breed = "Rabbit",
        shelter = "San Diego Animal Shelter",
        distance = "8.8 km",
        urgency = "High",
        daysLeft = 4,
        description = "Quiet and friendly, loves snacks and soft spaces.",
        imageRes = R.drawable.rabbit_william
    ),
    Pet(
        name = "Joshna",
        breed = "Fox",
        shelter = "Richmond Animal Protection",
        distance = "4.4 km",
        urgency = "Moderate",
        daysLeft = 6,
        description = "Curious and energetic, needs an experienced foster.",
        imageRes = R.drawable.fox_joshna
    ),
    Pet(
        name = "Luna",
        breed = "Chihuahua",
        shelter = "BC SPCA",
        distance = "10 km",
        urgency = "Critical",
        daysLeft = 3,
        description = "Small, affectionate, and comfortable around people.",
        imageRes = R.drawable.dog_alvin
    ),
    Pet(
        name = "Milo",
        breed = "Mixed Breed",
        shelter = "Burnaby Animal Shelter",
        distance = "6.2 km",
        urgency = "High",
        daysLeft = 5,
        description = "Friendly, active, and looking for a patient adopter.",
        imageRes = R.drawable.dog_alvin
    )
)