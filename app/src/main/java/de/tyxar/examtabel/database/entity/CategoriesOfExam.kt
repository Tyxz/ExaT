package de.tyxar.examtabel.database.entity

import androidx.room.*

@Entity(tableName = "cat_of_ex_table",
    foreignKeys =  [
        ForeignKey(
            entity = Exam::class,
            parentColumns = ["exam_id"],
            childColumns = ["exam_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["category_id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["exam_id", "category_id"],
    indices = [Index(value =  ["exam_id", "category_id"], unique = true)])
data class CategoriesOfExam (
    val exam_id : Int = -1,
    val category_id : Int = -1,
    var isActive : Boolean = false
)

data class ExamWithCategory(
    val exam : Exam,
    val category : Category
)

data class ExamWithAllCategories (
    val exam: Exam,
    val categories : List<Category>
)

data class CategoryWithAllExams (
    val category: Category,
    val exams : List<Exam>
)