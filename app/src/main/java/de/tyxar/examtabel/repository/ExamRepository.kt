package de.tyxar.examtabel.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import de.tyxar.examtabel.database.dao.ExamDao
import de.tyxar.examtabel.database.entity.Exam

class ExamRepository(private val examDao: ExamDao)
    : BaseRepository<Exam>(examDao) {

    fun read(examId : Int) : LiveData<Exam> = examDao.get(examId)

    fun readAll() : LiveData<MutableList<Exam>> = examDao.getAll()


    @WorkerThread
    suspend fun create(t: Exam) = examDao.insert(t)

    @WorkerThread
    suspend fun update(t: Exam) = examDao.update(t)

    @WorkerThread
    suspend fun delete(t: Exam) = examDao.delete(t)
}