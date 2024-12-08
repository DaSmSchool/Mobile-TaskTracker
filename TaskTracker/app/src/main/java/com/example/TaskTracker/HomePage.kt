package com.example.TaskTracker

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContentInfo
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.updateLayoutParams

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomePage : Fragment() {

    lateinit var taskGroups: ArrayList<TaskGroup>
    lateinit var rowContainer: ConstraintLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ConstraintLayout>(R.id.rowContainer)
        taskGroups = ArrayList<TaskGroup>()
        rowContainer = view.findViewById(R.id.rowContainer)

        var tg: TaskGroup = TaskGroup("blah")
        tg.addTask("t1")
        tg.addTask("t456")
        taskGroups.add(tg)

        var tg2: TaskGroup = TaskGroup("blah2")
        tg2.addTask("t1")
        tg2.addTask("t456")
        taskGroups.add(tg2)

        Log.i("TestTrackerTest", tg.toString())
    }

    override fun onResume() {
        super.onResume()
        updateRows()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("TaskTrackerLog", taskGroups[0].toString())
        for (taskGroupInd in 0..taskGroups.size) {
            outState.putString("group${taskGroupInd}", taskGroups[taskGroupInd].toString())
        }

    }

    fun updateRows() {
        for (child in rowContainer.children) {
            rowContainer.removeView(child)
        }

        var layoutRows = ArrayList<View>()

        for (groupInd in 0..taskGroups.size) {
            var rowView: View = LayoutInflater.from(this.context).inflate(R.layout.list_row, null)
            layoutRows.add(rowView)
            rowContainer.addView(rowView)
            if (groupInd != 0) {
                var params = rowView.layoutParams as ConstraintLayout.LayoutParams
                params.topToBottom = layoutRows.get(layoutRows.size-1).id
                rowView.requestLayout()
            }
        }
    }

}