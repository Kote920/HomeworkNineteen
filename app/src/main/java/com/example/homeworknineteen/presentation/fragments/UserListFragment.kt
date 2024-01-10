package com.example.homeworknineteen.presentation.fragments

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworknineteen.data.common.Resource
import com.example.homeworknineteen.databinding.FragmentUserListBinding
import com.example.homeworknineteen.presentation.BaseFragment
import com.example.homeworknineteen.presentation.adapters.UserListRecyclerAdapter
import com.example.homeworknineteen.presentation.viewModels.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
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

        binding.btnDelete.setOnClickListener{
            viewModel.deleteUser(adapter.removeItems())
            it.visibility = View.GONE
        }
    }

    private fun initItemRecycler(){
        adapter = UserListRecyclerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        adapter.itemOnClick = {
                openUser(it.id!!)
        }
        adapter.activateButton = {
            if(it){
                binding.btnDelete.visibility = View.VISIBLE
            }else{
                binding.btnDelete.visibility = View.GONE
            }
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

//                            d("resultaaa",it.responseData.toString())
                            adapter.submitList(it.responseData.toMutableList())
                            adapter.items = it.responseData.toMutableList()
//                            Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()
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
        val action = UserListFragmentDirections.actionUserListFragmentToUserFragment(id)
        findNavController().navigate(action)
    }



}