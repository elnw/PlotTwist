package pe.edu.upc.plottwist.network

import com.androidnetworking.error.ANError
import pe.edu.upc.plottwist.Models.User
import org.json.JSONObject
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener
import com.androidnetworking.interfaces.OkHttpResponseListener
import com.androidnetworking.interfaces.ParsedRequestListener
import com.google.gson.reflect.TypeToken
import okhttp3.Response
import pe.edu.upc.plottwist.Models.Story
import pe.edu.upc.plottwist.R


class PlotTwistAPI {
    companion object {
        val baseUrl = "https://plotwistapi-v61764m35h.c9users.io"
        val sheetsUrl = "$baseUrl/sheets"
        val storyUrl = "$baseUrl/stories"
        val signinUrl = "$baseUrl/auth/sign_in"
        val signoutUrl = "$baseUrl/auth/sign_out"
        val registerUrl = "$baseUrl/auth"

        fun createAccount(registerUser: User, confPassword: String, responseHandler: (UserResponse?) -> Unit,
                          errorHandler: (ANError?) -> Unit) {


            AndroidNetworking.post(PlotTwistAPI.registerUrl)
                    .addBodyParameter("name", registerUser.name)

                    .addBodyParameter("email", registerUser.email)
                    .addBodyParameter("nickname", registerUser.nickname)
                    .addBodyParameter("password", registerUser.password)
                    .addBodyParameter("password_confirmation", confPassword)
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

        fun loginAccount(email: String, password: String, responseHandler: (Response?, User?) -> Unit,
                         errorHandler: (ANError?) -> Unit) {


            val us = object : TypeToken<User>() {}


            val ans = object : OkHttpResponseAndParsedRequestListener<User> {
                override fun onResponse(okHttpResponse: Response?, response: User?) {
                    responseHandler(okHttpResponse, response)
                }

                override fun onError(anError: ANError?) {
                    errorHandler(anError)
                }


            }

            AndroidNetworking.post(PlotTwistAPI.signinUrl)
                    .addBodyParameter("email", email)
                    .addBodyParameter("password", password)
                    .setTag("PlotTwist")
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsOkHttpResponseAndParsed(us, ans)

            /*.getAsObject(loginResponse::class.java, object : ParsedRequestListener<loginResponse> {
                override fun onResponse(response: loginResponse) {
                    responseHandler(response)
                }

                override fun onError(error: ANError) {
                    errorHandler(error)
                }
            })*/

            // return logAnswer.header!!


        }

        /* fun getStories( token: String, responseHandler: (StoryResponse?) -> Unit,
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


        }*/

        fun getSheets(token: String, responseHandler: (SheetResponse?) -> Unit,
                      errorHandler: (ANError?) -> Unit) {
            AndroidNetworking.get(PlotTwistAPI.sheetsUrl)
                    .addQueryParameter("access-token", token)
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

        fun createStory(token: String, client: String, uid: String, expiry: String,  title: String, address: String, body: String, sumary: String, geoCoderToken: String, userid:String,
                        responseHandler: (CreateStoryResponse?) -> Unit,
                        errorHandler: (ANError?) -> Unit) {

            val requestedLocation = GeocoderAPI.requestLatLng(address,"AIzaSyDPDG4oYjgupXv_cRXmpaePI7YQtDVKtX0")// geoCoderToken)


            AndroidNetworking.post(PlotTwistAPI.storyUrl)
                    .addBodyParameter("title", title)
                    .addBodyParameter("summary", sumary)
                    .addBodyParameter("body", body)
                    .addBodyParameter("latitude", requestedLocation!!.location.lat)
                    .addBodyParameter("longitude", requestedLocation.location.lng)
                    .addBodyParameter("userid", userid)
                    .addHeaders("access-token",token)
                    .addHeaders("client",client)
                    .addHeaders("uid",uid)
                    .addHeaders("expiry",expiry)
                    .setTag("PlotTwist")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsObject(CreateStoryResponse::class.java, object : ParsedRequestListener<CreateStoryResponse> {
                        override fun onResponse(response: CreateStoryResponse) {
                            responseHandler(response)
                        }

                        override fun onError(error: ANError) {
                            errorHandler(error)
                        }
                    })


        }

        fun getStories(token: String, client: String, uid: String, expiry: String, responseHandler: (StoryResponse?) -> Unit,
                       errorHandler: (ANError?) -> Unit) {

            AndroidNetworking.get(PlotTwistAPI.storyUrl)
                    .addHeaders("access-token", token)
                    .addHeaders("client", client)
                    .addHeaders("uid", uid)
                    .addHeaders("expiry", expiry)
                    .setTag("PlotTwist")
                    .build()
                    .getAsObject(StoryResponse::class.java, object : ParsedRequestListener<StoryResponse> {
                        override fun onResponse(response: StoryResponse) {
                            responseHandler(response)
                        }

                        override fun onError(error: ANError) {
                            errorHandler(error)
                        }
                    })


        }
    }
}