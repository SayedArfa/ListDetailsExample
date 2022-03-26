package com.sarfa.listdetailsexample.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sarfa.domain.model.entity.Item
import com.sarfa.listdetailsexample.R
import com.sarfa.listdetailsexample.databinding.ItemRowBinding
import com.sarfa.listdetailsexample.ui.util.ext.loadFromUrl


class ItemListAdapter() :
    RecyclerView.Adapter<ItemListAdapter.ProjectViewHolder>() {
    var onProjectClickedListener: ((Item) -> Unit)? = null

    private val diffUtilCallbackItem = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffUtilCallbackItem)

    fun submitList(list: List<Item>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProjectViewHolder {
        return ProjectViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemListAdapter.ProjectViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRowBinding.bind(view)

        fun bind(item: Item) {
            binding.itemImageview.loadFromUrl(item.image_urls_thumbnails.firstOrNull())
            binding.nameTv.text = item.name
            binding.priceTv.text = item.price
            itemView.setOnClickListener {
                onProjectClickedListener?.invoke(item)
            }
        }
    }

}