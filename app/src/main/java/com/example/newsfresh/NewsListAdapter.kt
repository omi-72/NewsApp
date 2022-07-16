package com.example.newsfresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewSViewHolder>() {
    private val item:ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewSViewHolder {
        val View =LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewSViewHolder(View)
        View.setOnClickListener{
          listener.onItemClicked(item[viewHolder.adapterPosition])

        }
        return viewHolder

    }

    override fun getItemCount(): Int {
        return item.size

    }

    override fun onBindViewHolder(holder: NewSViewHolder, position: Int) {
        val currentItem = item[position]
        holder.titleView.text= currentItem.title
        holder.titleView.text = currentItem.title
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)

    }
  fun updateNews(updatedNews: ArrayList<News>){
      item.clear()
      item.addAll(updatedNews)

      notifyDataSetChanged()
  }
}
class NewSViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView= itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)

}
interface NewsItemClicked{
    fun onItemClicked(item: News)
}