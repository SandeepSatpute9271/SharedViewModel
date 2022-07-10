package com.demo.shared_view_model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainFragment : Fragment() {

    private lateinit var nextButton: AppCompatButton
    private lateinit var inputMessageEditText: EditText
    private lateinit var navController: NavController
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        nextButton = view.findViewById(R.id.btn_next)
        inputMessageEditText = view.findViewById(R.id.et_input_message)
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        nextButton.setOnClickListener(View.OnClickListener {
            sharedViewModel.setMessage(inputMessageEditText.text.toString())
            navController.navigate(R.id.action_masterFragment_to_detailFragment)
        })
    }
}