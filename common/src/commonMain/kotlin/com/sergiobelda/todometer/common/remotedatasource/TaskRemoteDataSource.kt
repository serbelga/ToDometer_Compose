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

import com.sergiobelda.todometer.common.api.client.ITaskApiClient
import com.sergiobelda.todometer.common.api.mapper.toTaskTagList
import com.sergiobelda.todometer.common.api.request.NewTaskRequestBody
import com.sergiobelda.todometer.common.api.safeApiCall
import com.sergiobelda.todometer.common.data.Result
import com.sergiobelda.todometer.common.model.Tag
import com.sergiobelda.todometer.common.model.Task
import com.sergiobelda.todometer.common.model.TaskState

class TaskRemoteDataSource(private val taskApiClient: ITaskApiClient) : ITaskRemoteDataSource {

    override suspend fun getTasksByProjectId(id: String): Result<List<Task>> =
        safeApiCall {
            taskApiClient.getTasksByProjectId(id)
        }.map { it.toTaskTagList() }

    override suspend fun insertTask(
        id: String?,
        title: String,
        description: String,
        projectId: String,
        tag: Tag
    ): Result<String> = safeApiCall {
        taskApiClient.insertTask(
            NewTaskRequestBody(
                id,
                title,
                description,
                TaskState.DOING.toString(),
                projectId,
                tag.toString()
            )
        )
    }

    override suspend fun updateTask() {

    }

    override suspend fun updateTaskState() {

    }

    override suspend fun deleteTask(id: String): Result<String> = safeApiCall {
        taskApiClient.deleteTask(id)
    }
}
