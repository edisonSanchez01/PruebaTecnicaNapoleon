package com.example.napoleonmessage.ViewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.napoleonmessage.model.EndPointsApi
import com.example.napoleonmessage.model.Post
import com.example.napoleonmessage.model.URL_API
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModelMainActivity : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    fun getPosts(){
        viewModelScope.launch {
            var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            var service: EndPointsApi = retrofit.create(EndPointsApi::class.java)
            service.posts().enqueue(object : Callback<List<Post>>{
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    _posts.value = response.body()
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    Log.i("Error in Post", "service post has failed")
                }

            })
        }
    }

}