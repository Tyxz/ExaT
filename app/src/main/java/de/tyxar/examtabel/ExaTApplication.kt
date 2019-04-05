package de.tyxar.examtabel

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import de.tyxar.examtabel.database.AppDatabase
import de.tyxar.examtabel.repository.CatOfExaRepository
import de.tyxar.examtabel.repository.CategoryRepository
import de.tyxar.examtabel.repository.ExamRepository
import de.tyxar.examtabel.view.model.category.CategoryViewModel
import de.tyxar.examtabel.view.model.category.CategoryViewModelFactory
import de.tyxar.examtabel.view.model.coe.CatOfExaViewModel
import de.tyxar.examtabel.view.model.coe.CatOfExaViewModelFactory
import de.tyxar.examtabel.view.model.exam.ExamViewModel
import de.tyxar.examtabel.view.model.exam.ExamViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class ExaTApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ExaTApplication))

        bind() from singleton { AppDatabase(applicationContext) }
        bind() from singleton { instance<AppDatabase>().categoryDao()}
        bind() from singleton { instance<AppDatabase>().examDao()}
        bind() from singleton { instance<AppDatabase>().categoriesOfExamDao()}
        bind() from singleton { CategoryRepository(instance())}
        bind() from provider { CategoryViewModelFactory(instance()) }
        bind() from singleton { ExamRepository(instance()) }
        bind() from provider { ExamViewModelFactory(instance()) }
        bind() from singleton { CatOfExaRepository(instance(), instance())}
        bind() from provider { CatOfExaViewModelFactory(instance()) }

    }

    init {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

}