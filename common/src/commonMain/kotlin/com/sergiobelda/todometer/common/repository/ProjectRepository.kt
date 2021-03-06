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

package com.sergiobelda.todometer.common.repository

import com.sergiobelda.todometer.common.data.Result
import com.sergiobelda.todometer.common.data.doIfSuccess
import com.sergiobelda.todometer.common.localdatasource.IProjectLocalDataSource
import com.sergiobelda.todometer.common.model.Project
import com.sergiobelda.todometer.common.model.ProjectTasks
import com.sergiobelda.todometer.common.remotedatasource.IProjectRemoteDataSource
import com.sergiobelda.todometer.common.util.randomUUIDString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProjectRepository(
    private val projectLocalDataSource: IProjectLocalDataSource,
    private val projectRemoteDataSource: IProjectRemoteDataSource
) : IProjectRepository {

    override fun getProject(id: String): Flow<Result<ProjectTasks?>> =
        projectLocalDataSource.getProject(id)

    override fun getProjects(): Flow<Result<List<Project>>> =
        projectLocalDataSource.getProjects().map { result ->
            result.doIfSuccess { projects ->
                projects.filter { !it.sync }.forEach { project ->
                    synchronizeProjectRemotely(project.id, project.name, project.description)
                }
            }
        }

    override suspend fun refreshProject(id: String) {
        val projectResult = projectRemoteDataSource.getProject(id)
        projectResult.doIfSuccess { project ->
            projectLocalDataSource.insertProject(project)
        }
    }

    override suspend fun refreshProjects() {
        val projectsResult = projectRemoteDataSource.getProjects()
        projectsResult.doIfSuccess {
            projectLocalDataSource.insertProjects(it)
        }
    }

    override suspend fun insertProject(name: String, description: String): Result<String> {
        var sync = false
        // TODO Set null to indicate DAO need to generate UUID
        var projectId = randomUUIDString()
        projectRemoteDataSource.insertProject(name = name, description = description).doIfSuccess {
            sync = true
            projectId = it
        }
        return projectLocalDataSource.insertProject(
            Project(
                id = projectId,
                name = name,
                description = description,
                sync = sync
            )
        )
    }

    private suspend fun synchronizeProjectRemotely(id: String, name: String, description: String) {
        val result = projectRemoteDataSource.insertProject(id = id, name = name, description = description)
        result.doIfSuccess {
            projectLocalDataSource.updateProject(
                Project(
                    id = id,
                    name = name,
                    description = description,
                    sync = true
                )
            )
        }
    }
}
