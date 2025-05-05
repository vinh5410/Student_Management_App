package vn.hust.edu.studentmanagementapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class UpdateStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update_student)

        val student = intent.getSerializableExtra("student") as Student

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtId = findViewById<EditText>(R.id.edtId)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val btnSave = findViewById<Button>(R.id.btnSave)

        edtName.setText(student.name)
        edtId.setText(student.id)
        edtEmail.setText(student.email)
        edtPhone.setText(student.phone)

        btnSave.setOnClickListener {
            val updatedStudent = Student(
                name = edtName.text.toString(),
                id = edtId.text.toString(),
                email = edtEmail.text.toString(),
                phone = edtPhone.text.toString()
            )

            val resultIntent = Intent()
            resultIntent.putExtra("student", updatedStudent)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}