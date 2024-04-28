package cn.zhj12399.outfit.WebService

import cn.zhj12399.outfit.Entity.People
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PeopleService {
    @POST("people/add_people")
    fun addPeople(@Body register_info: People): Call<ResponseBody>

    @POST("people/judge_password")
    fun judgePassword(@Body login_info: People): Call<ResponseBody>
}