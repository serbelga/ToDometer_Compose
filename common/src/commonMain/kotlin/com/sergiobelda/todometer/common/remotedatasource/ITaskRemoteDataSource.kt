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

package com.sergiobelda.todometer.common.remotedatasource

import com.sergiobelda.todometer.common.data.Result
import com.sergiobelda.todometer.common.model.Tag
import com.sergiobelda.todometer.common.model.Task

interface ITaskRemoteDataSource {

    suspend fun getTasksByProjectId(id: String): Result<List<Task>>

    suspend fun insertTask(
        id: String? = null,
        title: String,
        description: String,
        projectId: String,
        tag: Tag
    ): Result<String>

    suspend fun updateTask()

    suspend fun updateTaskState()

    suspend fun deleteTask(id: String): Result<String>
}
