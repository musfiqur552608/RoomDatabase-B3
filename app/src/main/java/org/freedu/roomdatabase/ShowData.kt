package org.freedu.roomdatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.freedu.roomdatabase.databinding.ActivityShowDataBinding
import org.freedu.roomdatabase.datashow.ViewAdapter

class ShowData : AppCompatActivity() {
    lateinit var binding: ActivityShowDataBinding
    lateinit var studentDB: StudentDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityShowDataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        studentDB = StudentDatabase.getDatabase(this)
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val studentList = studentDB.studentDao().getAll()
            val allListShowData = studentList.map{
                Student(id = 0, firstName = it.firstName, lastName = it.lastName, rollno = it.rollno.toInt())
            }
            val adapter = ViewAdapter(allListShowData)
            binding.recycleView.adapter = adapter
        }
    }
}