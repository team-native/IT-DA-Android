package com.example.it_da.ui.commonComponent.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

// Displays a drawable inside a reusable clickable image area.
@Composable
fun ItdaImageButton(
    @DrawableRes imageResId: Int,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    shape: Shape
) {
    Box(
        modifier = modifier
            .clip(shape)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )
    }
}
