package com.example.it_da.ui.commonComponent.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.it_da.R
import com.example.it_da.ui.commonComponent.ItdaLayoutDefaults
import com.example.it_da.ui.commonComponent.ItdaSectionHeader
import com.example.it_da.ui.screen.home.component.card.ParticipatingProjectCard
import com.example.it_da.ui.screen.home.state.ParticipatingProjectUiModel

// Shows the user's participating projects with a card type separate from recommendations.
@Composable
fun ParticipatingProjectSection(
    projects: List<ParticipatingProjectUiModel>,
    onProjectClick: (String) -> Unit,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ItdaSectionHeader(
            title = stringResource(id = R.string.home_participating_project_section_title)
        )

        Spacer(modifier = Modifier.height(ItdaLayoutDefaults.SectionHeaderContentSpacing))

        Column(
            verticalArrangement = Arrangement.spacedBy(ItdaLayoutDefaults.ProjectCardSpacing),
            modifier = Modifier.fillMaxWidth()
        ) {
            projects.forEach { project ->
                ParticipatingProjectCard(
                    project = project,
                    onProjectClick = onProjectClick,
                    onDetailClick = onDetailClick
                )
            }
        }
    }
}
