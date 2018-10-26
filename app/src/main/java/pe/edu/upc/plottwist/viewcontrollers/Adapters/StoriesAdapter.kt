package pe.edu.upc.plottwist.viewcontrollers.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_story.view.*
import pe.edu.upc.plottwist.Models.Story
import pe.edu.upc.plottwist.R

class
StoriesAdapter(var stories: ArrayList<Story>, val context: Context):
        RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.item_story, parent, false))
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val story = stories.get(position)
        holder.updateFrom(story)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView = view.titleTextView
        val summaryTextView = view.summaryTextView
        val storyLayout = view.storyLayout


        fun updateFrom(story: Story) {
            titleTextView.text = story.title
            summaryTextView.text = story.summary
            /* storyLayout.setOnClickListener { view ->
                 val context = view.context
                 context.startActivity(
                         Intent(context, ArticleActivity::class.java)
                                 .putExtras(article.toBundle()))
             }*/
        }
    }

}