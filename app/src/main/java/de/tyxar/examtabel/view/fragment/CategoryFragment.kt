package de.tyxar.examtabel.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

import de.tyxar.examtabel.R
import de.tyxar.examtabel.database.entity.Category
import de.tyxar.examtabel.view.model.category.CategoryViewModel
import de.tyxar.examtabel.view.model.category.CategoryViewModelFactory
import de.tyxar.examtabel.view.model.coe.CatOfExaViewModel
import de.tyxar.examtabel.view.model.coe.CatOfExaViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_category.*
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
class CategoryFragment : Fragment(), KodeinAware {
    override val kodein by kodein()

    private val catFactory : CategoryViewModelFactory by instance()

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
        val viewModel = ViewModelProviders.of(this, catFactory).get(CategoryViewModel::class.java)
        submit.setOnClickListener {
            viewModel.create(Category(
                category_name = input_text_name.text.toString(),
                category_credits = Integer.parseInt(input_text_credits.text.toString())
            ))
            findNavController().navigate(R.id.navigation_start)
        }
    }


}
