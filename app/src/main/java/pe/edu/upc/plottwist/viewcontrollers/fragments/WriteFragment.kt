package pe.edu.upc.plottwist.viewcontrollers.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.fragment_write.*
import kotlinx.android.synthetic.main.fragment_write.view.*


import pe.edu.upc.plottwist.R
import pe.edu.upc.plottwist.network.CreateStoryResponse
import pe.edu.upc.plottwist.network.PlotTwistAPI



class WriteFragment : Fragment() {
    var token:String = ""
    var client: String = ""
    var uid: String = ""
    var expiry: String = ""
    var userid: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val modifyView = inflater.inflate(R.layout.fragment_write, container, false)



        modifyView.createButtom.setOnClickListener{view->
            val title = titleTextView.text.toString()
            val address = addressTextView.text.toString()
            val body = bodytextView.text.toString()
            val summary = summaryTextView.text.toString()
            val geoCoderToken = getString(R.string.geocoder_key)
            PlotTwistAPI.createStory(token, client, uid, expiry, title,address,body, summary, geoCoderToken, userid,
                    { response -> handleResponse(response)},
                    { error -> handleError(error) })


        }

        return modifyView
    }

    private fun handleResponse(response: CreateStoryResponse?) {

        Toast.makeText(activity, getString(R.string.success_publish) , Toast.LENGTH_LONG).show()
        titleTextView.text.clear()
        addressTextView.text.clear()
        bodytextView.text.clear()
        summaryTextView.text.clear()
        //Toast.makeText(activity, getString(R.string.error_text), Toast.LENGTH_SHORT).show()



    }

    private fun handleError(anError: ANError?) {
        Log.d("PlotTwist", anError.toString())


     Toast.makeText(activity, getString(R.string.error_text), Toast.LENGTH_SHORT).show()


       //val message = Toast.makeText(this@WriteFragment, R.string.failure_action, Toast.LENGTH_SHORT)



    }





}
