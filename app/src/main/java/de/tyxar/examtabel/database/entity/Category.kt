package de.tyxar.examtabel.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val category_id : Int = -1,
    val category_name : String = "",
    var category_credits : Int = -1
)