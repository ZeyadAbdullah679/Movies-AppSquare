package com.company.movies.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.company.movies.R

val Axiforma = FontFamily(
    Font(R.font.axiforma_regular, FontWeight.Normal),
    Font(R.font.axiforma_bold, FontWeight.Bold),
)
val Abel = FontFamily(
    Font(R.font.abel_regular, FontWeight.Normal),
)
val Acme = FontFamily(
    Font(R.font.acme_regular, FontWeight.Normal),
)


// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Acme,
        fontWeight = FontWeight(400),
        fontSize = 32.sp,
        lineHeight = 40.51.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Axiforma,
        fontWeight = FontWeight(700),
        fontSize = 32.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = Abel,
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        lineHeight = 20.39.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Axiforma,
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        lineHeight = 25.34.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Axiforma,
        fontWeight = FontWeight(700),
        fontSize = 14.sp,
        lineHeight = 23.03.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Axiforma,
        fontWeight = FontWeight(400),
        fontSize = 14.sp,
        lineHeight = 22.18.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Abel,
        fontWeight = FontWeight(400),
        fontSize = 14.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Axiforma,
        fontWeight = FontWeight(700),
        fontSize = 12.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Axiforma,
        fontWeight = FontWeight(400),
        fontSize = 12.sp,
        lineHeight = 19.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Axiforma,
        fontWeight = FontWeight(400),
        fontSize = 10.sp,
        lineHeight = 15.84.sp
    )
)