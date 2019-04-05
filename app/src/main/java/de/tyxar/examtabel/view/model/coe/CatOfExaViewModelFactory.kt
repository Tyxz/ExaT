package de.tyxar.examtabel.view.model.coe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.tyxar.examtabel.repository.CatOfExaRepository
import de.tyxar.examtabel.repository.CategoryRepository

class CatOfExaViewModelFactory(private val repository: CatOfExaRepository) :
    ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatOfExaViewModel(repository) as T
    }
}