package pe.edu.upc.plottwist.viewcontrollers.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.error.ANError
import com.orm.SugarApp
import com.orm.SugarRecord
import kotlinx.android.synthetic.main.activity_login.*

import pe.edu.upc.plottwist.R
import pe.edu.upc.plottwist.network.PlotTwistAPI
import pe.edu.upc.plottwist.network.loginResponse

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener{view->
            PlotTwistAPI.loginAccount( mailTextInput.text.toString(), passTextIput.text.toString(),{
                response -> handleResponse(response)},
                    { error -> handleError(error) } )
        }



    }

    private fun handleResponse(response: loginResponse?) {
        if ( response!!.login == null ) {
            Log.d("PlotTwist", "Contraseña incorrecta")
            return
        }

        SugarRecord.save(response.login)


        startActivity(Intent( this , BeginActivity::class.java).putExtra("token",response.access_token))


    }

    private fun handleError(anError: ANError?) {
        startActivity(Intent( this ,LoginActivity::class.java))
    }
}
