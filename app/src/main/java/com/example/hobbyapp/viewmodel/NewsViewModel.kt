package com.example.hobbyapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hobbyapp.global.Global
import com.example.hobbyapp.model.News
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NewsViewModel(application: Application):AndroidViewModel(application) {
    val newsLD = MutableLiveData<News>()
    val listNewsLD = MutableLiveData<List<News>>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun refresh(){
        queue = Volley.newRequestQueue(getApplication())
        val url = "${Global.baseUrl}/getallnews.php"
        loadingLD.value = true
        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                val sType = object : TypeToken<List<News>>(){}.type
                val result = Gson().fromJson<List<News>>(it,sType)
                listNewsLD.value = result as ArrayList<News>?
                loadingLD.value = false
                Log.d("news", it.toString())
            },{
                loadingLD.value = false
                Log.d("students", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun getNews(id:Int){
        queue = Volley.newRequestQueue(getApplication())
        val url = "${Global.baseUrl}/getnews.php?id=$id"

        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                Log.d("news", it.toString())
                val result = Gson().fromJson(it, News::class.java)
                newsLD.value = result
            },{
                Log.d("news", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}