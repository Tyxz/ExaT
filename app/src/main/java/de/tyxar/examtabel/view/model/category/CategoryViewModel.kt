package de.tyxar.examtabel.view.model.category

import androidx.lifecycle.ViewModel
import de.tyxar.examtabel.database.entity.CategoriesOfExam
import de.tyxar.examtabel.database.entity.Category
import de.tyxar.examtabel.repository.CatOfExaRepository
import de.tyxar.examtabel.repository.CategoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {

    private var parentJob = Job()
    private val coroutineContext : CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun create(Category: Category) = scope.launch {
        repository.create(Category)
    }
    fun read(CategoryId : Int) = repository.read(CategoryId)
    fun readAll() = repository.readAll()
    fun update(Category: Category) = scope.launch {
        repository.update(Category)
    }
    fun delete(Category: Category) = scope.launch {
        repository.delete(Category)
    }
    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}