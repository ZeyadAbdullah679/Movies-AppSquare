package com.company.movies.presentation.screens.movieDetails.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.company.movies.R
import com.company.movies.presentation.theme.AppTheme
import com.company.movies.util.Constants.IMAGES_BASE

@Composable
fun ActorCard(
    actorName: String,
    actorRole: String,
    actorImage: String
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .wrapContentHeight()
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFF5E5E5E),
                        shape = MaterialTheme.shapes.large
                    )
                    .padding(
                        start = 80.dp,
                        end = 40.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    )
            ) {
                Text(
                    text = actorName,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.width(82.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = actorRole,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .width(53.dp),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }


            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(IMAGES_BASE + actorImage)
                    .crossfade(true)
                    .build(),
                loading = {
                    CircularProgressIndicator()
                },
                error = {
                    Image(
                        painter = painterResource(id = R.drawable.person),
                        contentDescription = null
                    )
                },
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ActorCardPreview() {
    AppTheme {
        ActorCard(
            actorName = "John Doe",
            actorRole = "Actor",
            actorImage = "https://example.com/actor_image.jpg"
        )
    }
}