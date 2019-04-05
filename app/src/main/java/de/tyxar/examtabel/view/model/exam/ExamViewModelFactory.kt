package de.tyxar.examtabel.view.model.exam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.tyxar.examtabel.repository.ExamRepository

class ExamViewModelFactory(private val repository: ExamRepository) :
    ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExamViewModel(repository) as T
    }
}