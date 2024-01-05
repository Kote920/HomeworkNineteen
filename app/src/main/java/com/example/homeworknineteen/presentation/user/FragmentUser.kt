package com.example.homeworknineteen.presentation.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.homeworknineteen.R
import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.databinding.FragmentUserBinding
import com.example.homeworknineteen.presentation.BaseFragment
import com.example.homeworknineteen.presentation.userList.UserListViewModel
import kotlinx.coroutines.launch

class FragmentUser : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()
    private val args: FragmentUserArgs by navArgs()
    override fun setUp() {
        viewModel.getUser(args.id)
        bindObserves()
    }

    override fun listeners() {
        TODO("Not yet implemented")
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userFlow.collect(){
                    when(it){

                        is Resource.Loading -> {
                            binding.pbLogIn.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.pbLogIn.visibility = View.GONE

//                            Log.d("resultaaa", it.responseData.userList.toString())
//                            adapter.submitList(it.responseData.userList)
                            Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Failed -> {
                            binding.pbLogIn.visibility = View.GONE
                            val errorMessage = it.message
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
                                .show()

                        }

                    }
                }
            }
        }
    }

}