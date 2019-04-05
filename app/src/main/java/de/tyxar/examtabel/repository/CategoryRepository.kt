package de.tyxar.examtabel.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import de.tyxar.examtabel.database.dao.CategoryDao
import de.tyxar.examtabel.database.entity.Category

class CategoryRepository(private val categoryDao: CategoryDao)
    : BaseRepository<Category>(categoryDao) {

    @WorkerThread
    suspend fun create(t: Category) = categoryDao.insert(t)

    @WorkerThread
    suspend fun update(t: Category) = categoryDao.update(t)

    @WorkerThread
    suspend fun delete(t: Category) = categoryDao.delete(t)

    fun read(categoryId : Int) : LiveData<Category> = categoryDao.get(categoryId)

    fun readAll() : LiveData<MutableList<Category>> = categoryDao.getAll()
}