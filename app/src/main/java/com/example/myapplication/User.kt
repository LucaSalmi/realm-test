package com.example.myapplication

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class User : RealmObject(){

    @PrimaryKey
    var id = UUID.randomUUID().toString()

    @Required
    var userName: String = "Friendo"
}