package de.tyxar.examtabel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.tyxar.examtabel.database.dao.CategoriesOfExamDao
import de.tyxar.examtabel.database.dao.CategoryDao
import de.tyxar.examtabel.database.dao.ExamDao
import de.tyxar.examtabel.database.entity.CategoriesOfExam
import de.tyxar.examtabel.database.entity.Category
import de.tyxar.examtabel.database.entity.Exam
import de.tyxar.examtabel.database.util.converter.DateTypeConverter
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Category::class, Exam::class, CategoriesOfExam::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun examDao(): ExamDao
    abstract fun categoriesOfExamDao(): CategoriesOfExamDao

    companion object {
        @Volatile
        private var instance : AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "examtable.db"
            ).build()
    }
}