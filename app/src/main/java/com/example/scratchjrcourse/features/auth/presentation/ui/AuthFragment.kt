package com.example.studyapp.features.auth.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scratchjrcourse.MainActivity
import com.example.scratchjrcourse.R
import com.example.scratchjrcourse.databinding.FragmentAuthBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AuthFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()

//        val navGraph = findNavController().navInflater.inflate(R.navigation.nav_main)
        val currentUser = auth.currentUser

        if (currentUser != null) {
            findNavController().navigate(R.id.action_authFragment_to_unitsListFragment)
//            navGraph.setStartDestination(R.id.unitsListFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToTasks()
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    Toast.makeText(
                        activity,
                        "Регистрация прошла успешно! Нажмите на кнопку входа",
                        Toast.LENGTH_SHORT
                    ).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(activity, "Произошла ошибка!", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    Toast.makeText(activity, "Вход произошел успешно!", Toast.LENGTH_SHORT).show()
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(activity, "Произошла ошибка!", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
    }


    companion object {
        private const val TAG = "EmailPassword"
    }

    private fun goToTasks() {

        with(binding) {
            signInButton.setOnClickListener {
                val email = emailTextInput.editText?.text?.trim().toString()
                val password = passwordTextInput.editText?.text?.trim().toString()
                signIn(email, password)
                findNavController().navigate(R.id.action_authFragment_to_unitsListFragment)
            }

            signUpButton.setOnClickListener {

                val email = emailTextInput.editText?.text?.trim().toString()
                val password = passwordTextInput.editText?.text?.trim().toString()
                createAccount(email, password)
            }
        }
    }


}