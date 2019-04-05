package de.tyxar.examtabel.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.tyxar.examtabel.R
import de.tyxar.examtabel.database.entity.Category
import de.tyxar.examtabel.view.model.coe.CatOfExaViewModel
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.android.synthetic.main.recycle_view_category_item.view.*

class MainRecycleViewAdapter(private val context : Context, private val viewModel: CatOfExaViewModel, private val owner : LifecycleOwner) : ListAdapter<Category, MainRecycleViewAdapter.CategoryHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_category_item, parent, false)
        return CategoryHolder(itemView)
    }

    private var expandedItemHolder : CategoryHolder? = null

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            textCategory.text = item.category_name
            textCredits.text = "0/${item.category_credits}"
            buttonExpand.setOnClickListener {
                if(expandedItemHolder == holder) {
                    hideItemExpansion(holder)
                } else {
                    swapItemExpansion(holder)
                }

            }
            examView.layoutManager = LinearLayoutManager(context!!)
            examView.setHasFixedSize(true)
            val adapter = ExamRecycleViewAdapter(context, item.category_id, viewModel)
            examView.adapter = adapter
            adapter.submitList(viewModel.readFromCategory(item.category_id).value)


        }
    }

    fun swapItemExpansion(showHolder : CategoryHolder) {
        showHolder.apply {
            buttonExpand.setIconResource(R.drawable.ic_reduce_24dp)
            // TODO show expand layout
        }
        expandedItemHolder?.let { hideItemExpansion(it) }
        expandedItemHolder = showHolder
    }

    private fun hideItemExpansion(hideHolder: CategoryHolder) {
        hideHolder.apply {
            // TODO: Hide expand layout
            buttonExpand.setIconResource(R.drawable.ic_expand_24dp)
        }
        /*
        if(hideHolder.editButtonLayout.visibility == View.VISIBLE)
            toggleEditableTextView(hideHolder, false)
            */
        expandedItemHolder = null
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
        val textCategory = itemView.text_main_item_category
        val textCredits = itemView.text_main_item_credits
        val buttonExpand = itemView.button_main_item_expand
        val examView = itemView.recycle_view_exam
    }
}