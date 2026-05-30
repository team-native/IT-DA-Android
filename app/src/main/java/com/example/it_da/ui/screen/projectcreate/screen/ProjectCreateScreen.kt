package com.example.it_da.ui.screen.projectcreate.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.R
import com.example.it_da.ui.commonComponent.ItdaBottomNavigationBar
import com.example.it_da.ui.commonComponent.ItdaDropdownTextField
import com.example.it_da.ui.commonComponent.ItdaLayoutDefaults
import com.example.it_da.ui.commonComponent.ItdaOutlinedTextField
import com.example.it_da.ui.commonComponent.ItdaPrimaryButton
import com.example.it_da.ui.commonComponent.ItdaSectionHeader
import com.example.it_da.ui.commonComponent.ItdaTopBar
import com.example.it_da.ui.screen.projectcreate.state.ProjectCreateUiState
import com.example.it_da.ui.theme.ITDATheme

// Assembles the project creation screen from reusable form and navigation components.
@Composable
fun ProjectCreateScreen(
    uiState: ProjectCreateUiState,
    onBackClick: () -> Unit,
    onProjectNameChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit,
    onPeriodChange: (String) -> Unit,
    onMethodChange: (String) -> Unit,
    onIntroductionChange: (String) -> Unit,
    onGoalChange: (String) -> Unit,
    onMemberCountChange: (String) -> Unit,
    onRoleChange: (String) -> Unit,
    onTechStackChange: (String) -> Unit,
    onDeadlineChange: (String) -> Unit,
    onDropdownArrowClick: () -> Unit,
    onSubmitClick: () -> Unit,
    onHomeTabClick: () -> Unit,
    onExploreTabClick: () -> Unit,
    onCreateProjectClick: () -> Unit,
    onNotificationTabClick: () -> Unit,
    onProfileTabClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ItdaTopBar(
                title = stringResource(id = R.string.project_create_top_bar_title),
                onBackClick = onBackClick,
                backContentDescription = stringResource(
                    id = R.string.project_create_back_description
                )
            )

            ProjectCreateContent(
                uiState = uiState,
                onProjectNameChange = onProjectNameChange,
                onCategoryChange = onCategoryChange,
                onPeriodChange = onPeriodChange,
                onMethodChange = onMethodChange,
                onIntroductionChange = onIntroductionChange,
                onGoalChange = onGoalChange,
                onMemberCountChange = onMemberCountChange,
                onRoleChange = onRoleChange,
                onTechStackChange = onTechStackChange,
                onDeadlineChange = onDeadlineChange,
                onDropdownArrowClick = onDropdownArrowClick,
                onSubmitClick = onSubmitClick,
                modifier = Modifier.weight(1f)
            )
        }

        ItdaBottomNavigationBar(
            onHomeClick = onHomeTabClick,
            onExploreClick = onExploreTabClick,
            onCreateProjectClick = onCreateProjectClick,
            onNotificationClick = onNotificationTabClick,
            onProfileClick = onProfileTabClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )
    }
}

