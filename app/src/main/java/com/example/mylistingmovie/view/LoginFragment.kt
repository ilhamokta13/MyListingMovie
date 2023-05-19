@file:Suppress("SpellCheckingInspection")

package com.example.mylistingmovie.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mylistingmovie.R
import com.example.mylistingmovie.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var pref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)



        binding.login.setOnClickListener {
            login()
        }

        binding.register.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnCrash.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }
    }


    private fun login() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passEditText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(context,"Login Berhasil", Toast.LENGTH_LONG).show()
                    Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_homeFragment)
                }else{
                    Toast.makeText(context, "Coba Cek Email dan password kembali", Toast.LENGTH_LONG).show()
                }
            }
        }


    }
}




