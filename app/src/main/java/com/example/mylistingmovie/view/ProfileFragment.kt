@file:Suppress("RedundantNullableReturnType", "UnusedImport", "UnusedImport", "UnusedImport",
    "UnusedImport", "UnusedImport", "UnusedImport"
)

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
import com.example.mylistingmovie.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
 class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
     lateinit var firebaseAuth: FirebaseAuth
     lateinit var pref : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        val getUser = pref.getString("username", "")
        binding.usernameUpText.setText(getUser)

        val getNama = pref.getString("nama", "")
        binding.namaUpText.setText(getNama)

        val getTgl = pref.getString("tgl", "")
        binding.tanggalUpText.setText(getTgl)

        val getAlamat = pref.getString("alamat", "")
        binding.alamatUpText.setText(getAlamat)




        binding.btnUpdate.setOnClickListener {
            val username = binding.usernameUpText.text.toString()
            val nama = binding.namaUpText.text.toString()
            val ttl = binding.tanggalUpText.text.toString()
            val alamat = binding.alamatUpText.text.toString()


            val adduser = pref.edit()
            adduser.putString("username", username)
            adduser.putString("nama", nama)
            adduser.putString("ttl", ttl)
            adduser.putString("alamat", alamat)
            adduser.apply()
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()
            Toast.makeText(context, "Done Update", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(binding.root).navigate(R.id.action_profileFragment_to_homeFragment)
        }


            binding.btnLogout.setOnClickListener {
                firebaseAuth = FirebaseAuth.getInstance()
                firebaseAuth.signOut()
                val addUser = pref.edit()
                addUser.remove("nama")
                addUser.remove("tgl")
                addUser.remove("alamat")
                addUser.apply()
                Toast.makeText(activity, "user berhasil logout", Toast.LENGTH_LONG).show()
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_profileFragment_to_loginFragment)
            }





    }


}