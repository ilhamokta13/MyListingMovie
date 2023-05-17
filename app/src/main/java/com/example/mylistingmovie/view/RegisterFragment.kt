@file:Suppress("UnusedImport")

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
import com.example.mylistingmovie.R
import com.example.mylistingmovie.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    lateinit var binding:FragmentRegisterBinding
//    private lateinit var uservm: UserViewModel
//    private lateinit var userdb: UserDatabase
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var pref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
//        uservm = ViewModelProvider(this).get(UserViewModel::class.java)
//        userdb = UserDatabase.getDatabase(requireContext())

        binding.btnRegister.setOnClickListener {

            register()

        }

    }

    private fun register() {
        val username = binding.usernameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val pass = binding.passEditText.text.toString()
        val confirmpass = binding.confirmpassEditText.text.toString()
        val addAkun = pref.edit()
        addAkun.putString("username", username)



        if (username.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty()) {
            if (pass == confirmpass) {
                addAkun.apply()
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Navigation.findNavController(binding.root)
                            .navigate(R.id.action_registerFragment_to_loginFragment)
                    } else {
                        Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(context, "Password Tidak Sesuai", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, "Maaf Data Belum Lengkap", Toast.LENGTH_SHORT).show()
        }
    }


//            if(pass == confirmpass){
//                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
//                    if(it.isSuccessful){
//                        val getUserAuth = Firebase.auth.currentUser
//                        getUserAuth?.let {it_auth->
//                            insertDataUser(username, it_auth.email!!)
//                        }
//                        Toast.makeText(context, "Registrasi Berhasil", Toast.LENGTH_LONG).show()
//
//                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
//                    }else{
//                        Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }else{
//                Toast.makeText(context, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show()
//            }
//        }else{
//            Toast.makeText(context,"Data Anda Kurang Lengkap", Toast.LENGTH_SHORT).show()
//        }


//    private fun insertDataUser(username: String, email: String) {
//        GlobalScope.async {
//            uservm.insertDataUser(
//                User(
//                    0,
//                    email,
//                    username,
//                    null,
//                    null,
//                    null
//                )
//            )
//        }
//
//
   }










