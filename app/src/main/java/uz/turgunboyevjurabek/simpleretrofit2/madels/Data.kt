package uz.turgunboyevjurabek.simpleretrofit2.madels


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("fact")
    val fact: String,
    @SerializedName("length")
    val length: Int
)