package com.example.napoleonmessage.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.napoleonmessage.model.EndPointsApi
import com.example.napoleonmessage.model.URL_API
import com.example.napoleonmessage.model.User
import com.example.napoleonmessage.model.UserDeserializador
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ViewModelDetailsActivity : ViewModel() {

    private val _detailsPost = MutableLiveData<User>()
    val detailsPost: LiveData<User> get() = _detailsPost

    fun getDetailsUser(idUser: Int){
        viewModelScope.launch {
            var gson = GsonBuilder()
            gson.registerTypeAdapter(UserDeserializador::class.java, UserDeserializador())
            var retrofit = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create(gson.create()))
                .build()
            var service = retrofit.create(EndPointsApi::class.java)
            service.getInfoUser(idUser).enqueue(object: Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    _detailsPost.value = response.body()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {

                }

            })
        }
    }




}