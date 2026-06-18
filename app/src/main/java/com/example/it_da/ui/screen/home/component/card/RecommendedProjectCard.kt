package com.example.it_da.ui.screen.home.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.it_da.R
import com.example.it_da.ui.commonComponent.card.ItdaCard
import com.example.it_da.ui.commonComponent.ItdaOutlinedBadge
import com.example.it_da.ui.commonComponent.button.ItdaUnderlinedTextButton
import com.example.it_da.ui.screen.home.component.HomeProjectContentEndPadding
import com.example.it_da.ui.screen.home.component.HomeProjectContentStartPadding
import com.example.it_da.ui.screen.home.component.HomeProjectTitleStartPadding
import com.example.it_da.ui.screen.home.state.RecommendedProjectUiModel
import com.example.it_da.ui.theme.ItdaPrimaryTextColor
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val RecommendedProjectTitleRecruitSpacing = 8.dp
private val RecommendedProjectRecruitStackSpacing = 18.dp
private val RecommendedProjectTechStackSpacing = 6.dp
private val RecommendedProjectStackParticipantsSpacing = 13.dp
private val RecommendedProjectCardMinHeight = 118.dp
private val RecommendedProjectCardTopPadding = 13.dp
private val RecommendedProjectCardBottomPadding = 12.dp
private const val RecommendedProjectTitleMaxLines = 1
private const val RecommendedProjectRecruitingMaxLines = 1
private const val RecommendedProjectParticipantMaxLines = 1
private const val RecommendedProjectTitleWeight = 1f
private const val RecommendedProjectParticipantWeight = 1f

// Displays one recommended project with state-provided title, status, stack, and participant text.
@Composable
fun RecommendedProjectCard(
    project: RecommendedProjectUiModel,
    onProjectClick: (String) -> Unit,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ItdaCard(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = RecommendedProjectCardMinHeight)
            .clickable {
                onProjectClick(project.id)
        }
    ) {
        Column(
            modifier = Modifier.padding(
                top = RecommendedProjectCardTopPadding,
                bottom = RecommendedProjectCardBottomPadding
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = HomeProjectTitleStartPadding,
                        end = HomeProjectContentEndPadding
                    )
            ) {
                Text(
                    text = project.title,
                    color = ItdaPrimaryTextColor,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = RecommendedProjectTitleMaxLines,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(RecommendedProjectTitleWeight)
                )

                ItdaOutlinedBadge(text = project.statusText)
            }

            Spacer(modifier = Modifier.height(RecommendedProjectTitleRecruitSpacing))

            Text(
                text = project.recruitingSummary,
                color = ItdaSecondaryTextColor,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = RecommendedProjectRecruitingMaxLines,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    start = HomeProjectContentStartPadding,
                    end = HomeProjectContentEndPadding
                )
            )

            Spacer(modifier = Modifier.height(RecommendedProjectRecruitStackSpacing))

            Row(
                horizontalArrangement = Arrangement.spacedBy(RecommendedProjectTechStackSpacing),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = HomeProjectContentStartPadding,
                        end = HomeProjectContentEndPadding
                    )
                    .horizontalScroll(rememberScrollState())
            ) {
                project.techStacks.forEach { techStack ->
                    ItdaOutlinedBadge(text = techStack)
                }
            }

            Spacer(modifier = Modifier.height(RecommendedProjectStackParticipantsSpacing))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = HomeProjectContentStartPadding,
                        end = HomeProjectContentEndPadding
                    )
            ) {
                Box(
                    modifier = Modifier
                        .weight(RecommendedProjectParticipantWeight)
                        .horizontalScroll(rememberScrollState())
                ) {
                    Text(
                        text = project.participantSummary,
                        color = ItdaSecondaryTextColor,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = RecommendedProjectParticipantMaxLines,
                        softWrap = false
                    )
                }

                ItdaUnderlinedTextButton(
                    text = stringResource(id = R.string.common_view_details),
                    onClick = {
                        onDetailClick(project.id)
                    }
                )
            }
        }
    }
}
