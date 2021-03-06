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

package com.sergiobelda.todometer.ui.addproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergiobelda.todometer.common.data.Result
import com.sergiobelda.todometer.common.usecase.InsertProjectUseCase
import kotlinx.coroutines.launch

class AddProjectViewModel(
    private val insertProjectUseCase: InsertProjectUseCase,
) : ViewModel() {

    // TODO: Migrate to StateFlow
    private val _result = MutableLiveData<Result<String>?>()
    val result: LiveData<Result<String>?> get() = _result

    fun insertProject(name: String, description: String) = viewModelScope.launch {
        _result.value = insertProjectUseCase.invoke(name, description)
    }
}
