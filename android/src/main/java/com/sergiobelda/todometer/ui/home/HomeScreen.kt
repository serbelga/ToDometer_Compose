/*
 * Copyright 2020 Sergio Belda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sergiobelda.todometer.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sergiobelda.todometer.android.R
import com.sergiobelda.todometer.common.data.doIfSuccess
import com.sergiobelda.todometer.common.model.Project
import com.sergiobelda.todometer.common.model.ProjectTasks
import com.sergiobelda.todometer.common.model.Tag
import com.sergiobelda.todometer.common.model.Task
import com.sergiobelda.todometer.compose.mapper.composeColorOf
import com.sergiobelda.todometer.compose.ui.components.DragIndicator
import com.sergiobelda.todometer.compose.ui.components.HorizontalDivider
import com.sergiobelda.todometer.compose.ui.task.TaskItem
import com.sergiobelda.todometer.compose.ui.theme.TodometerColors
import com.sergiobelda.todometer.compose.ui.theme.TodometerTypography
import com.sergiobelda.todometer.compose.ui.theme.primarySelected
import com.sergiobelda.todometer.ui.components.ToDometerTopAppBar
import com.sergiobelda.todometer.ui.theme.ToDometerTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    addProject: () -> Unit,
    addTask: () -> Unit,
    openTask: (String) -> Unit,
    homeViewModel: HomeViewModel = getViewModel()
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val selectedTask = remember { mutableStateOf("") }
    val deleteTaskAlertDialogState = remember { mutableStateOf(false) }

    var projects: List<Project> by remember { mutableStateOf(emptyList()) }
    val projectsResultState = homeViewModel.projects.collectAsState()
    projectsResultState.value.doIfSuccess { projects = it }

    var projectSelected: ProjectTasks? by remember { mutableStateOf(null) }
    val projectSelectedState = homeViewModel.projectSelected.collectAsState()
    projectSelectedState.value.doIfSuccess { projectSelected = it }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetElevation = 16.dp,
        sheetContent = {
            SheetContainer(
                projectSelected?.id,
                projects,
                addProject,
                selectProject = {
                    homeViewModel.setProjectSelected(it)
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                ToDometerTopAppBar(projectSelected)
            },
            bottomBar = {
                if (projects.isNotEmpty()) {
                    BottomAppBar(
                        backgroundColor = TodometerColors.surface,
                        contentColor = contentColorFor(TodometerColors.surface),
                        cutoutShape = CircleShape
                    ) {
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            IconButton(
                                onClick = {
                                    scope.launch { sheetState.show() }
                                }
                            ) {
                                Icon(Icons.Rounded.Menu, contentDescription = "Menu")
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(Icons.Rounded.MoreVert, contentDescription = "More")
                            }
                        }
                    }
                }
            },
            content = {
                if (deleteTaskAlertDialogState.value) {
                    RemoveTaskAlertDialog(
                        deleteTaskAlertDialogState,
                        deleteTask = { homeViewModel.deleteTask(selectedTask.value) }
                    )
                }
                if (projectSelected?.tasks.isNullOrEmpty()) {
                    EmptyTasksListView()
                } else {
                    TasksListView(
                        projectSelected?.tasks ?: emptyList(),
                        onDoingClick = {
                            homeViewModel.setTaskDoing(it)
                        },
                        onDoneClick = {
                            homeViewModel.setTaskDone(it)
                        },
                        onTaskItemClick = openTask,
                        onTaskItemLongClick = {
                            deleteTaskAlertDialogState.value = true
                            selectedTask.value = it
                        }
                    )
                }
            },
            floatingActionButton = {
                if (!projects.isNullOrEmpty()) {
                    FloatingActionButton(
                        onClick = addTask
                    ) {
                        Icon(Icons.Rounded.Add, contentDescription = "Add task")
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true
        )
    }
}

@Composable
fun RemoveTaskAlertDialog(
    showRemoveTaskAlertDialog: MutableState<Boolean>,
    deleteTask: () -> Unit
) {
    AlertDialog(
        title = {
            Text(stringResource(id = R.string.remove_task))
        },
        onDismissRequest = {
            showRemoveTaskAlertDialog.value = false
        },
        text = {
            Text(stringResource(id = R.string.remove_task_question))
        },
        confirmButton = {
            TextButton(
                onClick = {
                    deleteTask()
                    showRemoveTaskAlertDialog.value = false
                }
            ) {
                Text(stringResource(id = android.R.string.ok))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    showRemoveTaskAlertDialog.value = false
                }
            ) {
                Text(stringResource(id = R.string.cancel))
            }
        }
    )
}

@Composable
fun SheetContainer(
    selectedProjectId: String?,
    projectList: List<Project>,
    addProject: () -> Unit,
    selectProject: (String) -> Unit,
    tagList: List<Tag> = emptyList()
) {
    Column(modifier = Modifier.height(480.dp)) {
        DragIndicator()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(56.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.projects).toUpperCase(),
                style = typography.overline
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        HorizontalDivider()
        LazyColumn {
            items(projectList) { project ->
                ProjectListItem(project, project.id == selectedProjectId, selectProject)
            }
        }
        TextButton(onClick = addProject, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Rounded.Add, contentDescription = "Add project")
            Text(text = stringResource(id = R.string.add_project))
        }
        HorizontalDivider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(56.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.tags).toUpperCase(),
                style = typography.overline
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        HorizontalDivider()
        LazyColumn {
            items(tagList) { tag ->
                TagItem(tag)
            }
        }
        TextButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Rounded.Add, contentDescription = "Add tag")
            Text(text = stringResource(id = R.string.add_tag))
        }
    }
}

@Composable
fun ProjectListItem(
    project: Project,
    selected: Boolean,
    onItemClick: (String) -> Unit
) {
    val background = if (selected) {
        Modifier.background(TodometerColors.primarySelected)
    } else {
        Modifier
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(56.dp).clickable(onClick = { onItemClick(project.id) })
            .then(background)
    ) {
        val selectedColor =
            if (selected) TodometerColors.primary else TodometerColors.onSurface.copy(alpha = ContentAlpha.medium)
        Icon(
            Icons.Default.Book,
            tint = selectedColor,
            contentDescription = null,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = project.name,
            color = selectedColor,
            style = TodometerTypography.subtitle2,
            modifier = Modifier.weight(1f).padding(start = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun TagItem(tag: Tag) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(56.dp).clickable(onClick = { })
    ) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(16.dp)
                .clip(CircleShape)
                .background(TodometerColors.composeColorOf(tag))
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = tag.name,
                style = TodometerTypography.subtitle2,
                modifier = Modifier.padding(start = 16.dp).weight(1f)
            )
        }
    }
}

@Composable
fun TasksListView(
    tasks: List<Task>,
    onDoingClick: (String) -> Unit,
    onDoneClick: (String) -> Unit,
    onTaskItemClick: (String) -> Unit,
    onTaskItemLongClick: (String) -> Unit
) {
    LazyColumn {
        itemsIndexed(tasks) { index, task ->
            TaskItem(
                task,
                onDoingClick = onDoingClick,
                onDoneClick = onDoneClick,
                onClick = onTaskItemClick,
                onLongClick = onTaskItemLongClick
            )
            if (index == tasks.lastIndex) {
                Spacer(modifier = Modifier.height(56.dp))
            }
        }
    }
}

@Composable
fun EmptyTasksListView() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center).padding(bottom = 72.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.project_completed),
                modifier = Modifier.size(240.dp),
                contentDescription = null
            )
            Text(stringResource(id = R.string.no_tasks))
        }
    }
}

@Preview
@Composable
fun EmptyProjectTaskListPreview() {
    ToDometerTheme {
        EmptyTasksListView()
    }
}
