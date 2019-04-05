package de.tyxar.examtabel

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class ExaTApplication : Application(), KodeinAware {
    override val kodein by Kodein.lazy {
    }

}