package pe.edu.upc.plottwist.Models

import com.orm.SugarRecord

class Login() : SugarRecord() {
    var user: User? = null
    var token: String = ""
    var identifier: Int = 1

    constructor(user: User, token: String) : this() {
        this.user = user
        this.token = token
        this.identifier = 1
    }

    companion object {
        fun getData(identifier: String):List<Login>{
            return SugarRecord.find(
                    Login::class.java,
                    "identifier = ?", identifier)

        }
    }



}