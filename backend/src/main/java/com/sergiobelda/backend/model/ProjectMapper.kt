/*
 * Copyright 2021 Sergio Belda
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

package com.sergiobelda.backend.model

import com.sergiobelda.backend.database.entity.NewProjectEntity
import com.sergiobelda.backend.database.entity.ProjectEntity
import com.sergiobelda.backend.database.entity.ProjectTasksRelation
import java.util.UUID

fun ProjectEntity.toProject() = Project(
    id = id.toString(),
    name = name,
    description = description
)

fun Iterable<ProjectEntity>.toProjectList() = this.map { projectEntity ->
    projectEntity.toProject()
}

fun ProjectTasksRelation.toProjectTasks() =
    ProjectTasks(
        id = project.id.toString(),
        name = project.name,
        description = project.description,
        tasks = tasks.map { it.toTask() }
    )

fun Project.toProjectEntity() = ProjectEntity(
    id = UUID.fromString(id),
    name = name,
    description = description
)

fun NewProject.toNewProjectEntity() = NewProjectEntity(
    id = id?.let { UUID.fromString(it) },
    name = name,
    description = description
)
