package vn.hust.edu.studentmanagementapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private lateinit var studentList: MutableList<Student>
    private lateinit var adapter: StudentAdapter
    private val ADD_REQUEST = 1
    private val UPDATE_REQUEST = 2
    private var selectedPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentList = mutableListOf()
        adapter = StudentAdapter(studentList) { position, menuId -> handleMenu(position, menuId) }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_add_student) {
            startActivityForResult(Intent(this, AddStudentActivity::class.java), ADD_REQUEST)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val student = data.getSerializableExtra("student") as Student
            if (requestCode == ADD_REQUEST) {
                studentList.add(student)
                adapter.notifyItemInserted(studentList.size - 1)
            } else if (requestCode == UPDATE_REQUEST) {
                studentList[selectedPosition] = student
                adapter.notifyItemChanged(selectedPosition)
            }
        }
    }

    private fun handleMenu(position: Int, menuId: Int) {
        val student = studentList[position]
        selectedPosition = position

        when (menuId) {
            R.id.action_update -> {
                val intent = Intent(this, UpdateStudentActivity::class.java)
                intent.putExtra("student", student)
                startActivityForResult(intent, UPDATE_REQUEST)
            }

            R.id.action_delete -> {
                AlertDialog.Builder(this)
                    .setTitle("Xác nhận")
                    .setMessage("Bạn có chắc muốn xóa sinh viên này?")
                    .setPositiveButton("Xóa") { _, _ ->
                        studentList.removeAt(position)
                        adapter.notifyItemRemoved(position)
                    }
                    .setNegativeButton("Hủy", null)
                    .show()
            }

            R.id.action_call -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${student.phone}")
                startActivity(intent)
            }

            R.id.action_email -> {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:${student.email}")
                startActivity(intent)
            }
        }
    }
}