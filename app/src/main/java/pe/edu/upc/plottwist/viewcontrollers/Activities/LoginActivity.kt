package pe.edu.upc.plottwist.viewcontrollers.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.error.ANError
import com.orm.SugarApp
import com.orm.SugarRecord
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.Response
import pe.edu.upc.plottwist.Models.User

import pe.edu.upc.plottwist.R
import pe.edu.upc.plottwist.network.PlotTwistAPI
import pe.edu.upc.plottwist.network.loginResponse

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        loginButton.setOnClickListener{view->
            PlotTwistAPI.loginAccount( mailTextInput.text.toString(), passTextIput.text.toString() ,
                    { okHttpResponse, response -> handleResponse(okHttpResponse,response)},
                    { error -> handleError(error) })

        }

    }

    private fun handleResponse(okHttpResponse: Response?, response: User?) {
        if (response != null && okHttpResponse != null) {

                    startActivity(Intent( this , BeginActivity::class.java)
                            .putExtra("token",okHttpResponse.header("access-token"))
                            .putExtra("client", okHttpResponse.header("client"))
                            .putExtra("uid",okHttpResponse.header("uid"))
                            .putExtra("expiry", okHttpResponse.header("expiry"))
                            .putExtra("userId",response.id))
                   // Log.d("PlotTwist", response.headers().toString())
        }
        else{
            Toast.makeText(this, getString(R.string.error_incorrect_password) , Toast.LENGTH_LONG).show()
            Log.d("PlotTwist","Contrasena incorrecta")
            startActivity(Intent( this , LoginActivity::class.java))
        }

        //SugarRecord.save(response.login)





    }

    private fun handleError(anError: ANError?) {
        Log.d("PlotTwist", anError.toString())
        Toast.makeText(this, getString(R.string.error_incorrect_password) , Toast.LENGTH_LONG).show()
        startActivity(Intent( this , MainActivity::class.java))

    }
}
