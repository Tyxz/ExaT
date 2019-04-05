package de.tyxar.examtabel.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.tyxar.examtabel.R
import de.tyxar.examtabel.database.entity.Exam
import de.tyxar.examtabel.view.model.coe.CatOfExaViewModel
import kotlinx.android.synthetic.main.recycle_view_exam_item.view.*

class ExamRecycleViewAdapter(private val context : Context, private val categoryId : Int, private val viewModel: CatOfExaViewModel) : ListAdapter<Exam, ExamRecycleViewAdapter.ExamHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_category_item, parent, false)
        return ExamHolder(itemView)
    }

    private var expandedItemHolder : ExamHolder? = null

    override fun onBindViewHolder(holder: ExamHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            textName.text = item.exam_name
            textCredits.text = item.exam_credits.toString()
            isActive.isChecked = item.active_category_id == categoryId
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Exam>() {
            override fun areItemsTheSame(oldItem: Exam, newItem: Exam): Boolean {
                return oldItem.exam_id == newItem.exam_id
            }

            override fun areContentsTheSame(oldItem: Exam, newItem: Exam): Boolean {
                return oldItem.exam_name == newItem.exam_name
                        && oldItem.exam_credits == newItem.exam_credits
                        && oldItem.exam_grade == newItem.exam_grade
                        && oldItem.exam_date == newItem.exam_date
                        && oldItem.active_category_id == newItem.active_category_id
            }
        }
    }

    class ExamHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.text_main_item_exam
        val textCredits = itemView.text_main_item_credits
        val isActive = itemView.checkbox
    }
}