package com.de4aber.cappsule.User

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

private const val TAG ="UserRepo"
class UserRepo {

    private val url = "http://185.51.76.204:8091/User"

    private val httpClient: AsyncHttpClient = AsyncHttpClient()

    fun getAll(): LiveData<List<UserDTO>>{

        val response: MutableLiveData<List<UserDTO>> = MutableLiveData()
        httpClient.get("$url/GetAllDTOs", object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val users = getUsersFromString( String(responseBody!!) )
                Log.d(TAG, "Users received - ${users.size}")
                response.value =users
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

        return response
    }

    fun getUserById(id: Int): LiveData<UserDTO>{
        val response: MutableLiveData<UserDTO> = MutableLiveData()
        httpClient.get("$url/$id", object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                response.value = UserDTO(JSONObject(String(responseBody!!)))
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d(TAG, "failure in getUserById statusCode = $statusCode")
            }

        })
        return response
    }

    fun searchUsersByUsername(searchstring: String): MutableLiveData<List<UserLimitedInfoDTO>> {
        val response: MutableLiveData<List<UserLimitedInfoDTO>> = MutableLiveData()

        httpClient.get("$url/SearchByUsername/$searchstring", object :AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val users = getUsersLimitedInfoFromString(String(responseBody!!))
                Log.d(TAG, "Users received by search - ${users.size}")
                response.value = users
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d(TAG, "failure in searchUsersByUsername statusCode = $statusCode")
            }

        })

        return response
    }


    //Virker prob ikke
    fun createUser(context: Context, userDTO: CreateUserDTO): MutableLiveData<UserDTO> {
        val params = JSONObject()
        params.put("username", userDTO.username)
        params.put("password", userDTO.username)
        params.put("birthDate", userDTO.birthdate)
        val entity = StringEntity(params.toString())

        val response: MutableLiveData<UserDTO> = MutableLiveData()

        httpClient.post(context,"$url/CreateUser",entity, "application/json", object : AsyncHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                Log.d(TAG, "success in createUser statusCode = $statusCode")
                response.value = UserDTO(JSONObject(String(responseBody!!)))
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

        return response

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

    private fun getUsersLimitedInfoFromString(jsonString: String?): List<UserLimitedInfoDTO> {
        val result = ArrayList<UserLimitedInfoDTO>()

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
                result.add(UserLimitedInfoDTO(array.getJSONObject(i)))
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return result
    }


}