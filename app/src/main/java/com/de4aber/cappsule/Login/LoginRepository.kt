package com.de4aber.cappsule.Login

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.de4aber.cappsule.Friend.FriendDTO
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject

private const val TAG ="LoginRepository"

class LoginRepository {

    private val url = "http://185.51.76.204:8091/Auth"

    private val httpClient: AsyncHttpClient = AsyncHttpClient()

    fun login(context: Context, loginDTO: LoginDTO): MutableLiveData<JwtTokenDTO> {
        val response: MutableLiveData<JwtTokenDTO> = MutableLiveData()

        val params = JSONObject()
        params.put("username", loginDTO.username)
        params.put("password", loginDTO.password)
        val entity = StringEntity(params.toString())

        httpClient.post(context,"$url/Login", entity, "application/json", object: AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                response.value = JwtTokenDTO(JSONObject(String(responseBody!!)))
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d(TAG, "failure in login statusCode = $statusCode")
            }

        })

        return response
    }

}