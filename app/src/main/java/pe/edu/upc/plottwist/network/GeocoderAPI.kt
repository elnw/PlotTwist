package pe.edu.upc.plottwist.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener

class GeocoderAPI {
    companion object {
        val baseURL = "https://maps.googleapis.com/maps/api/geocode/json"

        fun requestAddress( latlng:String, key:String, responseHandler: (GeocoderRespone?) -> Unit,
                            errorHandler: (ANError?) -> Unit){

            AndroidNetworking.get(baseURL)
                    .addQueryParameter("latlng", latlng )
                    .addQueryParameter("key",key)
                    .setTag("PlotTwist")
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsObject(GeocoderRespone::class.java, object : ParsedRequestListener<GeocoderRespone> {
                        override fun onResponse(response: GeocoderRespone) {
                            responseHandler(response)
                        }

                        override fun onError(error: ANError) {
                            errorHandler(error)
                        }
                    })


        }

        fun requestLatLng( address: String, key: String,  responseHandler: (GeocoderRespone?) -> Unit,
                           errorHandler: (ANError?) -> Unit){
            AndroidNetworking.get(baseURL)
                    .addQueryParameter("address", address )
                    .addQueryParameter("key",key)
                    .setTag("PlotTwist")
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsObject(GeocoderRespone::class.java, object : ParsedRequestListener<GeocoderRespone> {
                        override fun onResponse(response: GeocoderRespone) {
                            responseHandler(response)
                        }

                        override fun onError(error: ANError) {
                            errorHandler(error)
                        }
                    })
        }

    }
}