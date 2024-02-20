package com.maxi.petzify.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maxi.petzify.databinding.FragmentHomeBinding
import com.maxi.petzify.databinding.FragmentPosteosBinding
import com.maxi.petzify.ui.home.postsAdapter.PostsAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var postsAdapter:PostsAdapter
    private val homeViewModel : HomeViewModel by viewModels()
    private lateinit var manager:LinearLayoutManager
    private var page:Int = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        setUpRecyclerView()
        observePosts()
        initListers()
    }

    private fun initListers() {
        binding.rvPostsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (manager.findLastVisibleItemPosition() == manager.itemCount - 1) {
                    page += 1
                    homeViewModel.getAllPosts(page)
                }
            }
        })
    }

    private fun observePosts(){
        lifecycleScope.launch(Dispatchers.IO) {
            homeViewModel.getAllPosts(page)
            homeViewModel._postsListValue.collectLatest {
                withContext(Dispatchers.Main){
                    if(it.isLoading){
                        Logger.d("is loading")
                    }else{
                        if(it.error.isNotBlank()){
                            Toast.makeText(
                                requireContext(),
                                it.error,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        else{
                            postsAdapter.setData(it.postsList)
                        }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView(){
        manager = LinearLayoutManager(requireContext())
        postsAdapter = PostsAdapter()
        binding.rvPostsList.apply {
            adapter = postsAdapter
            layoutManager = manager
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}