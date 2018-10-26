package pe.edu.upc.plottwist.Models

import android.os.Bundle

data class Sheet(  val id : String = "",
                   val sheetId: String = "",
                   val rammificationIdentifier: String = "",
                   val body: String = "",
                   val placeLat: String ="",
                   val placeLong: String ="",
                   val created_at: String = "",
                   val updated_at: String = "") {
    companion object {
        fun from(bundle: Bundle): Sheet {
            return Sheet(
                    bundle.getString("id"),
                    bundle.getString("sheetId"),
                    bundle.getString("rammificationIdentifier"),
                    bundle.getString("body"),
                    bundle.getString("placeLat"),
                    bundle.getString("placeLong"),
                    bundle.getString("created_at"),
                    bundle.getString("updated_at")
            )


        }
    }

    fun toBundle() : Bundle{
        val bundle = Bundle()
        bundle.putString("id", id )
        bundle.putString("sheetId",sheetId)
        bundle.putString("rammificationIdentifier",rammificationIdentifier)

        bundle.putString("body",body)
        bundle.putString("placeLat",placeLat)

        bundle.putString("placeLong",placeLong)
        bundle.putString("created_at",created_at)
        bundle.putString("updated_at",updated_at)




        return bundle
    }


}