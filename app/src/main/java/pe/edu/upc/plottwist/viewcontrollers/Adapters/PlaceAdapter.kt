package pe.edu.upc.plottwist.viewcontrollers.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_place.view.*
import kotlinx.android.synthetic.main.item_story.view.*
import pe.edu.upc.plottwist.Models.Sheet
import pe.edu.upc.plottwist.R
import pe.edu.upc.plottwist.viewcontrollers.Activities.StoriesActivity

class PlaceAdapter(var places: ArrayList<Sheet>, val context: Context): RecyclerView.Adapter<PlaceAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceAdapter.ViewHolder {
        return PlaceAdapter.ViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.item_place, parent, false))
    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onBindViewHolder(holder: PlaceAdapter.ViewHolder, position: Int) {
        val place = places.get(position)
        holder.updateFrom(place)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeTextView = view.placeTextView
        val titleTextView = view.sheetName
        val placeLayout = view.placeLayout


        fun updateFrom(place: Sheet) {
            placeTextView.text= place.placeLat //GEOCODER
            titleTextView.text = place.updated_at

            placeLayout.setOnClickListener { view ->
                val context = view.context
                context.startActivity( Intent(context, StoriesActivity::class.java).putExtra("sheet", place.toBundle() ))
            }



        }
    }



}
