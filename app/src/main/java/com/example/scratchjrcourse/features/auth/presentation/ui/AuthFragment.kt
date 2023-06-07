package com.example.studyapp.features.auth.presentation.ui


//class AuthFragment : Fragment() {
//
////    private lateinit var auth: FirebaseAuth
////    private lateinit var binding: FragmentAuthBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        auth = Firebase.auth
//    }
//
//    override fun onStart() {
//        super.onStart()
////        val currentUser = auth.currentUser
//
////        if (currentUser != null) {
////            findNavController().navigate(R.id.action_authFragment_to_tasksFragment)
////        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
////        binding = FragmentAuthBinding.inflate(layoutInflater)
////        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        goToTasks()
//    }
//
//    private fun createAccount(email: String, password: String) {
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(requireActivity()) { task ->
//                if (task.isSuccessful) {
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        context,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                    updateUI(null)
//                }
//            }
//    }
//
//    private fun signIn(email: String, password: String) {
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(requireActivity()) { task ->
//                if (task.isSuccessful) {
//                    Log.d(TAG, "signInWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    Log.w(TAG, "signInWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        context,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                    updateUI(null)
//                }
//            }
//    }
//
//    private fun updateUI(user: FirebaseUser?) {
//    }
//
//
//    companion object {
//        private const val TAG = "EmailPassword"
//    }
//
//    private fun goToTasks() {
//        with(binding) {
//            signInButton.setOnClickListener {
//                val email = emailTextInput.editText?.text?.trim().toString()
//                val password = passwordTextInput.editText?.text?.trim().toString()
//                signIn(email, password)
//                findNavController().navigate(R.id.action_authFragment_to_tasksFragment)
//            }
//
//            signUpButton.setOnClickListener {
//
//                val email = emailTextInput.editText?.text?.trim().toString()
//                val password = passwordTextInput.editText?.text?.trim().toString()
//                createAccount(email, password)
//            }
//        }
//    }
//
//}