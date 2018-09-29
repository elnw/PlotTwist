package pe.edu.upc.plottwist.viewcontrollers.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import pe.edu.upc.plottwist.R


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        writerButton.setOnClickListener { view ->
            startActivity(Intent(view.context, RegisterFormActivity::class.java).putExtra("rol", getString(R.string.rol_writer))) }

        readerButton.setOnClickListener { view ->
            startActivity(Intent(view.context, RegisterFormActivity::class.java).putExtra("rol", getString(R.string.rol_reader)))

        }

        bussinessButton.setOnClickListener { view ->
            startActivity(Intent(view.context, RegisterFormActivity::class.java).putExtra("rol", getString(R.string.rol_bussiness)))

        }







    }


}



