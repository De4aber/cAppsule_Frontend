package com.de4aber.cappsule.Friend

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

private const val TAG ="FriendRepository"

class FriendRepository {

    private val url = "http://185.51.76.204:8091/Friendship"

    private val httpClient: AsyncHttpClient = AsyncHttpClient()

    fun getFriendsByUserId(userId: Int): LiveData<List<FriendDTO>>{
        val response: MutableLiveData<List<FriendDTO>> = MutableLiveData()

        httpClient.get("$url/GetFriendsByUserId/$userId", object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val friends = getFriendDTOsFromString( String(responseBody!!) )
                Log.d(TAG, "Users received - ${friends.size}")
                response.value = friends
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

    fun getFriendRequestByUserId(userId: Int): LiveData<List<FriendRequestReceiverDTO>>{
        val response: MutableLiveData<List<FriendRequestReceiverDTO>> = MutableLiveData()

        httpClient.get("$url/GetFriendRequestsByUserId/$userId", object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val friends = getFriendsFromString( String(responseBody!!) )
                Log.d(TAG, "Users received - ${friends.size}")
                response.value = friends
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

    fun acceptFriendRequest(userId: Int, friendshipId: Int): MutableLiveData<FriendDTO> {
        val response: MutableLiveData<FriendDTO> = MutableLiveData()


        httpClient.put("$url/AcceptFriendRequest?friendshipId=$friendshipId&acceptingUserId=$userId", object: AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                response.value = FriendDTO(JSONObject(String(responseBody!!)))
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d(TAG, "failure in AcceptFriendRequest statusCode = $statusCode")
            }

        })

        return response
    }

    private fun getFriendDTOsFromString(jsonString: String?): List<FriendDTO> {
        val result = ArrayList<FriendDTO>()

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
                result.add(FriendDTO(array.getJSONObject(i)))
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return result
    }

    private fun getFriendsFromString(jsonString: String?): List<FriendRequestReceiverDTO> {
        val result = ArrayList<FriendRequestReceiverDTO>()

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
                result.add(FriendRequestReceiverDTO(array.getJSONObject(i)))
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return result
    }
}