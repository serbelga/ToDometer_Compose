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

import com.sergiobelda.todometer.TaskEntity
import com.sergiobelda.todometer.common.model.Task
import com.sergiobelda.todometer.common.model.TypeConverters.tagValueOf
import com.sergiobelda.todometer.common.model.TypeConverters.taskStateValueOf

fun TaskEntity.toDomain() = Task(
    id = id,
    title = title,
    description = description,
    state = taskStateValueOf(state),
    projectId = project_id,
    tag = tagValueOf(tag ?: ""),
    sync = sync
)

fun Iterable<TaskEntity>.toDomain() = this.map {
    it.toDomain()
}

fun Task.toEntity() = TaskEntity(
    id,
    title,
    description,
    state.toString(),
    projectId,
    tag.toString(),
    sync
)
