package de.tyxar.examtabel.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "exam_table",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["category_id"],
            childColumns = ["active_category_id"],
            onDelete = ForeignKey.SET_DEFAULT
        )
    ]
)
data class Exam (
    @PrimaryKey(autoGenerate = true)
    val exam_id : Int = 0,
    var exam_name : String = "",
    var exam_credits : Int = -1,
    var exam_grade : Float = -1f,
    var exam_date : Date? = null,
    var active_category_id : Int = -1
)