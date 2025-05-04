package vn.hust.edu.studentmanagementapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val nameEditText: EditText = findViewById(R.id.edtName)
        val mssvEditText: EditText = findViewById(R.id.edtMSSV)
        val emailEditText: EditText = findViewById(R.id.edtEmail)
        val phoneEditText: EditText = findViewById(R.id.edtPhone)
        val saveButton: Button = findViewById(R.id.btnSave)

        student = intent.getParcelableExtra("student")
        student?.let {
            nameEditText.setText(it.name)
            mssvEditText.setText(it.mssv)
            emailEditText.setText(it.email)
            phoneEditText.setText(it.phone)
        }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val mssv = mssvEditText.text.toString()
            val email = emailEditText.text.toString()
            val phoneNumber = phoneEditText.text.toString()

            if (name.isNotEmpty() && mssv.isNotEmpty() && email.isNotEmpty() && phoneNumber.isNotEmpty()) {
                val updatedStudent = Student(name, mssv, email, phoneNumber)
                val resultIntent = Intent().apply {
                    putExtra("student", updatedStudent)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
