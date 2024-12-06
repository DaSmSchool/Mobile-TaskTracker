package com.example.TaskTracker

class Task(taskName: String, dueDate: String, createDate: String) {
    var name: String = taskName

    init {
        
    }

}

class TaskGroup {
    var taskList: ArrayList<Task> = ArrayList()

    init {

    }
}