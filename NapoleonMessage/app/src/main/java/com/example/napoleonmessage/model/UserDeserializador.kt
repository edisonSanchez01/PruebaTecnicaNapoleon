package com.example.napoleonmessage.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

const val USER_ID = "id"
const val USER_NAME = "name"
const val USER_NICK_NAME = "username"
const val USER_EMAIL = "email"
const val USER_ADDRESS = "address"
const val STREET_ADDRESS = "street"
const val SUITE_ADDRESS = "suite"
const val CITY_ADDRESS = "city"
const val ZIP_CODE_ADDRESS = "zipcode"
const val USER_PHONE = "phone"
const val USER_WEBSITE = "website"
const val COMPANY_NAME = "name"
const val USER_COMPANY = "company"
const val COMPANY_PHRASE = "catchPhrase"
const val COMPANY_BS = "bs"

class UserDeserializador : JsonDeserializer<User> {


    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): User {
        val objectUser: JsonObject? = json?.asJsonObject
        var id: Int? = objectUser?.get(USER_ID)?.asInt
        var name: String? = objectUser?.get(USER_NAME)?.asString
        var userName: String? = objectUser?.get(USER_NICK_NAME)?.asString
        var email: String? = objectUser?.get(USER_EMAIL)?.asString
        var objectAddress: JsonObject? = objectUser?.getAsJsonObject(USER_ADDRESS)
        var street: String? = objectAddress?.get(STREET_ADDRESS)?.asString
        var suite: String? = objectAddress?.get(SUITE_ADDRESS)?.asString
        var city: String? = objectAddress?.get(CITY_ADDRESS)?.asString
        var zipCode: String? = objectAddress?.get(ZIP_CODE_ADDRESS)?.asString
        var address = Address(street, suite, city, zipCode)
        var phone: String? = objectUser?.get(USER_PHONE)?.asString
        var website: String? = objectUser?.get(USER_WEBSITE)?.asString
        var objectCompany: JsonObject? = objectUser?.getAsJsonObject(USER_COMPANY)
        var nameCompany: String? = objectCompany?.get(COMPANY_NAME)?.asString
        var phraseCompany: String? = objectCompany?.get(COMPANY_PHRASE)?.asString
        var bsCompany: String? = objectCompany?.get(COMPANY_BS)?.asString
        var company = Company(nameCompany, phraseCompany, bsCompany)
        return User(id,name,userName,email,phone,address,website,company)
    }

}