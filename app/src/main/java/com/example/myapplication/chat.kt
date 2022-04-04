package com.example.myapplication

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class chat : RealmObject(){

    @PrimaryKey
    var id = UUID.randomUUID().toString()

    @Required
    var sender: String = ""

    @Required
    var message: String = ""

    var reciever: String = "Friendo"

}