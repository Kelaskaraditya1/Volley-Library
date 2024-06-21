package com.starkindustries.volleylibrary
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.starkindustries.volleylibrary.Data.UserInfo
import com.starkindustries.volleylibrary.Data.UserInfoItem
import com.starkindustries.volleylibrary.databinding.ActivityMainBinding
import org.json.JSONObject
import java.lang.reflect.Method
import java.util.Arrays
import java.util.jar.Manifest
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    val URL="https://api.github.com/users"
    val URL2="https://api.github.com/users/mojombo"
    lateinit var userinfo:UserInfo
    var userInfoItem= arrayOf<UserInfoItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
//        binding.response.setOnClickListener(){
//            if(ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.INTERNET)==PackageManager.PERMISSION_GRANTED)
//            {
//                userinfo= UserInfo()
//                var request:StringRequest = StringRequest(Request.Method.GET,URL, Response.Listener
//                {
//                    var gsonBuilder:GsonBuilder = GsonBuilder()
//                    var gson:Gson=gsonBuilder.create()
//                    userInfoItem = gson.fromJson(it, Array<UserInfoItem>::class.java)
//                    userInfoItem.forEach {
//                        userinfo.add(it)
//                    }
//                    Log.d("response","The response is:"+userinfo.toString().trim())
//                }, Response.ErrorListener
//                {
//                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
//                })
//                val requestQueue:RequestQueue= Volley.newRequestQueue(applicationContext)
//                requestQueue.add(request)
//            }
//            else
//            {
//                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.INTERNET),101)
//            }
//
//        }
        binding.response.setOnClickListener()
        {
            var request:StringRequest = StringRequest(Request.Method.GET,URL,
                Response.Listener
                {
                Log.d("response",it.get(0).toString())
                },
                Response.ErrorListener
                {
                    Log.d("errorListner","The error is:"+it.toString())
                })
            var requestQueue:RequestQueue = Volley.newRequestQueue(applicationContext)
            requestQueue.add(request)
        }
        binding.arrayRequest.setOnClickListener(){
            var arrayRequest:JsonArrayRequest = JsonArrayRequest(URL,
                Response.Listener
            {
                for(i in 0..it.length()-1)
                {
                    var jaonObject=it.getJSONObject(i)
                    Log.d("response","The name is:"+jaonObject.get("login"))
                }
            }, Response.ErrorListener
                {
                    Log.d("ErrorListner","The Error is:"+it.message.toString().trim())
                })
            var requestQueue:RequestQueue = Volley.newRequestQueue(applicationContext)
            requestQueue.add(arrayRequest)
        }
        binding.arrayObject.setOnClickListener(){
            var request:JsonObjectRequest= JsonObjectRequest(URL2,
                Response.Listener
                {
//                for(i in 0..it.length()-1)
                    Log.d("response","The array object iis:"+it.get("login"))
                },Response.ErrorListener
                {

                })
            var requestQueue:RequestQueue = Volley.newRequestQueue(applicationContext)
            requestQueue.add(request)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}