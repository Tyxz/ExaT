package de.tyxar.examtabel.database.util.converter

import androidx.room.TypeConverter
import java.util.*

object DateTypeConverter {
    @TypeConverter
    @JvmStatic
    fun toDate(timestamp : Long?) : Date? {
        return timestamp?.let {time ->
            Date(time)
        }
    }
    @TypeConverter
    @JvmStatic
    fun fromDate(date : Date?) : Long? {
        return date?.time
    }
}