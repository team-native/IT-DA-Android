package com.example.it_da.ui.commonComponent.bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.it_da.R
import com.example.it_da.ui.commonComponent.button.ItdaImageButton
import com.example.it_da.ui.theme.ItdaTopBarTitleTextColor

private val ItdaTopBarHeight = 72.dp
private val ItdaTopTitleTopPadding = 22.dp
private val ItdaTopBackButtonSize = 30.dp
private val ItdaTopBackButtonStartPadding = 25.dp
private val ItdaTopBackButtonTopPadding = 25.dp
private val ItdaTopDividerHeight = 1.5.dp

// Draws the shared title area and the design line asset below it.
@Composable
fun ItdaTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    backContentDescription: String? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(ItdaTopBarHeight)
    ) {
        if (onBackClick != null) {
            ItdaImageButton(
                imageResId = R.drawable.backbutton,
                contentDescription = backContentDescription,
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        start = ItdaTopBackButtonStartPadding,
                        top = ItdaTopBackButtonTopPadding
                    )
                    .size(ItdaTopBackButtonSize),
                imageModifier = Modifier.fillMaxSize(),
                shape = MaterialTheme.shapes.small
            )
        }

        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = ItdaTopTitleTopPadding),
            color = ItdaTopBarTitleTextColor,
            style = MaterialTheme.typography.displayLarge
        )

        Image(
            painter = painterResource(id = R.drawable.line),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(ItdaTopDividerHeight)
        )
    }
}
