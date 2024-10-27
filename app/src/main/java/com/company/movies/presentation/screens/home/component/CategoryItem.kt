package com.company.movies.presentation.screens.home.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.movies.presentation.theme.AppTheme

@Composable
fun CategoryItem(category: String) {
    Box(
        modifier = Modifier
            .padding(end = 7.dp)
            .border(width = 1.dp, color = Color.Black, shape = MaterialTheme.shapes.small),
        contentAlignment = Alignment.Center,

        ) {

        Text(
            text = category,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 12.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryItemPreview() {
    AppTheme {
        CategoryItem("Drama")
    }
}