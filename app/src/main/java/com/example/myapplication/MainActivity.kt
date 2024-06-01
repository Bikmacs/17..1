package com.example.myapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var log:EditText
    lateinit var pass:EditText
    lateinit var pref:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log = findViewById(R.id.log)
        pass = findViewById(R.id.pass)
        findViewById<View>(R.id.load).setOnClickListener{
            AlertDialog.Builder(this)
                .setMessage("Восстановить данные?")
                .setPositiveButton("Да") {dialog, which ->
                    pref = getPreferences(MODE_PRIVATE)
                    log.setText(pref.getString("login",""))
                    pass.setText(pref.getString("password",""))
                }
                .setNegativeButton("Нет") {dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }
    }
    fun handler(view: View) {
        if (view.getId() == R.id.save){
            pref = getPreferences(MODE_PRIVATE)
            var ed = pref.edit()
            ed.putString("login",log.getText().toString())
            ed.putString("password",pass.getText().toString())
            ed.apply()
        }
    }

}