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

package com.sergiobelda.todometer.common.database.dao

import com.sergiobelda.todometer.DbProject
import com.sergiobelda.todometer.TodometerDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProjectDao : IProjectDao, KoinComponent {

    private val database: TodometerDatabase by inject()

    override fun getProjects(): Flow<List<DbProject>> =
        database.todometerQueries.selectAllProjects().asFlow().mapToList()

    override fun getProject(id: Long): Flow<DbProject?> =
        database.todometerQueries.selectProject(id).asFlow().mapToOneOrNull()

    override suspend fun insertProject(project: DbProject) =
        database.todometerQueries.insertProject(project)
}