package de.tyxar.examtabel.repository

import androidx.annotation.WorkerThread
import de.tyxar.examtabel.database.dao.BaseDao
import de.tyxar.examtabel.database.entity.Category

abstract class BaseRepository<T>(private val dao : BaseDao<T>) {
}