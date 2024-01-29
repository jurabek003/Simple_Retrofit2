package uz.turgunboyevjurabek.simpleretrofit2.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.turgunboyevjurabek.simpleretrofit2.utils.ConstItem

object ApiClient {

    val api:ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(ConstItem.BASE_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}