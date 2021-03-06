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

package com.sergiobelda.todometer.common.localdatasource

import com.sergiobelda.todometer.common.data.Result
import com.sergiobelda.todometer.common.model.Project
import com.sergiobelda.todometer.common.model.ProjectTasks
import kotlinx.coroutines.flow.Flow

interface IProjectLocalDataSource {

    fun getProjects(): Flow<Result<List<Project>>>

    fun getProject(id: String): Flow<Result<ProjectTasks?>>

    suspend fun insertProject(project: Project): Result<String>

    suspend fun insertProjects(projects: List<Project>)

    suspend fun updateProject(project: Project)

    suspend fun deleteProject(id: String)
}
