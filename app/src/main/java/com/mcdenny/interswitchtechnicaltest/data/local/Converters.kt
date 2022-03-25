package com.mcdenny.interswitchtechnicaltest.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mcdenny.interswitchtechnicaltest.domain.model.FeeGroup
import com.mcdenny.interswitchtechnicaltest.domain.model.PayConfiguration

class Converters {

    @TypeConverter
    fun fromList(value: List<String>) = Gson().toJson(value)

    @TypeConverter
    fun toList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun fromFeeGroup(value: List<FeeGroup>): String = Gson().toJson(value)

    @TypeConverter
    fun toFeeGroup(value: String) = Gson().fromJson(value, Array<FeeGroup>::class.java).toList()

    @TypeConverter
    fun fromConfiguration(value: List<PayConfiguration>): String = Gson().toJson(value)

    @TypeConverter
    fun toConfiguration(value: String) =
        Gson().fromJson(value, Array<PayConfiguration>::class.java).toList()
//
//    @TypeConverter
//    fun fromResponse(value: Response): String = Gson().toJson(value)
//
//    @TypeConverter
//    fun toResponse(value: String): Response = Gson().fromJson(value, Response::class.java)

}