package de.tyxar.examtabel.view.model.coe

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

class CatOfExaViewModel(private val repository: CatOfExaRepository) : ViewModel() {

    private var parentJob = Job()
    private val coroutineContext : CoroutineContext
    get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun create(catOfExa: CategoriesOfExam) = scope.launch {
        repository.create(catOfExa)
    }
    fun read(categoryId : Int, examId : Int) = repository.read(categoryId, examId)
    fun readAll() = repository.readAll()
    fun readFromExam(examId: Int) = repository.readFromExam(examId)
    fun readFromCategory(categoryId: Int) = repository.readFromCategory(categoryId)

    fun update(catOfExa: CategoriesOfExam) = scope.launch {
        repository.update(catOfExa)
    }
    fun delete(catOfExa: CategoriesOfExam) = scope.launch {
        repository.delete(catOfExa)
    }
    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}