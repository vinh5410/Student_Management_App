package vn.hust.edu.studentmanagementapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = ArrayList<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        studentAdapter = StudentAdapter(this, studentList)
        recyclerView.adapter = studentAdapter

        // Danh sách mẫu ban đầu
        studentList.add(Student("Nguyen Van A", "SV001", "nguyenvana@example.com", "0123456789"))
        studentList.add(Student("Tran Thi B", "SV002", "tranthib@example.com", "0987654321"))
    }

    // Menu hiển thị trên thanh công cụ
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Xử lý khi chọn item menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_student -> {
                val intent = Intent(this, UpdateStudentActivity::class.java)
                startActivityForResult(intent, 101)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Nhận kết quả từ màn hình thêm/cập nhật sinh viên
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK) {
            val student = data?.getParcelableExtra<Student>("student")
            student?.let {
                studentList.add(it)
                studentAdapter.notifyDataSetChanged()
            }
        }
    }
}
