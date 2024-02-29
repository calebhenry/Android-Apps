package com.example.a30days.data

import com.example.a30days.model.Day
import com.example.a30days.R

object DayRepository {
    val DayList: List<Day> = listOf(
        Day(
            dayRes = R.string.day_one,
            titleRes = R.string.day_one_title,
            photoRes = R.drawable.film,
            descriptionRes = R.string.day_one_description
        ),
        Day(
            dayRes = R.string.day_two,
            titleRes = R.string.day_two_title,
            photoRes = R.drawable.crafts,
            descriptionRes = R.string.day_two_description
        )

    )
}