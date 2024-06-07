package com.example.hobbyapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.hobbyapp.databinding.FragmentProfileBinding
import com.example.hobbyapp.global.Global.Companion.makeAlert
import com.example.hobbyapp.model.User
import com.example.hobbyapp.viewmodel.UserViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private lateinit var viewModel:UserViewModel
    var savedUsername:String?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        var sharedFile = requireActivity().packageName
        var shared: SharedPreferences = requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        savedUsername = shared.getString(LoginFragment.EXTRA_USERNAME, "")
        savedUsername?.let {
            if (savedUsername!!.isNotEmpty()){
                Log.d("saved username", savedUsername!!)
                viewModel.fetch(savedUsername!!)
                observeViewModel()
            }else{
                Navigation.findNavController(view).navigate(ProfileFragmentDirections.actionLogout())
            }
        }

        binding.buttonSave.setOnClickListener {
            val firstname = binding.editTextFisrtname.text.toString()
            val lastname = binding.editTextLastname.text.toString()
            val password = binding.editTextPassword.text.toString()

            val newUser:User = if(TextUtils.isEmpty(password)){
                Log.d("password if", password)
                User(0,savedUsername!!,null,firstname,lastname)
            }else{
                Log.d("password else", password)
                User(0,savedUsername!!,password,firstname,lastname)

            }
            viewModel.updateUser(newUser)
            viewModel.updateSuccessLD.observe(viewLifecycleOwner, Observer {
                if(it){
                    makeAlert(view.context,"Success!", "Update profile success!")
                }else{
                    makeAlert(view.context,"ALERT!", "Update profile failed!")

                }
            })
        }

        binding.textLogout.setOnClickListener {
            val username = ""
            var sharedFile = activity?.packageName
            var shared: SharedPreferences? = activity?.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
            var editor: SharedPreferences.Editor? = shared?.edit()
            editor?.putString(LoginFragment.EXTRA_USERNAME,username)
            editor?.apply()
            val action = ProfileFragmentDirections.actionLogout()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.textUsername.text = it.username
                binding.editTextFisrtname.setText(it.firstname)
                binding.editTextLastname.setText(it.lastname)
            }
            Log.d("User", it.toString())
        })
    }
}