package pe.edu.upc.plottwist.network

import pe.edu.upc.plottwist.Models.Story

class StoryResponse {
    var status: String = "ok"
    var code: String? = null
    var message: String? = null

    var stories : ArrayList<Story>? = null


}