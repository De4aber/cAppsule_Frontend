package com.de4aber.cappsule.User

import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONException

private const val TAG ="UserRepo"
class UserRepo {

    private val url = "http://185.51.76.204:8091/User"

    private val httpClient: AsyncHttpClient = AsyncHttpClient()

    fun getAll(callback: ICallback){
        httpClient.get("$url/GetAllDTOs", object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val users = getUsersFromString( String(responseBody!!) )
                Log.d(TAG, "Users received - ${users.size}")
                callback.onUsersReady( users )
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d(TAG, "failure in getAll statusCode = $statusCode")
            }

        })
    }

    fun createUser(userDTO: UserDTO) {
        val params = RequestParams()
        params.put("username", userDTO.username)

        httpClient.post("$url/CreateUser", params, object : AsyncHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                Log.d(TAG, "success in createUser statusCode = $statusCode")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d(TAG, "failure in createUser statusCode = $statusCode")
            }
        })



    }

    private fun getUsersFromString(jsonString: String?): List<UserDTO> {
        val result = ArrayList<UserDTO>()

        if (jsonString!!.startsWith("error")) {
            Log.d(TAG, "Error: $jsonString")
            return result
        }
        if (jsonString == null) {
            Log.d(TAG, "Error: NO RESULT")
            return result
        }
        var array: JSONArray?
        try {
            array = JSONArray(jsonString)
            for (i in 0 until array.length()) {
                result.add(UserDTO(array.getJSONObject(i)))
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return result
    }
}