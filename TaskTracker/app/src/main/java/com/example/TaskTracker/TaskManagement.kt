package com.example.TaskTracker

import android.util.Log
import java.util.Date

class Task(name: String = " ") {

    var taskName: String = name



    override fun toString(): String {
        return "{name:${taskName}}"
    }
}

class TaskGroup(name: String ="") {

    var taskList: ArrayList<Task> = ArrayList()

    var groupName: String = name

    constructor(
        constructed: String,
        built: Boolean
    ) : this() {
        if (constructed != "" && built) {
            var parseString: String = constructed.substring(1, constructed.length - 1)
            var splitString = parseString.split(",")
            groupName = splitString[0].split(":")[1]

            var strTasks: String = splitString[2].split(":")[1]
            strTasks = strTasks.substring(1, strTasks.length - 1)
            for (task in strTasks.split("^")) {
                taskList.add(Task(task.substring(task.indexOf(":")+1, task.indexOf("}"))))
            }
        }
    }



    fun addTask(name: String) {
        taskList.add(Task(name))
    }

    override fun toString(): String {
        var assemble: String = "{name:${groupName},taskList:{"
        for (task in taskList) {
            assemble += task
            assemble += "^"
        }
        assemble = assemble.substring(0, assemble.length-1)
        assemble += "}}"
        return assemble
    }

}
