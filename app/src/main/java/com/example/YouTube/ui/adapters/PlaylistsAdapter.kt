package com.example.YouTube.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.YouTube.data.model.Item
import com.example.counterofsixmonth.databinding.ItemPlaylistsBinding

class PlaylistsAdapter : PagingDataAdapter<Item, PlaylistsAdapter.PlaylistsViewHolder>(
     PlaylistsItemDiffUtils
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder =
        PlaylistViewHolder(
            ItemPlaylistsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class PlaylistViewHolder(val binding: ItemPlaylistsBinding) : ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.tvTitle.text = item.snippet.title
        }
    }

    class PlaylistsItemDiffUtils() : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem

    }
}


//class PlaylistsAdapter(
//    private val list: List<Item>
//) : RecyclerView.Adapter<PlaylistsAdapter.PlaylistViewHolder>() {
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder =
//        PlaylistViewHolder(
//            ItemPlaylistsBinding.inflate(
//                LayoutInflater.from(
//                    parent.context
//                ),
//                parent,
//                false
//            )
//        )
//
//    override fun getItemCount() = list.size
//
//    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
//        holder.bind(list[position])
//    }
//
//    class PlaylistViewHolder(val binding: ItemPlaylistsBinding) : ViewHolder(binding.root) {
//        fun bind(item: Item) {
//            binding.tvTitle.text = item.snippet.title
//        }
//    }
//}