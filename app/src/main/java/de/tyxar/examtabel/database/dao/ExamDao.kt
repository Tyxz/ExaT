package de.tyxar.examtabel.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import de.tyxar.examtabel.database.entity.Exam

@Dao
interface ExamDao : BaseDao<Exam> {

    @Query("SELECT * FROM exam_table WHERE exam_id = :exam_id")
    fun get(exam_id : Int) : LiveData<Exam>

    @Query("SELECT * FROM exam_table")
    fun getAll() : LiveData<MutableList<Exam>>
}