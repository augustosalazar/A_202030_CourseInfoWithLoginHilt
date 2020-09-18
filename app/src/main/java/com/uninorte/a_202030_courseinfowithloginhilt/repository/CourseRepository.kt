package com.uninorte.a_202030_courseinfowithlogin.repository

import com.uninorte.a_202030_courseinfowithlogin.service.api.course.CourseApiService
import com.uninorte.a_202030_courseinfowithlogin.service.api.login.LoginApiService
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val service: CourseApiService
) {

    fun getCourses(user: String, token: String) = service.getCourses(user,token)

    fun addCourse(user: String, token: String) = service.addCourse(user,token)

    fun getCourseData() = service.getCourseData()
}