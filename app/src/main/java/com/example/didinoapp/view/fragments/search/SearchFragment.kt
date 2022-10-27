package com.example.didinoapp.view.fragments.search


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.didinoapp.R
import com.example.didinoapp.data.api.ApiClient
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.databinding.FragmentSearchBinding
import com.example.didinoapp.utils.viewGoneAnimationForBottomNav
import com.example.didinoapp.utils.viewVisibleAnimationForBottomNav
import com.example.didinoapp.view.customview.FadeInLinearLayoutManager
import com.example.didinoapp.view.fragments.search.adapter.SearchAdapter

class SearchFragment : Fragment() {


    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchViewModel: SearchViewModel

    private var movieId: Int = 0
    private lateinit var namePersian: String
    private lateinit var nameForeign: String
    private lateinit var voteAverage: String
    private lateinit var movieAbout: String
    private lateinit var movieStory: String
    private lateinit var previewVideo: String
    private lateinit var movieActors: String
    private lateinit var movieDirector: String
    private lateinit var movieProducer: String
    private lateinit var movieTheWriter: String
    private lateinit var movieProductionYear: String
    private lateinit var mainPost: String
    private lateinit var backgroundPost: String
    private lateinit var movieCategory: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        viewGoneAnimationForBottomNav(requireContext(), requireActivity())

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
            viewVisibleAnimationForBottomNav(requireContext(),requireActivity())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        search()
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////


    private fun setUpViewModel() {
        searchViewModel = ViewModelProvider(
            this,
            SearchViewModelFactory(ApiClient.apiService)
        )[SearchViewModel::class.java]
    }

    private fun search() {
        binding.searchEditTextViewID.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(
                p0: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                searchViewModel.getRepos(p0.toString().trim())
                setUpObservers()
            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.searchEditTextViewID.text.isEmpty()) {
                    binding.img.visibility = View.VISIBLE
                    binding.txt.visibility = View.VISIBLE
                    binding.searchCategoryListRecyclerViewID.visibility = View.GONE
                }
            }
        })
    }

    private fun setUpObservers() {
        val animationUtils = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        binding.searchCategoryListRecyclerViewID.visibility = View.VISIBLE
        binding.searchCategoryListRecyclerViewID.startAnimation(animationUtils)
        binding.img.visibility = View.GONE
        binding.txt.visibility = View.GONE
        searchViewModel.searchLiveData.observe(viewLifecycleOwner) {
            setUpSearchList(it)
        }
    }

    private fun setUpSearchList(data: MutableList<MovieAndSeriesModel>) {
        searchAdapter = SearchAdapter(requireContext(), IItemClickListener = { _, data ->
            sendDataToOtherFragment(data)
        })
        val anim = AnimationUtils.loadLayoutAnimation(requireContext(),R.anim.layout_animation_fall_down)
        searchAdapter.setProducts(data)
        binding.searchCategoryListRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext())
    binding.searchCategoryListRecyclerViewID.adapter = searchAdapter
}

private fun sendDataToOtherFragment(data: MovieAndSeriesModel) {
    movieId = data.id
    namePersian = data.name_persian_SE
    nameForeign = data.name_foreign_SE
    voteAverage = data.vote_average
    movieAbout = data.about
    movieStory = data.story
    previewVideo = data.preview_video
    movieActors = data.actors
    movieDirector = data.director
    movieProducer = data.producer
    movieTheWriter = data.the_writer
    movieProductionYear = data.production_year
    mainPost = data.main_posturl
    backgroundPost = data.background_posturl
    movieCategory = data.grouping_id

    val allMovieData = MovieAndSeriesModel(
        movieId,
        namePersian,
        nameForeign,
        voteAverage,
        movieAbout,
        movieStory,
        previewVideo,
        movieActors,
        movieDirector,
        movieProducer,
        movieTheWriter,
        movieProductionYear,
        mainPost,
        backgroundPost,
        movieCategory
    )
    findNavController().navigate(
        SearchFragmentDirections.actionSearchFragmentToDetailFragment(
            allMovieData
        )
    )
}
}