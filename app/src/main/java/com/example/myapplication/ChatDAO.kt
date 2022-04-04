package com.example.myapplication

import io.realm.Realm
import java.util.ArrayList

class ChatDAO {

    val db = Realm.getDefaultInstance()

    fun addChat(reciever: String, sentMessage: String){

        db.executeTransactionAsync {
            val chat = chat().apply {

                this.reciever = reciever
                message = sentMessage

            }
            it.insert(chat)
        }

    }

    fun addUser(userName: String){

        db.executeTransactionAsync{

            val user = User().apply {

                this.userName = userName
            }

            it.insert(user)

        }

    }

    fun getMessage(reciever: String): ArrayList<String> {
        var obj = ArrayList<chat>()
        obj.addAll(db.where(chat::class.java).equalTo("reciever", reciever).findAllAsync())
        var texts = ArrayList<String>()
        for (x in obj){

            texts.add(x.message)

        }

        return texts
    }

    fun getUsers(): ArrayList<User> {

        var obj = ArrayList<User>()
        obj.addAll(db.where(User::class.java).findAllAsync())

        return obj

    }
}