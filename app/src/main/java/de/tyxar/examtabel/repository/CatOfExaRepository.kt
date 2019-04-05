package de.tyxar.examtabel.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import de.tyxar.examtabel.database.dao.CategoriesOfExamDao
import de.tyxar.examtabel.database.entity.CategoriesOfExam
import de.tyxar.examtabel.database.entity.Category
import de.tyxar.examtabel.database.entity.Exam

class CatOfExaRepository(private val categoriesOfExamDao: CategoriesOfExamDao) {
    @WorkerThread
    suspend fun create(categoriesOfExam: CategoriesOfExam) = categoriesOfExamDao.insert(categoriesOfExam)

    fun read(categoryId: Int, examId : Int) : LiveData<CategoriesOfExam> = categoriesOfExamDao.get(categoryId, examId)

    fun readAll() : LiveData<MutableList<CategoriesOfExam>> = categoriesOfExamDao.getAll()

    fun readFromExam(examId: Int) : LiveData<MutableList<Category>> = categoriesOfExamDao.getCategoriesFromExam(examId)

    fun readFromCategory(categoryId : Int) : LiveData<MutableList<Exam>> = categoriesOfExamDao.getExamsFromCategory(categoryId)

    @WorkerThread
    suspend fun update(categoriesOfExam: CategoriesOfExam) = categoriesOfExamDao.update(categoriesOfExam)

    @WorkerThread
    suspend fun delete(categoriesOfExam: CategoriesOfExam) = categoriesOfExamDao.delete(categoriesOfExam)
}