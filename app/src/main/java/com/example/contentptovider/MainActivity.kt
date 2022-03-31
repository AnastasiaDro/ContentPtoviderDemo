package com.example.contentptovider

import android.content.ContentValues
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nextBtn = findViewById<Button>(R.id.button1)
        val clearBtn = findViewById<Button>(R.id.clearBtn)
        val prevBtn = findViewById<Button>(R.id.prevBtn)
        val insertBtn = findViewById<Button>(R.id.insertBtn)
        val updateBtn = findViewById<Button>(R.id.updateBtn)
        val deleteBtn = findViewById<Button>(R.id.deleteBtn)
        val editText1 = findViewById<EditText>(R.id.editTextTextPersonName)
        val editText2 = findViewById<EditText>(R.id.editTextTextPersonName3)


        //
        var rs : Cursor? = contentResolver.query(AcronymProvider.CONTENT_URI,
            arrayOf(AcronymProvider._ID, AcronymProvider.NAME, AcronymProvider.MEANING), null, null, AcronymProvider.NAME)

       //btnNext
        nextBtn.setOnClickListener {
            if (rs?.moveToNext()!!) {
                editText1.setText(rs.getString(1))
                editText2.setText(rs.getString(2))
            }
        }
        clearBtn.setOnClickListener{
            editText1.setText("")
            editText2.setText("")
            editText1.requestFocus()
        }

        prevBtn.setOnClickListener {
            if (rs?.moveToPrevious()!!) {
                editText1.setText(rs.getString(1))
                editText2.setText(rs.getString(2))
            }
        }

        insertBtn.setOnClickListener{
            var cv = ContentValues()
            cv.put(AcronymProvider.NAME, editText1.text.toString())
            cv.put(AcronymProvider.MEANING, editText2.text.toString())
            contentResolver.insert(AcronymProvider.CONTENT_URI, cv)
            rs?.requery()
        }

        updateBtn.setOnClickListener{
            val cv = ContentValues()
            cv.put(AcronymProvider.MEANING, editText2.text.toString())
            contentResolver.update(AcronymProvider.CONTENT_URI, cv, "NAME = ?", arrayOf(editText1.toString()) )
            rs?.requery()
        }

        deleteBtn.setOnClickListener{
            contentResolver.delete(AcronymProvider.CONTENT_URI, "NAME = ?", arrayOf(editText1.toString()))
            rs?.requery()
        }



    //
//        var helper = MyHelper(applicationContext)
//        var db = helper.readableDatabase
//        var rs = db.rawQuery("SELECT * FROM ACTABLE", null)
//        if(rs.moveToFirst())
//            Toast.makeText(applicationContext, rs.getString(1) + "\n" + rs.getString(2), Toast.LENGTH_LONG).show()
//



    }



}