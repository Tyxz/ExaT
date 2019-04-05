package de.tyxar.examtabel.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import de.tyxar.examtabel.database.dao.CategoryDao
import de.tyxar.examtabel.database.entity.Category

class CategoryRepository(private val categoryDao: CategoryDao) {
    @WorkerThread
    suspend fun create(category: Category) = categoryDao.insert(category)

    fun read(categoryId : Int) : LiveData<Category> = categoryDao.get(categoryId)

    fun readAll() : LiveData<MutableList<Category>> = categoryDao.getAll()

    @WorkerThread
    suspend fun update(category: Category) = categoryDao.update(category)

    @WorkerThread
    suspend fun delete(category: Category) = categoryDao.delete(category)
}