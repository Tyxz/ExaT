package de.tyxar.examtabel.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import de.tyxar.examtabel.database.dao.CategoriesOfExamDao
import de.tyxar.examtabel.database.dao.ExamDao
import de.tyxar.examtabel.database.entity.CategoriesOfExam
import de.tyxar.examtabel.database.entity.Category
import de.tyxar.examtabel.database.entity.Exam

class CatOfExaRepository(private val categoriesOfExamDao: CategoriesOfExamDao, private  val examDao: ExamDao)
    : BaseRepository<CategoriesOfExam>(categoriesOfExamDao) {

    fun read(categoryId: Int, examId : Int) : LiveData<CategoriesOfExam> = categoriesOfExamDao.get(categoryId, examId)

    fun readAll() : LiveData<MutableList<CategoriesOfExam>> = categoriesOfExamDao.getAll()

    fun readFromExam(examId: Int) : LiveData<MutableList<Category>> = categoriesOfExamDao.getCategoriesFromExam(examId)

    fun readFromCategory(categoryId : Int) : LiveData<MutableList<Exam>> = categoriesOfExamDao.getExamsFromCategory(categoryId)

    @WorkerThread
    suspend fun updateActiveCategory(categoryId: Int, exam : Exam) {
        categoriesOfExamDao.changeActiveCategory(categoryId, exam.exam_id)
        examDao.update(exam)
    }

    @WorkerThread
    suspend fun create(t: CategoriesOfExam) = categoriesOfExamDao.insert(t)

    @WorkerThread
    suspend fun update(t: CategoriesOfExam) = categoriesOfExamDao.update(t)

    @WorkerThread
    suspend fun delete(t: CategoriesOfExam) = categoriesOfExamDao.delete(t)
}