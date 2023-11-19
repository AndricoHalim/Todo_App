package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.todoapp.databinding.ActivityTaskDetailBinding
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskDetailBinding

    private val detailViewModel by viewModels<DetailTaskViewModel> {
        ViewModelFactory.getInstance(application)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO 11 : Show detail task and implement delete action
        val idTask = intent.getIntExtra(TASK_ID, 0)
        detailViewModel.setTaskId(idTask)

        initAction()

        binding.btnDeleteTask.setOnClickListener{
            deleteTask()
        }
    }

    private fun initAction(){
        detailViewModel.task.observe(this){task ->
            if (task != null){
                binding.detailEdTitle.setText(task.title)
                binding.detailEdDescription.setText(task.description)
                binding.detailEdDueDate.setText(DateConverter.convertMillisToString(task.dueDateMillis))
            }
        }
    }

    private fun deleteTask(){
        detailViewModel.deleteTask()
        Toast.makeText(this, "Task Deleted", Toast.LENGTH_SHORT).show()
        finish()
    }
}