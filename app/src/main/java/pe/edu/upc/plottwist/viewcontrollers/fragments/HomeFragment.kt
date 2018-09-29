package pe.edu.upc.plottwist.viewcontrollers.fragments

import android.content.Context
import android.net.Uri
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
import pe.edu.upc.plottwist.Models.Login
import pe.edu.upc.plottwist.Models.Sheet
import pe.edu.upc.plottwist.Models.Story

import pe.edu.upc.plottwist.R
import pe.edu.upc.plottwist.network.GeocoderRespone
import pe.edu.upc.plottwist.network.PlotTwistAPI
import pe.edu.upc.plottwist.network.SheetResponse
import pe.edu.upc.plottwist.network.StoryResponse
import pe.edu.upc.plottwist.viewcontrollers.Adapters.PlaceAdapter


class HomeFragment : Fragment() {

    var sheets = ArrayList<Sheet>()
    var placesAux :MutableSet<String> = mutableSetOf()
    var places= ArrayList<String>()



    private lateinit var placesRecyclerView: RecyclerView
    private lateinit var placesAdapter: PlaceAdapter
    private lateinit var placeLayoutManager: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        placesRecyclerView = view.placesRecyclerView
        placesAdapter = PlaceAdapter(places,  view.context)
        placeLayoutManager = GridLayoutManager(view.context, 2)
        placesRecyclerView.adapter = placesAdapter
        placesRecyclerView.layoutManager = placeLayoutManager

        val loginData = Login.getData("1")

        PlotTwistAPI.getSheets( loginData[0].token ,
                { response -> handleResponse(response)},
                { error -> handleError(error) })
        return view

    }

    private fun handleResponse(response: SheetResponse?) {
        if ("error".equals(response!!.status, true)) {

            return
        }

        sheets = response.sheets!!
        //val address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)



        for (sheet: Sheet in sheets){
            placesAux.add(sheet.placeLat+ "," + sheet.placeLong)
        }






    }

    private fun handleError(anError: ANError?) {
        Log.d("CatchUp", anError!!.message)
    }

    private fun handleResponseGeocoder(response: GeocoderRespone?) {
        if ("error".equals(response!!.status, true)) {
            return
        }

    }



}
