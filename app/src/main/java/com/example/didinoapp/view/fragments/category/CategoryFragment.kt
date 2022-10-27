package com.example.didinoapp.view.fragments.category

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.didinoapp.data.api.ApiClient
import com.example.didinoapp.data.model.MovieAndSeriesCategoryModel
import com.example.didinoapp.databinding.FragmentCategoryBinding
import com.example.didinoapp.utils.showDialogAccountForFragments
import com.example.didinoapp.utils.viewGoneAnimationForBottomNav
import com.example.didinoapp.utils.viewVisibleAnimationForBottomNav
import com.example.didinoapp.view.fragments.category.adapter.CategoryRecyclerViewAdapter
import com.example.didinoapp.viewmodel.MovieAndSeriesCategoryViewModel
import com.example.didinoapp.viewmodel.MovieAndSeriesCategoryViewModelFactory

class CategoryFragment : Fragment() {


    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryAdapter: CategoryRecyclerViewAdapter
    private lateinit var categoryViewModel: MovieAndSeriesCategoryViewModel
    private var limitForLoadData = true

    private lateinit var genre: String
    private lateinit var genreId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)

        binding.searchBtn.setOnClickListener {
            findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToSearchFragment())
        }

        binding.accountBtn.setOnClickListener {
            showDialogAccountForFragments(requireContext())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpObservers()
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////


    private fun setUpViewModel() {
        categoryViewModel = ViewModelProvider(
            this,
            MovieAndSeriesCategoryViewModelFactory(ApiClient.apiService)
        ).get(MovieAndSeriesCategoryViewModel::class.java)
    }


    private fun setUpObservers() {
        setData()

        categoryViewModel.movieAndSeriesCategoryLiveData.observe(viewLifecycleOwner) {
            setMovieCategory(it)
        }
    }

    private fun setData() {
        if (limitForLoadData) {
            categoryViewModel.getCategory()
            limitForLoadData = false
        }
    }

    private fun setMovieCategory(data: List<MovieAndSeriesCategoryModel>) {
        categoryAdapter =
            CategoryRecyclerViewAdapter(requireContext(), data, IItemClickListener = { _, data ->

                genre = data.genre
                genreId = data.genre_id
                val action = MovieAndSeriesCategoryModel(genre, genreId, null)
                viewGoneAnimationForBottomNav(requireContext(),requireActivity())
                findNavController().navigate(
                    CategoryFragmentDirections.actionCategoryFragmentToMovieListFragment(action)
                )
            })
        binding.categoryRecyclerViewID.layoutManager = LinearLayoutManager(requireContext())
        binding.categoryRecyclerViewID.setHasFixedSize(true)
        binding.categoryRecyclerViewID.adapter = categoryAdapter
    }
}