// Lays out the scrollable form while leaving room for the fixed bottom navigation bar.
@Composable
private fun ProjectCreateContent(
    uiState: ProjectCreateUiState,
    onProjectNameChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit,
    onPeriodChange: (String) -> Unit,
    onMethodChange: (String) -> Unit,
    onIntroductionChange: (String) -> Unit,
    onGoalChange: (String) -> Unit,
    onMemberCountChange: (String) -> Unit,
    onRoleChange: (String) -> Unit,
    onTechStackChange: (String) -> Unit,
    onDeadlineChange: (String) -> Unit,
    onDropdownArrowClick: () -> Unit,
    onSubmitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = ItdaLayoutDefaults.FormHorizontalPadding)
            .padding(top = ItdaLayoutDefaults.LongVerticalSpacing)
            .padding(bottom = ItdaLayoutDefaults.BottomNavigationContentPadding),
        verticalArrangement = Arrangement.spacedBy(ItdaLayoutDefaults.LongVerticalSpacing)
    ) {
        ProjectCreateIntroSection(
            uiState = uiState,
            onProjectNameChange = onProjectNameChange,
            onCategoryChange = onCategoryChange,
            onPeriodChange = onPeriodChange,
            onMethodChange = onMethodChange,
            onIntroductionChange = onIntroductionChange,
            onGoalChange = onGoalChange,
            onDropdownArrowClick = onDropdownArrowClick
        )

        ProjectCreateRecruitSection(
            uiState = uiState,
            onMemberCountChange = onMemberCountChange,
            onRoleChange = onRoleChange,
            onTechStackChange = onTechStackChange,
            onDeadlineChange = onDeadlineChange,
            onDropdownArrowClick = onDropdownArrowClick
        )

        ItdaPrimaryButton(
            enabled = uiState.isSubmitEnabled,
            onClick = onSubmitClick,
            text = stringResource(id = R.string.project_create_submit)
        )
    }
}

// Groups the title, basic information, and description fields with short vertical spacing.
@Composable
private fun ProjectCreateIntroSection(
    uiState: ProjectCreateUiState,
    onProjectNameChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit,
    onPeriodChange: (String) -> Unit,
    onMethodChange: (String) -> Unit,
    onIntroductionChange: (String) -> Unit,
    onGoalChange: (String) -> Unit,
    onDropdownArrowClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(ItdaLayoutDefaults.ShortVerticalSpacing)
    ) {
        ItdaSectionHeader(
            title = stringResource(id = R.string.project_create_screen_title),
            description = stringResource(id = R.string.project_create_basic_info)
        )

        ProjectCreateBasicInfoSection(
            uiState = uiState,
            onProjectNameChange = onProjectNameChange,
            onCategoryChange = onCategoryChange,
            onPeriodChange = onPeriodChange,
            onMethodChange = onMethodChange,
            onDropdownArrowClick = onDropdownArrowClick
        )

        ProjectCreateDescriptionSection(
            uiState = uiState,
            onIntroductionChange = onIntroductionChange,
            onGoalChange = onGoalChange
        )
    }
}

// Displays the basic project fields using the shared sign-up form components.
@Composable
private fun ProjectCreateBasicInfoSection(
    uiState: ProjectCreateUiState,
    onProjectNameChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit,
    onPeriodChange: (String) -> Unit,
    onMethodChange: (String) -> Unit,
    onDropdownArrowClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(ItdaLayoutDefaults.ShortVerticalSpacing)
    ) {
        ItdaOutlinedTextField(
            label = stringResource(id = R.string.project_create_project_name_label),
            value = uiState.projectName,
            onValueChange = onProjectNameChange,
            placeholder = stringResource(id = R.string.project_create_project_name_placeholder)
        )

        ItdaDropdownTextField(
            label = stringResource(id = R.string.project_create_category_label),
            value = uiState.category,
            onValueChange = onCategoryChange,
            placeholder = stringResource(id = R.string.project_create_category_default),
            onArrowClick = onDropdownArrowClick
        )

        ItdaDropdownTextField(
            label = stringResource(id = R.string.project_create_period_label),
            value = uiState.period,
            onValueChange = onPeriodChange,
            placeholder = stringResource(id = R.string.project_create_period_default),
            onArrowClick = onDropdownArrowClick
        )

        ItdaOutlinedTextField(
            label = stringResource(id = R.string.project_create_method_label),
            value = uiState.method,
            onValueChange = onMethodChange,
            placeholder = stringResource(id = R.string.project_create_method_default)
        )
    }
}

