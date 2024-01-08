package com.example.homeworknineteen.presentation.user

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.databinding.FragmentUserBinding
import com.example.homeworknineteen.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()
    private val args: UserFragmentArgs by navArgs()
    override fun setUp() {
        viewModel.getUser(args.id)
        bindObserves()
    }

    override fun listeners() {

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
                            val user = it.responseData
                            binding.tvFirstName.text = user.firstName
                            binding.tvLastName.text = user.lastName
                            binding.tvEmail.text = user.email
                            imgManagement(user.avatar, requireContext(), binding.ivAvatar)

//                            Log.d("resultaaa", it.responseData.userList.toString())
//                            adapter.submitList(it.responseData.userList)
//                            Toast.makeText(requireContext(), it.responseData.lastName.toString(), Toast.LENGTH_SHORT).show()
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