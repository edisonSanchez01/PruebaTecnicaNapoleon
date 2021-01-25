package com.example.napoleonmessage.model

class Post(var userId: Int?, var id: Int?, var title: String?, var body: String?)

class Address(var street: String?, var suite: String?, var city: String?, var zipcode: String?)

class Company(var name: String?, var catchPhrase: String?, var bs: String?)

class User(var id: Int?, var name: String?, var userName: String?, var email: String?, var phone: String?,
           var address: Address?, var website: String?, var company: Company?)