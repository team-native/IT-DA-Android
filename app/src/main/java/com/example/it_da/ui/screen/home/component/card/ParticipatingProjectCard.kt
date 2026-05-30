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
import com.example.it_da.ui.commonComponent.ItdaCard
import com.example.it_da.ui.commonComponent.ItdaOutlinedBadge
import com.example.it_da.ui.commonComponent.ItdaUnderlinedTextButton
import com.example.it_da.ui.screen.home.component.HomeProjectContentEndPadding
import com.example.it_da.ui.screen.home.component.HomeProjectContentStartPadding
import com.example.it_da.ui.screen.home.component.HomeProjectTitleStartPadding
import com.example.it_da.ui.screen.home.state.ParticipatingProjectUiModel
import com.example.it_da.ui.theme.ItdaPrimaryTextColor
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val ParticipatingProjectTitleRoleSpacing = 8.dp
private val ParticipatingProjectRoleTeamSpacing = 36.dp

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
            .heightIn(min = 106.dp)
            .clickable {
                onProjectClick(project.id)
        }
    ) {
        Column(
            modifier = Modifier.padding(
                top = 13.dp,
                bottom = 12.dp
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
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                ItdaOutlinedBadge(text = project.statusText)
            }

            Spacer(modifier = Modifier.height(ParticipatingProjectTitleRoleSpacing))

            Text(
                text = project.myRole,
                color = ItdaSecondaryTextColor,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
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
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
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
