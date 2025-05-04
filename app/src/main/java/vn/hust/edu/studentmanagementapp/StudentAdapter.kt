package vn.hust.edu.studentmanagementapp
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private val context: Context,
    private val studentList: ArrayList<Student>
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvMSSV: TextView = view.findViewById(R.id.tvMSSV)
        val btnMenu: ImageButton = view.findViewById(R.id.btnMenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.tvName.text = student.name
        holder.tvMSSV.text = student.mssv

        holder.btnMenu.setOnClickListener {
            val popup = PopupMenu(context, holder.btnMenu)
            popup.menuInflater.inflate(R.menu.student_menu, popup.menu)
            popup.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_update -> {
                        val intent = Intent(context, UpdateStudentActivity::class.java)
                        intent.putExtra("student", student)
                        intent.putExtra("position", position)
                        (context as Activity).startActivityForResult(intent, 101)
                    }

                    R.id.menu_delete -> {
                        AlertDialog.Builder(context)
                            .setTitle("Xóa sinh viên")
                            .setMessage("Bạn có chắc muốn xóa?")
                            .setPositiveButton("Xóa") { _, _ ->
                                studentList.removeAt(position)
                                notifyDataSetChanged()
                            }
                            .setNegativeButton("Hủy", null)
                            .show()
                    }

                    R.id.menu_call -> {
                        val callIntent = Intent(Intent.ACTION_DIAL)
                        callIntent.data = Uri.parse("tel:${student.phone}")
                        context.startActivity(callIntent)
                    }

                    R.id.menu_email -> {
                        val emailIntent = Intent(Intent.ACTION_SENDTO)
                        emailIntent.data = Uri.parse("mailto:${student.email}")
                        context.startActivity(emailIntent)
                    }
                }
                true
            }
            popup.show()
        }
    }

    override fun getItemCount(): Int = studentList.size
}
