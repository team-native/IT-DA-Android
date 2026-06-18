package com.example.it_da.ui.commonComponent.section

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.R
import com.example.it_da.ui.commonComponent.ItdaCardDefaults
import com.example.it_da.ui.screen.home.state.HomeProjectCountUiModel
import com.example.it_da.ui.theme.ItdaPrimaryTextColor
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val HomeProfileImageGreetingSpacing = 13.dp
private val HomeProfileGreetingDescriptionSpacing = 5.dp
private val HomeProfileCountCardSpacing = 18.dp
private val HomeProjectCountLabelValueSpacing = 17.dp
private val HomeProfileImageSize = 58.dp
private val HomeProfileDescriptionLineHeight = 15.sp
private val HomeProjectCountCardElevation = 8.dp
private val HomeProjectCountCardCornerRadius = 8.dp
private val HomeProjectCountCardHeight = 80.dp

// Shows the user greeting and the project status count summary.
@Composable
fun HomeProfileSummarySection(
    @DrawableRes profileImageResId: Int,
    userName: String,
    greetingDescription: String,
    projectCount: HomeProjectCountUiModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = profileImageResId),
                contentDescription = stringResource(id = R.string.home_profile_image_description),
                modifier = Modifier.size(HomeProfileImageSize)
            )

            Spacer(modifier = Modifier.width(HomeProfileImageGreetingSpacing))

            Column {
                Text(
                    text = stringResource(id = R.string.home_profile_greeting, userName),
                    color = ItdaPrimaryTextColor,
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(HomeProfileGreetingDescriptionSpacing))

                Text(
                    text = greetingDescription,
                    color = ItdaSecondaryTextColor,
                    style = MaterialTheme.typography.bodySmall.copy(
                        lineHeight = HomeProfileDescriptionLineHeight
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(HomeProfileCountCardSpacing))

        HomeProjectCountCard(projectCount = projectCount)
    }
}

// Displays the three count values that summarize the user's project activity.
@Composable
private fun HomeProjectCountCard(
    projectCount: HomeProjectCountUiModel,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = HomeProjectCountCardElevation,
                shape = RoundedCornerShape(HomeProjectCountCardCornerRadius),
                clip = false
        ),
        shape = RoundedCornerShape(HomeProjectCountCardCornerRadius),
        color = MaterialTheme.colorScheme.surface,
        border = ItdaCardDefaults.outlinedBorder()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(HomeProjectCountCardHeight),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeProjectCountItem(
                label = stringResource(id = R.string.home_project_count_applying),
                count = projectCount.applyingCount
            )
            HomeProjectCountItem(
                label = stringResource(id = R.string.home_project_count_participating),
                count = projectCount.participatingCount
            )
            HomeProjectCountItem(
                label = stringResource(id = R.string.home_project_count_completed),
                count = projectCount.completedCount
            )
        }
    }
}

// Shows one labeled count inside the project count summary card.
@Composable
private fun HomeProjectCountItem(
    label: String,
    count: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = ItdaSecondaryTextColor,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(HomeProjectCountLabelValueSpacing))

        Text(
            text = count.toString(),
            color = ItdaPrimaryTextColor,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}
