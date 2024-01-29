package uz.turgunboyevjurabek.simpleretrofit2.network

import okhttp3.Response
import retrofit2.http.GET
import uz.turgunboyevjurabek.simpleretrofit2.madels.CatsFacts
import uz.turgunboyevjurabek.simpleretrofit2.madels.Data

interface ApiService {
    @GET("facts/")
    suspend fun getAllItem():CatsFacts


}