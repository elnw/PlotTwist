package pe.edu.upc.plottwist.viewcontrollers.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_stories.*
import pe.edu.upc.plottwist.Models.Sheet
import pe.edu.upc.plottwist.Models.Story
import pe.edu.upc.plottwist.R
import pe.edu.upc.plottwist.network.PlotTwistAPI
import pe.edu.upc.plottwist.viewcontrollers.Adapters.StoriesAdapter

class StoriesActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stories)

        val sheet = Sheet.from(intent.getBundleExtra("sheet"))


        titleStory.text = sheet.updated_at
        storyContent.text = sheet.body





    }
}
