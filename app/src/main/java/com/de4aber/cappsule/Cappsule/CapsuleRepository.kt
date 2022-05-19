package com.de4aber.cappsule.Cappsule

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream

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
        //TODO implement photos
        params.put("photo", null)
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


    fun getCapsulesByReceiverId(receiverId: Int): LiveData<List<Capsule>> {
        val response: MutableLiveData<List<Capsule>> = MutableLiveData()
        httpClient.get("$url/GetByReceiverId/$receiverId", object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val receivedDTOs = getReceiveCapsuleDTOsFromString( String(responseBody!!) )
                Log.d(TAG, "ReceiveCapsuleDTO received - ${receivedDTOs.size}")
                val receivedCapsules: MutableList<Capsule> = mutableListOf()
                for (dto in receivedDTOs){
                    receivedCapsules.add(Capsule(dto.senderUsername,dto.message, dto.time, getDoubleFromString(dto.latitude), getDoubleFromString(dto.longitude), getBitmapFromString(dto.photo)))
                }
                response.value = receivedCapsules
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d(TAG, "failure in getCapsulesByReceiverId statusCode = $statusCode")
            }

        })
        return response
    }

    /*
   * This Function converts the String back to Bitmap
   * */
    private fun getBitmapFromString(string: String?): Bitmap? {

        if(string.isNullOrEmpty()|| string.startsWith("null")){
            return null
        }
        val decodedString: ByteArray = Base64.decode(string, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    private fun getDoubleFromString(string: String): Double?{
        if(string.isEmpty() || string.startsWith("null")){
            return null
        }
        return string.toDouble()
    }

    /*
 * This functions converts Bitmap picture to a string which can be
 * JSONified.
 * */
    private fun getStringFromBitmap(bitmapPicture: Bitmap?): String? {
        if (bitmapPicture != null) {
            val COMPRESSION_QUALITY = 100
            val encodedImage: String
            val byteArrayBitmapStream = ByteArrayOutputStream()
            bitmapPicture.compress(
                Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
                byteArrayBitmapStream
            )
            val b: ByteArray = byteArrayBitmapStream.toByteArray()
            encodedImage = Base64.encodeToString(b, Base64.DEFAULT)
            return encodedImage
        }
        return null
    }

    private fun getReceiveCapsuleDTOsFromString(jsonString: String?): List<ReceiveCapsuleDTO> {
        val result = ArrayList<ReceiveCapsuleDTO>()


        if (jsonString == null) {
            Log.d(TAG, "Error: NO RESULT")
            return result
        }
        if (jsonString.startsWith("error")) {
            Log.d(TAG, "Error: $jsonString")
            return result
        }
        val array: JSONArray?
        try {
            array = JSONArray(jsonString)
            for (i in 0 until array.length()) {
                result.add(ReceiveCapsuleDTO(array.getJSONObject(i)))
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return result
    }
}