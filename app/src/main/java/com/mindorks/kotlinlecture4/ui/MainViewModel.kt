package com.mindorks.kotlinlecture4.ui

import android.content.Context
import com.mindorks.kotlinlecture4.mapper.StudentMapper
import com.mindorks.kotlinlecture4.model.Student
import com.mindorks.kotlinlecture4.services.NetworkService
import com.mindorks.kotlinlecture4.util.NetworkUtils

class MainViewModel(val networkService: NetworkService) {

    fun getTopperStudent(context: Context): Student? {

        val isInternetConnected = NetworkUtils.isInternetConnected(context)

        return if (isInternetConnected) {
            val apiStudent = networkService.getTopperStudent()
            StudentMapper.map(apiStudent)
        } else {
             null
        }
    }

    fun getListOfStudent(context: Context): List<Student> {

        val isInternetConnected = NetworkUtils.isInternetConnected(context)
        val studentList: MutableList<Student> = mutableListOf()

        if (isInternetConnected) {

            val listApiStudent = networkService.getListOfStudent()

            for (apiStudent in listApiStudent) {

                val student = StudentMapper
                    .map(apiStudent)

                studentList.add(student)
            }

        }

        return studentList
    }

    fun getStudentGrade(averageMark: Int): String {
        val grade: String = when (averageMark) {
            in 95..99 -> "A"
            100 -> "A+"
            in 90..94 -> "B"
            else -> "C"
        }
        
        return grade
    }

}