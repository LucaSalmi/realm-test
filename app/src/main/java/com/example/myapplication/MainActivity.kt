package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmConfiguration

private lateinit var binder: ActivityMainBinding
private lateinit var chatDAO: ChatDAO


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("chatDB")
            .allowWritesOnUiThread(true)
            .schemaVersion(1)
            .build()
        Realm.setDefaultConfiguration(config)


        chatDAO = ChatDAO()
        chatDAO.getUsers()

        val array = chatDAO.getUsers()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
        binder.messageView.adapter = adapter

        binder.messageView.setOnItemClickListener { adapterView, view, i, l ->

            val intent = Intent(this, MainActivity2::class.java).apply {
                putExtra("data", array[i].userName)
            }
            startActivity(intent)
        }

    }
}