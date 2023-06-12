package com.example.studyapp.features.profile.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scratchjrcourse.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.Date

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            emailTextView.text = auth.currentUser?.email
            createdAtTextView.text =
                Date(auth.currentUser?.metadata?.creationTimestamp ?: 0).toString()
        }

        binding.logoutButton.setOnClickListener {
            Toast.makeText(activity, "Выход произошел успешно!", Toast.LENGTH_SHORT).show()
            auth.signOut()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToAuthFragment())
        }
    }


}