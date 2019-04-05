package de.tyxar.examtabel.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import de.tyxar.examtabel.database.dao.ExamDao
import de.tyxar.examtabel.database.entity.Exam

class ExamRepository(private val examDao: ExamDao) {
    @WorkerThread
    suspend fun create(exam: Exam) = examDao.insert(exam)

    fun read(examId : Int) : LiveData<Exam> = examDao.get(examId)

    fun readAll() : LiveData<MutableList<Exam>> = examDao.getAll()

    @WorkerThread
    suspend fun update(exam: Exam) = examDao.update(exam)

    @WorkerThread
    suspend fun delete(exam: Exam) = examDao.delete(exam)
}