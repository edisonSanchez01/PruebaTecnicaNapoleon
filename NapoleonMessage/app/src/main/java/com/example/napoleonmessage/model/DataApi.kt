package com.example.napoleonmessage.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val URL_API = "https://jsonplaceholder.typicode.com"
const val GET_POSTS = "/posts"
const val INFO_USER = "/users/{id}"

interface EndPointsApi{

    @GET(GET_POSTS)
    fun posts(): Call<List<Post>>

    @GET(INFO_USER)
    fun getInfoUser(@Path("id") id: Int): Call<User>

}