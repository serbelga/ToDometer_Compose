package com.sergiobelda.todometer.common.model

data class ProjectTasks(
    val id: String,
    val name: String,
    val description: String,
    val tasks: List<Task> = arrayListOf(),
    val sync: Boolean
) {
    override fun toString() = name
}
