package pe.edu.upc.plottwist.viewcontrollers.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
            val userRegistered = User( "0",nameTextInput.text.toString() + " " + lastNameTextInput.text.toString(),emailTextInput.text.toString(), UserTextInput.text.toString(),
                    PasswordTextInput.text.toString()

            )
            PlotTwistAPI.createAccount(userRegistered, ConfPasswordTextInput.text.toString(),
                    { response -> handleResponse(response)},
                    { error -> handleError(error) })

        }

    }


    private fun handleResponse(response: UserResponse?) {
        if ("error".equals(response!!.status, true)) {
            Toast.makeText(this, getString(R.string.failure_action) , Toast.LENGTH_LONG).show()
            Log.d("CatchUp", "Error: ${response.message}")
            return
        }
        Toast.makeText(this, getString(R.string.succesful_signup) , Toast.LENGTH_LONG).show()
        startActivity(Intent( this , MainActivity::class.java))


    }

    private fun handleError(anError: ANError?) {
        Toast.makeText(this, getString(R.string.failure_action) , Toast.LENGTH_LONG).show()
        startActivity(Intent( this , RegisterActivity::class.java))
    }


}
