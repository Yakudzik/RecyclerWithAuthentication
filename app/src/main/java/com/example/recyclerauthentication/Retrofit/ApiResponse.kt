package com.example.recyclerauthentication.Retrofit


import android.content.Context
import android.text.InputFilter
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerauthentication.ContentFragment
import com.example.recyclerauthentication.JsModel.JsonModelTwin
import com.example.recyclerauthentication.JsModel.JsonModelTwinItem
import com.example.recyclerauthentication.JsModel.PhoneMask
import com.example.recyclerauthentication.JsModel.ResponseFromServ
import com.example.recyclerauthentication.MainActivity
import com.example.recyclerauthentication.R
import com.example.recyclerauthentication.Recycler.RecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiResponse {
    fun getData(activity: MainActivity, context: Context) {
        GetDataApi.invoke().searchMainData().enqueue(object : retrofit2.Callback<JsonModelTwin?> {
            override fun onResponse(
                call: Call<JsonModelTwin?>,
                response: Response<JsonModelTwin?>
            ) {
                if (response.isSuccessful) {
                    val res = response.body()
                    res.let {
                        val adapter = RecyclerAdapter(res as ArrayList<JsonModelTwinItem>)
                        val recycler = activity.findViewById<RecyclerView>(R.id.recycler_cf_ID)
                        recycler?.layoutManager = LinearLayoutManager(context)
                        recycler?.adapter = adapter
                    }
                    Log.e("Success", "retrofit result is success")
                }
            }

            override fun onFailure(call: Call<JsonModelTwin?>, t: Throwable) {
                Log.e("Error", "retrofit result error")
            }
        })
    }

    fun getPhoneMasks(activity: MainActivity) {
        GetMaskApi.invoke().getPhoneMask().enqueue(object : Callback<PhoneMask?> {
            override fun onResponse(call: Call<PhoneMask?>, response: Response<PhoneMask?>) {
                if (response.isSuccessful) {
                    val result = response.body()?.mask
                    result.let {
                        var number = activity.findViewById<EditText>(R.id.number_input_af_ID)
                        var numberLength = result?.filter { it.isLetterOrDigit() }
                        number.filters = arrayOf(InputFilter.LengthFilter(numberLength!!.length))
                        number.hint = result
                    }
                }
            }

            override fun onFailure(call: Call<PhoneMask?>, t: Throwable) {
                Log.e("maska", "Ошибка получения маски телефона")
            }
        })
    }

    fun makePost(phoneNumbear: String, passWord: String, activity: MainActivity) {
        PostDataApi.invoke().registrationPost(phoneNumbear, passWord)
            .enqueue(object : Callback<ResponseFromServ?> {
                override fun onResponse(
                    call: Call<ResponseFromServ?>,
                    response: Response<ResponseFromServ?>
                ) {
                    if (response.isSuccessful) {
                        var res = response.body()?.success.toString()
                        Toast.makeText(activity,res,Toast.LENGTH_SHORT).show()
                        Log.i("response", res)
                    }else{
                        Toast.makeText(activity,"false",Toast.LENGTH_SHORT).show()
                        Log.i("response", "false")
                    }
                }

                override fun onFailure(call: Call<ResponseFromServ?>, t: Throwable) {
                    Log.e("response", "Ошибка  ")
                }
            })

    }
}