package com.example.mycity.data

import com.example.mycity.model.Category
import com.example.mycity.model.Destination

object DataSource {

    val catagories = listOf(
        Category(
            name = "Coffee Shops",
            description = "Find cool coffee shops here!",
            destinations = listOf(
                Destination(
                    name = "Common Ground",
                    description = "A chill coffee shop located right on the waterfront",
                    features = "Park access, accessible to kids, pet friendly."
                ),
                Destination(
                    name = "City Java",
                    description = "A modern, upscale coffee shop located below a hotel",
                    features = "Hotel access, pet friendly"
                )
            )
        ),
        Category(
            name = "Parks",
            description = "Find the best parks in town!",
            destinations = listOf(
                Destination(
                    name = "Waterfront",
                    description = "A park right on the Beaufort river",
                    features = "Accessible to kids, pet friendly, playground, swings."
                )
            )
        )
    )
}