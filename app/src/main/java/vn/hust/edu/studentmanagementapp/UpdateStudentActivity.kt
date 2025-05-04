package vn.hust.edu.studentmanagementapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class UpdateStudentActivity : Activity() {

    private lateinit var edtName: EditText
    private lateinit var edtMSSV: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var btnSave: Button
    private var student: Student? = null
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_student)

        edtName = findViewById(R.id.edtName)
        edtMSSV = findViewById(R.id.edtMSSV)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        btnSave = findViewById(R.id.btnSave)

        student = intent.getParcelableExtra("student")
        position = intent.getIntExtra("position", -1)

        student?.let {
            edtName.setText(it.name)
            edtMSSV.setText(it.mssv)
            edtEmail.setText(it.email)
            edtPhone.setText(it.phone)
        }

        btnSave.setOnClickListener {
            val updatedStudent = Student(
                edtName.text.toString(),
                edtMSSV.text.toString(),
                edtEmail.text.toString(),
                edtPhone.text.toString()
            )

            val resultIntent = Intent()
            resultIntent.putExtra("student", updatedStudent)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
