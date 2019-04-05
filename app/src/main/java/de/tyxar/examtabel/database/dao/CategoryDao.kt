package de.tyxar.examtabel.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import de.tyxar.examtabel.database.entity.Category

@Dao
interface CategoryDao : BaseDao<Category>{

    @Query("SELECT * FROM category_table WHERE category_id = :category_id")
    fun get(category_id : Int) : LiveData<Category>

    @Query("SELECT * FROM category_table")
    fun getAll() : LiveData<MutableList<Category>>
}