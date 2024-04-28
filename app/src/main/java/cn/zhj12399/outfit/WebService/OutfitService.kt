package cn.zhj12399.outfit.WebService

import cn.zhj12399.outfit.Entity.OutfitName
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface OutfitService {

    @POST("outfit/add_outfit")
    fun addOutfit(@Body outfit: OutfitName): Call<ResponseBody>

    @POST("outfit/get_last_outfit")
    fun getLastOutfit(@Query("name") username: String): Call<ResponseBody>

    @POST("outfit/get_all_outfit")
    fun getAllOutfit(@Query("name") username: String): Call<ResponseBody>
}