package acr.browser.lightning.browser.home.adpter

import acr.browser.lightning.R
import acr.browser.lightning.database.Bookmark
import acr.browser.lightning.browser.image.ImageLoader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class BookmarkHomeAdapter(
    private val imageLoader: ImageLoader,
    private val onClick: (Bookmark.Entry) -> Unit,
) : ListAdapter<Bookmark, BookmarkHomeAdapter.BookmarkHomeViewHolder>(object :
    DiffUtil.ItemCallback<Bookmark>() {
    override fun areItemsTheSame(oldItem: Bookmark, newItem: Bookmark) = oldItem.url == newItem.url
    override fun areContentsTheSame(oldItem: Bookmark, newItem: Bookmark) = oldItem == newItem
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkHomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bookmark, parent, false)
        return BookmarkHomeViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: BookmarkHomeViewHolder, position: Int) {
        val item = getItem(position)
        if (item is Bookmark.Entry) {
            holder.bind(item,imageLoader)
        }
    }

    class BookmarkHomeViewHolder(
        itemView: View,
        private val onClick: (Bookmark.Entry) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.bookmarkIcon)
        private val title: TextView = itemView.findViewById(R.id.bookmarkTitle)

        fun bind(bookmark: Bookmark.Entry, imageLoader: ImageLoader) {
            title.text = bookmark.title
            // icon.setImageResource(bookmark.folder)
            imageLoader.loadImage(icon, bookmark)
            itemView.setOnClickListener { onClick(bookmark) }
        }
    }
}