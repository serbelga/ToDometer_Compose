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

package com.sergiobelda.todometer.common.database.mapper

import com.sergiobelda.todometer.ProjectEntity
import com.sergiobelda.todometer.common.database.ProjectTasksRelation
import com.sergiobelda.todometer.common.model.Project
import com.sergiobelda.todometer.common.model.ProjectTasks

fun ProjectEntity.toDomain() = Project(
    id,
    name,
    description,
    sync
)

fun Iterable<ProjectEntity>.toDomain() = this.map {
    it.toDomain()
}

fun ProjectTasksRelation.toDomain() = ProjectTasks(
    project.id,
    project.name,
    project.description,
    tasks.map { it.toDomain() },
    project.sync
)

fun Project.toEntity() = ProjectEntity(
    id,
    name,
    description,
    sync
)
