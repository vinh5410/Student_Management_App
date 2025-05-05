package vn.hust.edu.studentmanagementapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update_student)

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtId = findViewById<EditText>(R.id.edtId)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val student = Student(
                name = edtName.text.toString(),
                id = edtId.text.toString(),
                email = edtEmail.text.toString(),
                phone = edtPhone.text.toString()
            )

            val resultIntent = Intent()
            resultIntent.putExtra("student", student)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}