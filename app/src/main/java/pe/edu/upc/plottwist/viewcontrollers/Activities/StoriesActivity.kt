package pe.edu.upc.plottwist.viewcontrollers.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import pe.edu.upc.plottwist.Models.Story
import pe.edu.upc.plottwist.R
import pe.edu.upc.plottwist.network.PlotTwistAPI
import pe.edu.upc.plottwist.viewcontrollers.Adapters.StoriesAdapter

class StoriesActivity : AppCompatActivity() {

    var stories = ArrayList<Story>()
    private lateinit var articlesRecyclerView: RecyclerView
    private lateinit var articlesAdapter: StoriesAdapter
    private lateinit var articlesLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stories)

        /*PlotTwistAPI.loginAccount( mailTextInput.text.toString(), passTextIput.text.toString(),{
            response -> handleResponse(response)},
                { error -> handleError(error) } )*/




    }
}
