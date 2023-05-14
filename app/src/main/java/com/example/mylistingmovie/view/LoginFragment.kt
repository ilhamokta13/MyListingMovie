package com.example.mylistingmovie.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.mylistingmovie.R
import com.example.mylistingmovie.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)


        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.btnReg.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    fun login() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passEditText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){

                    Toast.makeText(context, "Login Berhasil", Toast.LENGTH_LONG).show()
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_loginFragment_to_homeFragment)

        }else{
            Toast.makeText(context, "Coba Cek Email dan password kembali",Toast.LENGTH_LONG).show()
        }




    }
}




