package de.tyxar.examtabel.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import de.tyxar.examtabel.database.entity.CategoriesOfExam
import de.tyxar.examtabel.database.entity.Category
import de.tyxar.examtabel.database.entity.Exam

@Dao
abstract class CategoriesOfExamDao : BaseDao<CategoriesOfExam> {

    @Query("SELECT * FROM cat_of_ex_table WHERE category_id = :category_id AND exam_id = :exam_id")
    abstract fun get(category_id: Int, exam_id: Int) : LiveData<CategoriesOfExam>

    @Query("SELECT * FROM cat_of_ex_table")
    abstract fun getAll() : LiveData<MutableList<CategoriesOfExam>>

    @Query("SELECT cat.* FROM cat_of_ex_table coe INNER JOIN category_table cat ON cat.category_id = coe.category_id INNER JOIN exam_table exa ON exa.exam_id = coe.exam_id WHERE coe.exam_id = :exam_id")
    abstract fun getCategoriesFromExam(exam_id : Int) : LiveData<MutableList<Category>>

    @Query("SELECT exa.* FROM cat_of_ex_table coe INNER JOIN category_table cat ON cat.category_id = coe.category_id INNER JOIN exam_table exa ON exa.exam_id = coe.exam_id WHERE coe.exam_id = :category_id")
    abstract fun getExamsFromCategory(category_id : Int) : LiveData<MutableList<Exam>>

    @Query("UPDATE cat_of_ex_table SET isActive = 1 WHERE exam_id = :exam_id AND category_id = :category_id")
    abstract fun setActiveCategory(category_id: Int, exam_id: Int)

    @Query("UPDATE cat_of_ex_table SET isActive = 0 WHERE exam_id = :exam_id")
    abstract fun unsetActiveCategory(exam_id: Int)

    @Query("UPDATE cat_of_ex_table SET isActive = 0 WHERE exam_id = :exam_id")
    @Transaction
    fun changeActiveCategory(category_id: Int, exam_id: Int) {
        setActiveCategory(category_id, exam_id)
    }

}