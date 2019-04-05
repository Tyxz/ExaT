package de.tyxar.examtabel.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

import de.tyxar.examtabel.R
import de.tyxar.examtabel.database.entity.CategoriesOfExam
import de.tyxar.examtabel.database.entity.Category
import de.tyxar.examtabel.database.entity.Exam
import de.tyxar.examtabel.view.model.category.CategoryViewModel
import de.tyxar.examtabel.view.model.category.CategoryViewModelFactory
import de.tyxar.examtabel.view.model.coe.CatOfExaViewModel
import de.tyxar.examtabel.view.model.coe.CatOfExaViewModelFactory
import de.tyxar.examtabel.view.model.exam.ExamViewModel
import de.tyxar.examtabel.view.model.exam.ExamViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_exam.*
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
class ExamFragment : Fragment(), KodeinAware {
    override val kodein by kodein()

    private val catFactory : CategoryViewModelFactory by instance()
    private val examFactory : ExamViewModelFactory by instance()
    private val coeFactory : CatOfExaViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_category, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            it.fab.hide()
            it.fab2.hide()
        }
        val catViewModel = ViewModelProviders.of(this, catFactory).get(CategoryViewModel::class.java)
        val exaViewModel = ViewModelProviders.of(this, examFactory).get(ExamViewModel::class.java)
        val coeViewModel = ViewModelProviders.of(this, coeFactory).get(CatOfExaViewModel::class.java)
        var pos  = 0
        catViewModel.readAll().observe(viewLifecycleOwner, Observer {

            val categorieNames = Array<String>(it.size) { i -> it[i].category_name}
            val adapter = ArrayAdapter<String>(context!!, R.layout.support_simple_spinner_dropdown_item, categorieNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.setOnItemClickListener { parent, view, position, id ->
                pos = position
            }
        })
        submit.setOnClickListener {
            val exam = Exam(
                exam_name = input_text_name.text.toString(),
                exam_credits = Integer.parseInt(input_text_credits.text.toString()),
                active_category_id = pos
            )
            exaViewModel.create(exam)
            coeViewModel.create(CategoriesOfExam(
                exam_id = exam.exam_id,
                category_id = pos,
                isActive = true
            ))
            findNavController().navigate(R.id.navigation_start)
        }
    }


}
