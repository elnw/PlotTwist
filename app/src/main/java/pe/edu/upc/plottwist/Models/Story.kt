package pe.edu.upc.plottwist.Models

data class Story (
        val userid: Int,
        val image:String,
        val title:String ,
        val summary:String,
        val body: String,
        val latitude: String,
        val longitude: String,
        val created_at: String
){
}