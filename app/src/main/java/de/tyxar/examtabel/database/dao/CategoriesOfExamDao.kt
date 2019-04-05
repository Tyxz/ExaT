package de.tyxar.examtabel.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import de.tyxar.examtabel.database.entity.CategoriesOfExam
import de.tyxar.examtabel.database.entity.Category
import de.tyxar.examtabel.database.entity.Exam

@Dao
interface CategoriesOfExamDao {

    @Insert
    fun insert(category: CategoriesOfExam)

    @Query("SELECT * FROM cat_of_ex_table WHERE category_id = :category_id AND exam_id = :exam_id")
    fun get(category_id: Int, exam_id: Int) : LiveData<CategoriesOfExam>

    @Query("SELECT * FROM cat_of_ex_table")
    fun getAll() : LiveData<MutableList<CategoriesOfExam>>

    @Query("SELECT cat.* FROM cat_of_ex_table coe INNER JOIN category_table cat ON cat.category_id = coe.category_id INNER JOIN exam_table exa ON exa.exam_id = coe.exam_id WHERE coe.exam_id = :exam_id")
    fun getCategoriesFromExam(exam_id : Int) : LiveData<MutableList<Category>>

    @Query("SELECT exa.* FROM cat_of_ex_table coe INNER JOIN category_table cat ON cat.category_id = coe.category_id INNER JOIN exam_table exa ON exa.exam_id = coe.exam_id WHERE coe.exam_id = :category_id")
    fun getExamsFromCategory(category_id : Int) : LiveData<MutableList<Exam>>

    @Update
    fun update(category: CategoriesOfExam)

    @Delete
    fun delete(category: CategoriesOfExam)
}