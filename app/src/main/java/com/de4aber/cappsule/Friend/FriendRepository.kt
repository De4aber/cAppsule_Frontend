package com.de4aber.cappsule.Friend

import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONException

private const val TAG ="FriendRepository"

class FriendRepository {

    private val url = "http://185.51.76.204:8091/Friendship"

    private val httpClient: AsyncHttpClient = AsyncHttpClient()

    fun getFriendsByUserId(friendCallback: IFriendCallback, userId: Int){
        httpClient.get("$url/GetFriendsByUserId/$userId", object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val friends = getFriendsFromString( String(responseBody!!) )
                Log.d(TAG, "Users received - ${friends.size}")
                friendCallback.onFriendsReady(friends)
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

    private fun getFriendsFromString(jsonString: String?): List<FriendDTO> {
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
}