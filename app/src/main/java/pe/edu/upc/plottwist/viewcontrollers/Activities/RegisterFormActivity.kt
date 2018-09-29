package pe.edu.upc.plottwist.viewcontrollers.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.activity_register_form.*
import pe.edu.upc.plottwist.Models.User
import pe.edu.upc.plottwist.R
import pe.edu.upc.plottwist.network.PlotTwistAPI
import pe.edu.upc.plottwist.network.UserResponse

class RegisterFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_form)





        registerButton.setOnClickListener{view->
            val userRegistered = User( nameTextInput.text.toString(), lastNameTextInput.text.toString(),emailTextInput.text.toString(), UserTextInput.text.toString(),
                    PasswordTextInput.text.toString(), ConfPasswordTextInput.text.toString(),ageInput.text.toString(), intent.extras.getString("rol"), sexTextInput.text.substring(0,1)

            )
            PlotTwistAPI.createAccount(userRegistered, {
                response -> handleResponse(response)},
                    { error -> handleError(error) })

        }

    }


    private fun handleResponse(response: UserResponse?) {
        if ("error".equals(response!!.status, true)) {
            Log.d("CatchUp", "Error: ${response.message}")
            return
        }

        startActivity(Intent( this , MainActivity::class.java))


    }

    private fun handleError(anError: ANError?) {
        startActivity(Intent( this , RegisterActivity::class.java))
    }


}
