package com.example.it_da.ui.screen.home.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import com.example.it_da.ui.screen.home.state.ParticipatingProjectUiModel
import com.example.it_da.ui.theme.ItdaPrimaryTextColor
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val ParticipatingProjectTitleRoleSpacing = 8.dp
private val ParticipatingProjectRoleTeamSpacing = 36.dp
private val ParticipatingProjectCardMinHeight = 106.dp
private val ParticipatingProjectCardTopPadding = 13.dp
private val ParticipatingProjectCardBottomPadding = 12.dp
private const val ParticipatingProjectTitleMaxLines = 1
private const val ParticipatingProjectRoleMaxLines = 1
private const val ParticipatingProjectTeamMaxLines = 1
private const val ParticipatingProjectTitleWeight = 1f
private const val ParticipatingProjectTeamWeight = 1f

// Displays one participating project with role and progress text supplied by state.
@Composable
fun ParticipatingProjectCard(
    project: ParticipatingProjectUiModel,
    onProjectClick: (String) -> Unit,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ItdaCard(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = ParticipatingProjectCardMinHeight)
            .clickable {
                onProjectClick(project.id)
        }
    ) {
        Column(
            modifier = Modifier.padding(
                top = ParticipatingProjectCardTopPadding,
                bottom = ParticipatingProjectCardBottomPadding
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
                    maxLines = ParticipatingProjectTitleMaxLines,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(ParticipatingProjectTitleWeight)
                )

                ItdaOutlinedBadge(text = project.statusText)
            }

            Spacer(modifier = Modifier.height(ParticipatingProjectTitleRoleSpacing))

            Text(
                text = project.myRole,
                color = ItdaSecondaryTextColor,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = ParticipatingProjectRoleMaxLines,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    start = HomeProjectContentStartPadding,
                    end = HomeProjectContentEndPadding
                )
            )

            Spacer(modifier = Modifier.height(ParticipatingProjectRoleTeamSpacing))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = HomeProjectContentStartPadding,
                        end = HomeProjectContentEndPadding
                    )
            ) {
                Text(
                    text = project.teamSummary,
                    color = ItdaSecondaryTextColor,
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = ParticipatingProjectTeamMaxLines,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(ParticipatingProjectTeamWeight)
                )

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
