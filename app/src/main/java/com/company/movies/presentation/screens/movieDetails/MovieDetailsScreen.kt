package com.company.movies.presentation.screens.movieDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.company.movies.presentation.screens.movieDetails.component.CustomRatingBar
import com.company.movies.presentation.theme.primary
import com.company.movies.util.Constants.IMAGES_BASE
import com.company.movies.R
import com.company.movies.presentation.screens.home.component.MovieCard
import com.company.movies.presentation.screens.movieDetails.component.ActorCard
import com.company.movies.presentation.theme.AppTheme


@Composable
fun MovieDetailsScreen(
    movieId: Int,
    language: String,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    onBackClicked: () -> Unit,
    onMovieClicked: (Int, String) -> Unit
) {
    val movieDetailsState = viewModel.movieDetailsState.collectAsState().value
    val mostSearchedState = viewModel.mostSearchedState.collectAsState().value

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId, language)
        viewModel.fetchMostSearchedMovies(language)
    }

    when (movieDetailsState) {
        is MovieDetailsUiState.Loading -> {
            Text("Loading movie details...")
        }

        is MovieDetailsUiState.Success -> {
            val movieDetails = movieDetailsState.movieDetails
            val actors = movieDetails.productionCompanies.filter { it.logoPath != null }
            val rating = movieDetails.voteAverage
            val numberOfRatingUsers = movieDetails.voteCount

            Column(Modifier.fillMaxSize()) {
                Box(modifier = Modifier.fillMaxWidth()) {

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(IMAGES_BASE + movieDetails.backdropPath)
                            .build(),
                        contentDescription = stringResource(R.string.movie_image),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.FillBounds
                    )


                    Card(
                        colors = CardDefaults.cardColors(primary),
                        modifier = Modifier
                            .padding(start = 20.dp, top = 44.dp)
                            .size(35.dp),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                                contentDescription = "back",
                                modifier = Modifier
                                    .clickable { onBackClicked() }
                                    .padding(vertical = 8.dp, horizontal = 11.dp)
                                    .size(24.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(7.dp))

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    Column(Modifier.weight(0.7f)) {
                        Text(
                            text = movieDetails.title,
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier
                        )
                        Text(
                            text = movieDetails.originalTitle,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }


                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(top = 4.dp)
                    ) {
                        CustomRatingBar(
                            rating = rating.toFloat(),
                            modifier = Modifier
                        )
                        Text(
                            text = "From $numberOfRatingUsers users",
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                }

                Text(
                    text = movieDetails.overview,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color(0xFF8F8F8F),
                    modifier = Modifier.padding(
                        start = 17.dp,
                        end = 32.dp,
                        top = 17.dp,
                        bottom = 23.dp
                    )
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(start = 17.dp, end = 14.dp)
                ) {
                    items(actors) { actor ->
                        actor.logoPath?.let {
                            ActorCard(
                                actorName = actor.name,
                                actorRole = actor.originCountry,
                                actorImage = it
                            )
                        }

                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(R.string.most_searched),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 17.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                when (mostSearchedState) {
                    is MostSearchedUiState.Loading -> {
                        Text("Loading most searched movies...")
                    }

                    is MostSearchedUiState.Success -> {
                        LazyRow(modifier = Modifier.padding(horizontal = 17.dp)) {
                            items(mostSearchedState.movies) { movie ->
                                MovieCard(
                                    movieDetails = movie,
                                    onMovieClicked = {
                                        onMovieClicked(
                                            movie.id,
                                            movie.originalLanguage
                                        )
                                    }
                                )
                            }
                        }
                    }

                    is MostSearchedUiState.Error -> {
                        Text("Error loading most searched movies: ${mostSearchedState.message}")
                    }

                    is MostSearchedUiState.Empty -> {
                        Text("No most searched movies found.")
                    }
                }
            }
        }

        is MovieDetailsUiState.Error -> {
            Text("Error loading movie details: ${movieDetailsState.message}")
        }

        is MovieDetailsUiState.Empty -> {
            Text("No movie details found.")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MovieDetailsScreenPreview() {
    AppTheme {
        MovieDetailsScreen(
            movieId = 1,
            language = "en",
            onBackClicked = {},
            onMovieClicked = { _, _ -> }
        )
    }
}