// Displays the project introduction and goal fields.
@Composable
private fun ProjectCreateDescriptionSection(
    uiState: ProjectCreateUiState,
    onIntroductionChange: (String) -> Unit,
    onGoalChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(ItdaLayoutDefaults.ShortVerticalSpacing)
    ) {
        ItdaSectionHeader(
            title = stringResource(id = R.string.project_create_description_goal),
            titleFontSize = 13.sp
        )

        ItdaOutlinedTextField(
            label = stringResource(id = R.string.project_create_intro_label),
            value = uiState.introduction,
            onValueChange = onIntroductionChange,
            placeholder = stringResource(id = R.string.project_create_intro_placeholder),
            inputHeight = 82.dp,
            singleLine = false
        )

        ItdaOutlinedTextField(
            label = stringResource(id = R.string.project_create_goal_label),
            value = uiState.goal,
            onValueChange = onGoalChange,
            placeholder = stringResource(id = R.string.project_create_goal_placeholder),
            inputHeight = 82.dp,
            singleLine = false
        )
    }
}

// Displays the recruitment fields for members, role, stack, and deadline.
@Composable
private fun ProjectCreateRecruitSection(
    uiState: ProjectCreateUiState,
    onMemberCountChange: (String) -> Unit,
    onRoleChange: (String) -> Unit,
    onTechStackChange: (String) -> Unit,
    onDeadlineChange: (String) -> Unit,
    onDropdownArrowClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(ItdaLayoutDefaults.ShortVerticalSpacing)
    ) {
        ItdaSectionHeader(
            title = stringResource(id = R.string.project_create_recruit_info),
            titleFontSize = 13.sp
        )

        ItdaDropdownTextField(
            label = stringResource(id = R.string.project_create_member_count_label),
            value = uiState.memberCount,
            onValueChange = onMemberCountChange,
            placeholder = stringResource(id = R.string.project_create_member_count_default),
            onArrowClick = onDropdownArrowClick
        )

        ItdaDropdownTextField(
            label = stringResource(id = R.string.project_create_role_label),
            value = uiState.role,
            onValueChange = onRoleChange,
            placeholder = stringResource(id = R.string.project_create_role_default),
            onArrowClick = onDropdownArrowClick
        )

        ItdaOutlinedTextField(
            label = stringResource(id = R.string.project_create_tech_stack_label),
            value = uiState.techStack,
            onValueChange = onTechStackChange,
            placeholder = stringResource(id = R.string.project_create_tech_stack_placeholder)
        )

        ItdaOutlinedTextField(
            label = stringResource(id = R.string.project_create_deadline_label),
            value = uiState.deadline,
            onValueChange = onDeadlineChange,
            placeholder = stringResource(id = R.string.project_create_deadline_placeholder)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProjectCreateScreenPreview() {
    var uiState by remember { mutableStateOf(ProjectCreateUiState()) }

    ITDATheme {
        ProjectCreateScreen(
            uiState = uiState,
            onBackClick = {},
            onProjectNameChange = { projectName ->
                uiState = uiState.copy(projectName = projectName)
            },
            onCategoryChange = { category ->
                uiState = uiState.copy(category = category)
            },
            onPeriodChange = { period ->
                uiState = uiState.copy(period = period)
            },
            onMethodChange = { method ->
                uiState = uiState.copy(method = method)
            },
            onIntroductionChange = { introduction ->
                uiState = uiState.copy(introduction = introduction)
            },
            onGoalChange = { goal ->
                uiState = uiState.copy(goal = goal)
            },
            onMemberCountChange = { memberCount ->
                uiState = uiState.copy(memberCount = memberCount)
            },
            onRoleChange = { role ->
                uiState = uiState.copy(role = role)
            },
            onTechStackChange = { techStack ->
                uiState = uiState.copy(techStack = techStack)
            },
            onDeadlineChange = { deadline ->
                uiState = uiState.copy(deadline = deadline)
            },
            onDropdownArrowClick = {},
            onSubmitClick = {},
            onHomeTabClick = {},
            onExploreTabClick = {},
            onCreateProjectClick = {},
            onNotificationTabClick = {},
            onProfileTabClick = {}
        )
    }
}
