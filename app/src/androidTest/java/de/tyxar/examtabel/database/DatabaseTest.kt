package de.tyxar.examtabel.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import de.tyxar.examtabel.database.dao.*
import de.tyxar.examtabel.database.entity.*
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var catDao : CategoryDao
    private lateinit var exaDao : ExamDao
    private lateinit var catOfExaDao : CategoriesOfExamDao

    private lateinit var db : AppDatabase

    @Before
    fun createDB() {
        db = AppDatabase(ApplicationProvider.getApplicationContext())
        catDao = db.categoryDao()
        exaDao = db.examDao()
        catOfExaDao = db.categoriesOfExamDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}