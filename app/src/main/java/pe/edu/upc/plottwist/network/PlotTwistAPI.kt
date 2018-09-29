package pe.edu.upc.plottwist.network

import com.androidnetworking.error.ANError
import pe.edu.upc.plottwist.Models.User
import org.json.JSONObject
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.interfaces.ParsedRequestListener


class PlotTwistAPI {
    companion object {
        val baseUrl = "https://plotwistapi-v61764m35h.c9users.io"
        val sheetsUrl = "$baseUrl/sheets"
        val storyUrl = "$baseUrl/stories"
        val signinUrl = "$baseUrl/auth/sign_in"
        val signoutUrl = "$baseUrl/auth/sign_out"
        val registerUrl = "$baseUrl/auth"

        fun createAccount(registerUser: User, responseHandler: (UserResponse?) -> Unit,
                          errorHandler: (ANError?) -> Unit ){


            AndroidNetworking.post(PlotTwistAPI.registerUrl)
                    .addBodyParameter("name", registerUser.name)
                    .addBodyParameter("lastname", registerUser.lastname)
                    .addBodyParameter("email", registerUser.mail)
                    .addBodyParameter("username", registerUser.username)
                    .addBodyParameter("password", registerUser.password)
                   .addBodyParameter("age", registerUser.age)
                    .addBodyParameter("rol", registerUser.rol)
                   .addBodyParameter("sex", registerUser.sex)
                    .addBodyParameter("password_confirmation", registerUser.confPassword)
                    .addBodyParameter("confirm_success_url", "www.google.com")
                    .setTag("PlotTwist")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsObject(UserResponse::class.java, object : ParsedRequestListener<UserResponse> {
                        override fun onResponse(response: UserResponse) {
                            responseHandler(response)
                        }

                        override fun onError(error: ANError) {
                            errorHandler(error)
                        }
                    })
}
        fun loginAccount( email: String, password: String , responseHandler: (loginResponse?) -> Unit,
                          errorHandler: (ANError?) -> Unit ){

            AndroidNetworking.post(PlotTwistAPI.signinUrl)
                    .addBodyParameter("email", email)
                    .addBodyParameter("password",password)
                    .setTag("PlotTwist")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsObject(loginResponse::class.java, object : ParsedRequestListener<loginResponse> {
                        override fun onResponse(response: loginResponse) {
                            responseHandler(response)
                        }

                        override fun onError(error: ANError) {
                            errorHandler(error)
                        }
                    })




        }

        fun getStories( token: String, responseHandler: (StoryResponse?) -> Unit,
                        errorHandler: (ANError?) -> Unit ){

            AndroidNetworking.get(PlotTwistAPI.storyUrl)
                    .addQueryParameter("access-token", token)
                    .setTag("PlotTwist")
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsObject(StoryResponse::class.java, object : ParsedRequestListener<StoryResponse> {
                        override fun onResponse(response: StoryResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })


        }

        fun getSheets(token:String,responseHandler: (SheetResponse?) -> Unit,
        errorHandler: (ANError?) -> Unit ){
            AndroidNetworking.get(PlotTwistAPI.sheetsUrl)
                    .addQueryParameter("access-token",token)
                    .setTag("PlotTwist")
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsObject(SheetResponse::class.java, object : ParsedRequestListener<SheetResponse> {
                        override fun onResponse(response: SheetResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }

        fun createStory( token:String){

            AndroidNetworking.post(PlotTwistAPI.storyUrl)



        }




    }
}