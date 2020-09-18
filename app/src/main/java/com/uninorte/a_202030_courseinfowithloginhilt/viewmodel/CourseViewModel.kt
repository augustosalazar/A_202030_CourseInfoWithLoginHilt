package com.uninorte.a_202030_courseinfowithlogin.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uninorte.a_202030_courseinfowithlogin.model.Course
import com.uninorte.a_202030_courseinfowithlogin.model.User
import com.uninorte.a_202030_courseinfowithlogin.repository.CourseRepository
import com.uninorte.a_202030_courseinfowithlogin.repository.LoginRepository

class CourseViewModel
@ViewModelInject constructor(
    private val repository: CourseRepository
) : ViewModel() {


    fun getCourses(user: String, token: String) = repository.getCourses(user, token)

    fun addCourse(user: String, token: String)  {
        Log.d("MyOut", "CourseViewModel addCourses with token  <" + token+">")
         repository.addCourse(user, token)
    }

    fun getCourseData() = repository.getCourseData()

}
