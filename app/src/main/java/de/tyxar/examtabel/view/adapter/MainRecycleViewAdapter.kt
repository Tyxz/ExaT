package de.tyxar.examtabel.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.tyxar.examtabel.database.entity.Category
import de.tyxar.examtabel.view.model.coe.CatOfExaViewModel

class MainRecycleViewAdapter(private val viewModel: CatOfExaViewModel) : ListAdapter<Category, MainRecycleViewAdapter.CategoryHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.category_id == newItem.category_id
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.category_name == newItem.category_name
                        && oldItem.category_credits == newItem.category_credits
            }
        }
    }

    class CategoryHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }
}