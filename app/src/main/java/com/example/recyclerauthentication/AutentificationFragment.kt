package com.example.recyclerauthentication

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.navigation.Navigation
import com.example.recyclerauthentication.Retrofit.ApiResponse
import kotlinx.android.synthetic.main.fragment_autentification_fargment.*


class AutentificationFragment : Fragment() {
    private var logInButton: Button? = null
    val response = ApiResponse()
    val supportClass = SupportClass()
    var editNumberField: EditText? = null
    var editPassField: EditText? = null
    lateinit var checkBox: CheckBox
      lateinit var pref: SharedPreferences
    lateinit  var test:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_autentification_fargment, container, false)


        pref = requireActivity().getSharedPreferences("sharedP", Context.MODE_PRIVATE)
          test = pref.getString("LOGIN_KEY"," ").toString()



        editNumberField = view.findViewById<EditText>(R.id.number_input_af_ID) as EditText
        editPassField = view.findViewById<EditText>(R.id.pass_input_af_ID) as EditText
        checkBox = view.findViewById<CheckBox>(R.id.checkbox_af_ID) as CheckBox
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (test!!.isNotEmpty()){
            Navigation.findNavController(view).navigate(R.id.contentFragment)
            supportClass.makeToast(requireContext(),test)
        }else{
            if (!supportClass.isOnline(requireContext())) {
                supportClass.makeToast(requireContext(), "Ethernet connection is lost")
                ethernet_connection_af_ID.setText(R.string.connectionStatusOffline)
                ethernet_connection_af_ID.setTextColor(Color.RED)

                logInButton = view.findViewById<Button>(R.id.login_button_af_ID).apply {
                    setOnClickListener {
                        supportClass.makeToast(requireContext(), "Ethernet connection is lost")
                    }
                }
            } else {
                ethernet_connection_af_ID.setText(R.string.connectionStatusOnline)
                ethernet_connection_af_ID.setTextColor(Color.GREEN)

                response.getPhoneMasks(activity as MainActivity)

                logInButton = view.findViewById<Button>(R.id.login_button_af_ID).apply {
                    setOnClickListener {

                        if (check(context)) {
                            if (checkBox.isChecked) {
                                saveData()
                                Navigation.findNavController(view).navigate(R.id.contentFragment)
                            } else
                                Navigation.findNavController(view).navigate(R.id.contentFragment)
                        }
                    }
                }
            }
        }

    }

    private fun check(context: Context): Boolean {
        if (supportClass.checkPhone(editNumberField?.text.toString())) {
            if (supportClass.checkPassword(editPassField?.text.toString())) {
                response.makePost(
                    editNumberField?.text.toString(),
                    editPassField?.text.toString(),
                    activity as MainActivity
                )
                return true
            } else {
                supportClass.makeToast(context, "Wrong Password")
                return false
            }
        } else {
            supportClass.makeToast(context, "Wrong Phone number")
            return false
        }
    }

    fun saveData() {
        pref = requireActivity().getSharedPreferences("sharedP", Context.MODE_PRIVATE)
        val editor = pref.edit().putString("LOGIN_KEY", editNumberField!!.text.toString())
        editor.apply()
    }
}
