package com.nerdstone.neatform

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.nerdstone.neatform.form.FormActivity
import com.nerdstone.neatform.form.FormData
import com.nerdstone.neatform.form.FormRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var formRecyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var floatingActionButton: com.google.android.material.floatingactionbutton.FloatingActionButton
    private lateinit var exitAppImageView: ImageView

    private var formRecyclerAdapter = FormRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        formRecyclerView = findViewById(R.id.formRecyclerView)
        floatingActionButton = findViewById(R.id.newFormFab)
        exitAppImageView = findViewById(R.id.exitAppImageView)

        formRecyclerView.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this)
        formRecyclerAdapter.formList =
            mutableListOf(
                FormData(
                    formTitle = "Programmer Survey",
                    formCategory = "IT Computer",
                    filePath = "sample/sample_one_form.json"
                ),
                FormData(
                    formTitle = "Customer feedback",
                    formCategory = "Marketing",
                    filePath = "sample/sample_one_form.json"
                )
            )

        formRecyclerView.adapter = formRecyclerAdapter
        formRecyclerAdapter.listener = View.OnClickListener {
            val viewHolder = it.tag as androidx.recyclerview.widget.RecyclerView.ViewHolder
            val formData = formRecyclerAdapter.formList[viewHolder.adapterPosition]
            val intent = Intent(this, FormActivity::class.java)
            intent.putExtra("path", formData.filePath)
            intent.putExtra("page", formData.formTitle)
            startActivity(intent)
        }

        newFormFab.setOnClickListener(this)
        exitAppImageView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when {
            v?.id == R.id.newFormFab -> com.google.android.material.snackbar.Snackbar.make(
                findViewById(R.id.mainActivityConstraintLayout),
                "Action not yet implemented",
                com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
            ).show()
            v?.id == R.id.exitAppImageView -> finish()
        }
    }
}