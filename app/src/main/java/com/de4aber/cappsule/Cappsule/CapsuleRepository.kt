package com.de4aber.cappsule.Cappsule

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.de4aber.cappsule.Friend.FriendRequestDTO
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject

private const val TAG ="CapsuleRepository"
class CapsuleRepository {

    private val url = "http://185.51.76.204:8091/Capsule"

    private val httpClient: AsyncHttpClient = AsyncHttpClient()

    fun sendCapsule(context: Context, sendCapsuleDTO: SendCapsuleDTO): MutableLiveData<Boolean> {

        val params = JSONObject()
        params.put("senderId", sendCapsuleDTO.senderId)
        params.put("recieverUsername", sendCapsuleDTO.recieverUserName)
        params.put("message", sendCapsuleDTO.message)
        params.put("time", sendCapsuleDTO.date + "T" + sendCapsuleDTO.time)
        params.put("latitude", sendCapsuleDTO.latitude)
        params.put("longitude", sendCapsuleDTO.longitude)
        params.put("photo", sendCapsuleDTO.photo)
        val entity = StringEntity(params.toString())

        val response: MutableLiveData<Boolean> = MutableLiveData()

        httpClient.post(context, "$url/SendCapsule", entity, "application/json", object: AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                response.value = String(responseBody!!).toBoolean()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d(TAG, "failure in sendCapsule statusCode = $statusCode")
            }

        })
        return response

    }
}