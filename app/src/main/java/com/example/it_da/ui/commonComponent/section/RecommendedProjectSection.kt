package com.example.it_da.ui.commonComponent.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.it_da.R
import com.example.it_da.ui.commonComponent.ItdaLayoutDefaults
import com.example.it_da.ui.commonComponent.ItdaSectionHeader
import com.example.it_da.ui.screen.home.component.card.RecommendedProjectCard
import com.example.it_da.ui.screen.home.state.RecommendedProjectUiModel

// Shows the recommended project section with independently clickable project cards.
@Composable
fun RecommendedProjectSection(
    projects: List<RecommendedProjectUiModel>,
    onProjectClick: (String) -> Unit,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ItdaSectionHeader(
            title = stringResource(id = R.string.home_recommended_project_section_title),
            titleFontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(ItdaLayoutDefaults.SectionHeaderContentSpacing))

        Column(
            verticalArrangement = Arrangement.spacedBy(ItdaLayoutDefaults.ProjectCardSpacing),
            modifier = Modifier.fillMaxWidth()
        ) {
            projects.forEach { project ->
                RecommendedProjectCard(
                    project = project,
                    onProjectClick = onProjectClick,
                    onDetailClick = onDetailClick
                )
            }
        }
    }
}
