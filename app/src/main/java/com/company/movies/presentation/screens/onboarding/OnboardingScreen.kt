package com.company.movies.presentation.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.movies.R
import com.company.movies.presentation.theme.AppTheme
import com.company.movies.presentation.theme.primary


@Composable
fun OnboardingScreen(
    onEnterClicked: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
//            .background(background)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.onboarding_img),
            contentDescription = stringResource(R.string.onboarding_image),
            modifier = Modifier
                .fillMaxWidth()
                .height(492.24.dp),
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.height(29.dp))

        Text(
            text = stringResource(R.string.onboarding),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.onboarding_body) + stringResource(R.string.for_free),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 17.dp),
            textAlign = TextAlign.Center
        )


        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onEnterClicked,
            colors = ButtonDefaults.buttonColors(containerColor = primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 96.dp, end = 96.dp, bottom = 79.dp)
        ) {
            Text(
                text = stringResource(R.string.enter_now),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center

            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun OnboardingScreenPreview() {
    AppTheme {
        OnboardingScreen {}
    }
}