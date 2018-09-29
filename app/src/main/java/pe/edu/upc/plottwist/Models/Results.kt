package pe.edu.upc.plottwist.Models

data class Results(
        val address_components: ArrayList<AddressComponents>? = null,
        val formatted_address:String = "",
        val geometry: Geometry? = null,
        var location_type:String = "",
        var viewport:Viewport? = null,
        var place_id: String = "",
        var types: String= ""
) {
}