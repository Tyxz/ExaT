package de.tyxar.examtabel.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import de.tyxar.examtabel.R
import de.tyxar.examtabel.view.adapter.MainRecycleViewAdapter
import de.tyxar.examtabel.view.model.category.CategoryViewModel
import de.tyxar.examtabel.view.model.category.CategoryViewModelFactory
import de.tyxar.examtabel.view.model.coe.CatOfExaViewModel
import de.tyxar.examtabel.view.model.coe.CatOfExaViewModelFactory
import de.tyxar.examtabel.view.model.exam.ExamViewModel
import de.tyxar.examtabel.view.model.exam.ExamViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_start.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class StartFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val catFactory : CategoryViewModelFactory by instance()
    private val examFactory : ExamViewModelFactory by instance()
    private val coeFactory : CatOfExaViewModelFactory by instance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            it.fab.show()
            it.fab2.show()
        }
        val catViewModel = ViewModelProviders.of(this, catFactory).get(CategoryViewModel::class.java)
        val exaViewModel = ViewModelProviders.of(this, examFactory).get(ExamViewModel::class.java)
        val coeViewModel = ViewModelProviders.of(this, coeFactory).get(CatOfExaViewModel::class.java)
        recycle_view_main.layoutManager = LinearLayoutManager(context!!)
        recycle_view_main.setHasFixedSize(true)
        val adapter = MainRecycleViewAdapter(context!!, coeViewModel, viewLifecycleOwner)
        recycle_view_main.adapter = adapter
        catViewModel.readAll().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

    }


}
