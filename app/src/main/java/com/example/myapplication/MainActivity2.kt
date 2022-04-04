package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.myapplication.databinding.ActivityMain2Binding
import io.realm.Realm
import io.realm.RealmChangeListener

private lateinit var binder : ActivityMain2Binding
private lateinit var dao : ChatDAO
private lateinit var realmListener: RealmChangeListener<Realm>
private lateinit var reciever: String

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binder.root)

        reciever = intent.getStringExtra("data").toString()
        dao = ChatDAO()
        update()
        realmListener = RealmChangeListener {

            update()

        }
        dao.db.addChangeListener(realmListener)

        binder.sendBtn.setOnClickListener {

            val input = binder.inputMessage.text.toString()

            if (input != "") {

                dao.addChat(reciever, input)
            }

        }
    }

    fun update(){

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dao.getMessage(
            reciever))
        binder.chatList.adapter = adapter
    }
}