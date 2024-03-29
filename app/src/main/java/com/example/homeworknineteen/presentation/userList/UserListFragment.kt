package com.example.homeworknineteen.presentation.userList

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworknineteen.R
import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.databinding.FragmentUserListBinding
import com.example.homeworknineteen.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UserListFragment : BaseFragment<FragmentUserListBinding>(FragmentUserListBinding::inflate){

    private val viewModel: UserListViewModel by viewModels()
    private lateinit var adapter: UserListRecyclerAdapter

    override fun setUp() {
        viewModel.getList()
        initItemRecycler()
        bindObserves()
    }

    override fun listeners() {
    }

    private fun initItemRecycler(){
        adapter = UserListRecyclerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        adapter.itemOnClick = {
                openUser(it.id!!)
        }

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userListFlow.collect(){
                    when(it){

                        is Resource.Loading -> {
                            binding.pbLogIn.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.pbLogIn.visibility = View.GONE

                            d("resultaaa",it.responseData.userList.toString())
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

    private fun openUser(id: Int) {
        val action = UserListFragmentDirections.actionUserListFragmentToFragmentUser(id)
        findNavController().navigate(action)
    }


}