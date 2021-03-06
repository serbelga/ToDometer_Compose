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

package com.sergiobelda.backend.service

import com.sergiobelda.backend.model.NewProject
import com.sergiobelda.backend.model.Project

interface IProjectService {

    suspend fun getProject(id: String): Project

    suspend fun getProjects(): List<Project>

    suspend fun insertProject(newProject: NewProject): String

    suspend fun updateProject(project: Project)

    suspend fun deleteProject(id: String)
}
