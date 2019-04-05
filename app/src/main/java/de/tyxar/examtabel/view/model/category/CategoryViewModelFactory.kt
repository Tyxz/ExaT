package de.tyxar.examtabel.view.model.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.tyxar.examtabel.repository.CategoryRepository

class CategoryViewModelFactory(private val repository: CategoryRepository) :
    ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(repository) as T
    }
}