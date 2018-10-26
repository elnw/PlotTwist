package pe.edu.upc.plottwist.viewcontrollers.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.fragment_home.view.*
import pe.edu.upc.plottwist.Models.Story

import pe.edu.upc.plottwist.R
import pe.edu.upc.plottwist.network.GeocoderRespone
import pe.edu.upc.plottwist.network.PlotTwistAPI
import pe.edu.upc.plottwist.network.StoryResponse
import pe.edu.upc.plottwist.viewcontrollers.Adapters.StoriesAdapter


class HomeFragment : Fragment() {
    var tokenUser:String = ""
    var client: String = ""
    var uid: String = ""
    var expiry: String = ""


    var stories = ArrayList<Story>()

    private lateinit var storyRecyclerView: RecyclerView
    private lateinit var storyAdapter: StoriesAdapter
    private lateinit var storyLayoutManager: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        storyRecyclerView = view.storiesRecyclerView
        storyAdapter = StoriesAdapter(stories,  view.context)
        storyLayoutManager = GridLayoutManager(view.context, 1)
        storyRecyclerView.adapter = storyAdapter
        storyRecyclerView.layoutManager = storyLayoutManager



        PlotTwistAPI.getStories( tokenUser,client, uid, expiry,
                { response -> handleResponse(response)},
                { error -> handleError(error) })
        return view

    }

    private fun handleResponse(response: StoryResponse?) {
        if ("error".equals(response!!.status, true)) {
            Log.d("PlotTwist", response.stories!!.count().toString() )
            return
        }

        stories = response.stories!!
        Log.d("PlotTwist", stories.count().toString())
        storyAdapter.stories= stories
        storyAdapter.notifyDataSetChanged()
        //val address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)



        /* for (sheet: Sheet in sheets){
             placesAux.add(sheet.placeLat+ "," + sheet.placeLong)
         }*/
    }

    private fun handleError(anError: ANError?) {
        Log.d("CatchUp", anError!!.message)
        return
    }

    private fun handleResponseGeocoder(response: GeocoderRespone?) {
        if ("error".equals(response!!.status, true)) {
            return
        }

    }



}
