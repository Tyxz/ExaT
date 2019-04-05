package de.tyxar.examtabel.view.model.exam

import androidx.lifecycle.ViewModel
import de.tyxar.examtabel.database.entity.Exam
import de.tyxar.examtabel.repository.ExamRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ExamViewModel(private val repository: ExamRepository) : ViewModel() {

    private var parentJob = Job()
    private val coroutineContext : CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun create(exam: Exam) = scope.launch {
        repository.create(exam)
    }
    fun read(examId : Int) = repository.read(examId)
    fun readAll() = repository.readAll()
    fun update(exam: Exam) = scope.launch {
        repository.update(exam)
    }
    fun delete(exam: Exam) = scope.launch {
        repository.delete(exam)
    }
    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}