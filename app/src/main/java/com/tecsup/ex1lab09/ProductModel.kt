package com.tecsup.ex1lab09

import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Double,
    @SerializedName("thumbnail") val thumbnail: String
)

data class ProductResponse(
    @SerializedName("products") val products: List<ProductModel>
)