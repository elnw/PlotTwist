package pe.edu.upc.plottwist

import android.app.Application
import com.androidnetworking.AndroidNetworking

class PlotTwist: Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(getApplicationContext());
    }
}