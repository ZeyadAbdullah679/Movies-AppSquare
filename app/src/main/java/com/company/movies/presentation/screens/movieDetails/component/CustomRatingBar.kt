package com.company.movies.presentation.screens.movieDetails.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.company.movies.R

@Composable
fun CustomRatingBar(
    rating: Float,
    modifier: Modifier = Modifier,
    starSize: Int = 16,
    starPadding: Int = 0,
) {

    Row(modifier = modifier) {
        for (i in 1..5) {
            val starResId = if (i <= rating) R.drawable.filled_star else R.drawable.unfilled_star
            Image(
                painter = painterResource(id = starResId),
                contentDescription = null,
                modifier = Modifier
                    .size(starSize.dp)
                    .padding(horizontal = starPadding.dp)
            )
        }
    }
}