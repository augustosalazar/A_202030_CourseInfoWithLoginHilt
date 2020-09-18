package com.uninorte.a_202030_courseinfowithlogin.service.api.course

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.uninorte.a_202030_courseinfowithlogin.model.Course
import com.uninorte.a_202030_courseinfowithlogin.service.api.login.LoginApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class CourseApiService @Inject constructor(
    private val engine: CourseApi
){

    //companion object{

        val theResponse = MutableLiveData<List<Course>>()
        var courses = mutableListOf<Course>()
    //}

    fun getCourseData() = theResponse


     fun getCourses(user: String, token: String){

        //Log.d("MyOut", "getCourses with token  <" + token+">")
        val auth = "Bearer "+token
         engine.getCourses(user,auth).enqueue(object: Callback<List<Course>>{
            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        //theResponse.value = response.body()
                        //theResponse.postValue(response.body())
                        courses.clear()
                        val t = response.body() as List<Course>
                        courses.addAll(t)
                        theResponse.postValue(courses)
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })

    }

    fun addCourse(user: String, token: String) {

        Log.d("MyOut", "addCourse with token  <" + token+">")
        val auth = "Bearer "+token
        engine.addCourse(user,auth).enqueue(object: Callback<Course>{
            override fun onResponse(call: Call<Course>, response: Response<Course>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        //Log.d("MyOut", "OK isSuccessful token " )
                        courses.add(response.body()!!)
                        theResponse.postValue(courses)
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<Course>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }

}