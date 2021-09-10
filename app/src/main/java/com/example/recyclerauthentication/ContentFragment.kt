package com.example.recyclerauthentication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toolbar
import androidx.navigation.Navigation
import com.example.recyclerauthentication.Retrofit.ApiResponse


class ContentFragment : Fragment() {
    var response = ApiResponse()
    lateinit var updatePage: ImageView
    lateinit var logOutButton: ImageView
    private lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_content, container, false)

        response.getData(activity as MainActivity, requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updatePage = view.findViewById<ImageView>(R.id.update_button_toolbar_ID).apply {
            setOnClickListener {
                response.getData(activity as MainActivity, requireContext())
            }
        }
        updatePage = view.findViewById<ImageView>(R.id.logout_button_ID).apply {
            setOnClickListener {
                pref = requireActivity().getSharedPreferences("sharedP", Context.MODE_PRIVATE)
                val editor = pref.edit().putString("LOGIN_KEY", "")
                editor.apply()

                Navigation.findNavController(view).navigate(R.id.autentificationFargment)

            }

        }


    }
}