package pe.edu.upc.plottwist.viewcontrollers.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_write.*

import pe.edu.upc.plottwist.R



class WriteFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        createButtom.setOnClickListener{view->


        }

        return inflater.inflate(R.layout.fragment_write, container, false)
    }





}
