package com.example.didinoapp.view.fragments.didino

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.didinoapp.R
import com.example.didinoapp.data.api.ApiClient
import com.example.didinoapp.data.model.MovieAndSeriesCategoryModel
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.databinding.FragmentDidinoBinding
import com.example.didinoapp.utils.showDialogAccountForFragments
import com.example.didinoapp.utils.viewGoneAnimationForBottomNav
import com.example.didinoapp.utils.viewVisibleAnimationForBottomNav
import com.example.didinoapp.view.activities.videoplayer.MovieAndSeriesPlayerActivity
import com.example.didinoapp.view.fragments.didino.adapter.DefaultMovieAdapter
import com.example.didinoapp.view.fragments.didino.adapter.GallerySliderAdapter
import com.example.didinoapp.view.fragments.didino.adapter.SpecialMovieAdapter
import com.example.didinoapp.viewmodel.MovieAndSeriesViewModel
import com.example.didinoapp.viewmodel.MovieAndSeriesViewModelFactory
import kotlin.math.min

class DidinoFragment : Fragment() {

    private lateinit var binding: FragmentDidinoBinding
    private lateinit var movieAndSeriesViewModel: MovieAndSeriesViewModel
    private lateinit var specialMovieAdapter: SpecialMovieAdapter
    private lateinit var defaultMovieAdapter: DefaultMovieAdapter
    private lateinit var gallerySliderAdapter: GallerySliderAdapter
    private var limitForLoadData = true

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

    private var genre: String = ""
    private var genreId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDidinoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarButtonClickListener()
        openMovieCategoryList()
        setUpViewModel()
        setUpObservers()

        binding.nestedScroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            val colorAppBar = changeAlpha(
                ContextCompat.getColor(requireActivity(), R.color.main_Color2),
                (min(255, scrollY).toFloat() / 255.0f).toDouble()
            )

            val colorAppName = changeAlpha(
                ContextCompat.getColor(requireActivity(), R.color.orange),
                (min(255, scrollY).toFloat() / 255.0f).toDouble()
            )

            binding.appName.setTextColor(colorAppName)
            binding.materialToolbar.setBackgroundColor(colorAppBar)
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////


    private fun setUpViewModel() {
        movieAndSeriesViewModel = ViewModelProvider(
            this,
            MovieAndSeriesViewModelFactory(ApiClient.apiService)
        )[MovieAndSeriesViewModel::class.java]
    }

    private fun setUpObservers() {
        setData()

        movieAndSeriesViewModel.sliderLiveData.observe(viewLifecycleOwner) {
            setSlider(it)
        }

        movieAndSeriesViewModel.specialMovieLiveData.observe(viewLifecycleOwner) {
            setSpecialMovie(it)
        }
//
        movieAndSeriesViewModel.newMovieLiveData.observe(viewLifecycleOwner) {
            setNewMovie(it)
        }

        movieAndSeriesViewModel.freeMovieLiveData.observe(viewLifecycleOwner) {
            setFreeMovie(it)
        }

        movieAndSeriesViewModel.persianMovieLiveData.observe(viewLifecycleOwner) {
            setPersianSeries(it)
        }

        movieAndSeriesViewModel.foreignMovieLiveData.observe(viewLifecycleOwner) {
            setForeignSeries(it)
        }

        movieAndSeriesViewModel.actionMovieLiveData.observe(viewLifecycleOwner) {
            setActionMovie(it)
        }

        movieAndSeriesViewModel.historicalMovieLiveData.observe(viewLifecycleOwner) {
            setHistoricalMovie(it)
        }

        movieAndSeriesViewModel.romanticMovieLiveData.observe(viewLifecycleOwner) {
            setRomanticMovie(it)
        }

        movieAndSeriesViewModel.familyMovieLiveData.observe(viewLifecycleOwner) {
            setFamilyMovie(it)
        }

        movieAndSeriesViewModel.documentaryMovieLiveData.observe(viewLifecycleOwner) {
            setDocumentaryMovie(it)
        }

        movieAndSeriesViewModel.comedyMovieLiveData.observe(viewLifecycleOwner) {
            setComedyMovie(it)
        }

        movieAndSeriesViewModel.scienceMovieLiveData.observe(viewLifecycleOwner) {
            setScienceMovie(it)
        }

        movieAndSeriesViewModel.scaryMovieLiveData.observe(viewLifecycleOwner) {
            setScaryMovie(it)
        }

        movieAndSeriesViewModel.animationMovieLiveData.observe(viewLifecycleOwner) {
            setAnimationMovie(it)
        }
    }

    private fun setData() {
        if (limitForLoadData) {
            movieAndSeriesViewModel.getGalleryViewModel()
            movieAndSeriesViewModel.getSpecialViewModel()
            movieAndSeriesViewModel.getNewViewModel()
            movieAndSeriesViewModel.getFreeViewModel()
            movieAndSeriesViewModel.getPersianSeriesViewModel()
            movieAndSeriesViewModel.getForeignSeriesViewModel()
            movieAndSeriesViewModel.getActionViewModel()
            movieAndSeriesViewModel.getHistoricalViewModel()
            movieAndSeriesViewModel.getRomanticViewModel()
            movieAndSeriesViewModel.getFamilyViewModel()
            movieAndSeriesViewModel.getDocumentaryViewModel()
            movieAndSeriesViewModel.getComedyViewModel()
            movieAndSeriesViewModel.getScienceViewModel()
            movieAndSeriesViewModel.getScaryViewModel()
            movieAndSeriesViewModel.getAnimationViewModel()
            limitForLoadData = false
        }
    }

    private fun setSlider(data: List<MovieAndSeriesModel>) {
        gallerySliderAdapter =
            GallerySliderAdapter(requireContext(), data,
                IItemClickListener = { _, data -> sendDataToOtherFragments(data) },
                IItemClickListenerShowMovie = { _, data -> sendMovieDataToVideoPlayer(data) })
        binding.viewPagerID.adapter = gallerySliderAdapter
        binding.viewPagerDotsIndicator.setViewPager2(binding.viewPagerID)
    }

    private fun setSpecialMovie(data: List<MovieAndSeriesModel>) {
        specialMovieAdapter =
            SpecialMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.specialRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.specialRecyclerViewID.setHasFixedSize(true)
        binding.specialRecyclerViewID.adapter = specialMovieAdapter
    }

    private fun setNewMovie(data: List<MovieAndSeriesModel>) {
        defaultMovieAdapter =
            DefaultMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.newsRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.newsRecyclerViewID.setHasFixedSize(true)
        binding.newsRecyclerViewID.adapter = defaultMovieAdapter
    }

    private fun setFreeMovie(data: List<MovieAndSeriesModel>) {
        defaultMovieAdapter =
            DefaultMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.freeRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.freeRecyclerViewID.setHasFixedSize(true)
        binding.freeRecyclerViewID.adapter = defaultMovieAdapter
    }

    private fun setPersianSeries(data: List<MovieAndSeriesModel>) {
        specialMovieAdapter =
            SpecialMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.iranianSeriesRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.iranianSeriesRecyclerViewID.setHasFixedSize(true)
        binding.iranianSeriesRecyclerViewID.adapter = specialMovieAdapter
    }

    private fun setForeignSeries(data: List<MovieAndSeriesModel>) {
        specialMovieAdapter =
            SpecialMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.foreignSeriesRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.foreignSeriesRecyclerViewID.setHasFixedSize(true)
        binding.foreignSeriesRecyclerViewID.adapter = specialMovieAdapter
    }

    private fun setActionMovie(data: List<MovieAndSeriesModel>) {
        defaultMovieAdapter =
            DefaultMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.actionRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.actionRecyclerViewID.setHasFixedSize(true)
        binding.actionRecyclerViewID.adapter = defaultMovieAdapter
    }

    private fun setHistoricalMovie(data: List<MovieAndSeriesModel>) {
        defaultMovieAdapter =
            DefaultMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.historicalAndReligiousRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.historicalAndReligiousRecyclerViewID.setHasFixedSize(true)
        binding.historicalAndReligiousRecyclerViewID.adapter = defaultMovieAdapter
    }

    private fun setRomanticMovie(data: List<MovieAndSeriesModel>) {
        defaultMovieAdapter =
            DefaultMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.romanticRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.romanticRecyclerViewID.setHasFixedSize(true)
        binding.romanticRecyclerViewID.adapter = defaultMovieAdapter
    }

    private fun setFamilyMovie(data: List<MovieAndSeriesModel>) {
        defaultMovieAdapter =
            DefaultMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.familyRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.familyRecyclerViewID.setHasFixedSize(true)
        binding.familyRecyclerViewID.adapter = defaultMovieAdapter
    }

    private fun setDocumentaryMovie(data: List<MovieAndSeriesModel>) {
        defaultMovieAdapter =
            DefaultMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.documentaryRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.documentaryRecyclerViewID.setHasFixedSize(true)
        binding.documentaryRecyclerViewID.adapter = defaultMovieAdapter
    }

    private fun setComedyMovie(data: List<MovieAndSeriesModel>) {
        defaultMovieAdapter =
            DefaultMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.comedyRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.comedyRecyclerViewID.setHasFixedSize(true)
        binding.comedyRecyclerViewID.adapter = defaultMovieAdapter
    }

    private fun setScienceMovie(data: List<MovieAndSeriesModel>) {
        defaultMovieAdapter =
            DefaultMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.scienceFictionRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.scienceFictionRecyclerViewID.setHasFixedSize(true)
        binding.scienceFictionRecyclerViewID.adapter = defaultMovieAdapter
    }

    private fun setScaryMovie(data: List<MovieAndSeriesModel>) {
        defaultMovieAdapter =
            DefaultMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.scaryRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.scaryRecyclerViewID.setHasFixedSize(true)
        binding.scaryRecyclerViewID.adapter = defaultMovieAdapter
    }

    private fun setAnimationMovie(data: List<MovieAndSeriesModel>) {
        defaultMovieAdapter =
            DefaultMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
                sendDataToOtherFragments(data)
            })
        binding.animationRecyclerViewID.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.animationRecyclerViewID.setHasFixedSize(true)
        binding.animationRecyclerViewID.adapter = defaultMovieAdapter
    }

    private fun openMovieCategoryList() {

        binding.specialRelativeLayoutID.setOnClickListener {
            genreId = "2"
            sendDataToListFragment(genreId)
        }

        binding.newsRelativeLayoutID.setOnClickListener {
            genreId = "3"
            sendDataToListFragment(genreId)
        }

        binding.freeRelativeLayoutID.setOnClickListener {
            genreId = "4"
            sendDataToListFragment(genreId)
        }

        binding.persianRelativeLayoutID.setOnClickListener {
            genreId = "6"
            sendDataToListFragment(genreId)
        }

        binding.foreignRelativeLayoutID.setOnClickListener {
            genreId = "5"
            sendDataToListFragment(genreId)
        }

        binding.actionRelativeLayoutID.setOnClickListener {
            genreId = "7"
            sendDataToListFragment(genreId)
        }

        binding.historicalRelativeLayoutID.setOnClickListener {
            genreId = "8"
            sendDataToListFragment(genreId)
        }

        binding.romanticRelativeLayoutID.setOnClickListener {
            genreId = "9"
            sendDataToListFragment(genreId)
        }

        binding.familyRelativeLayoutID.setOnClickListener {
            genreId = "10"
            sendDataToListFragment(genreId)
        }

        binding.documentaryRelativeLayoutID.setOnClickListener {
            genreId = "11"
            sendDataToListFragment(genreId)
        }

        binding.comedyRelativeLayoutID.setOnClickListener {
            genreId = "12"
            sendDataToListFragment(genreId)
        }

        binding.scienceRelativeLayoutID.setOnClickListener {
            genreId = "13"
            sendDataToListFragment(genreId)
        }

        binding.scaryRelativeLayoutID.setOnClickListener {
            genreId = "14"
            sendDataToListFragment(genreId)
        }

        binding.animationRelativeLayoutID.setOnClickListener {
            genreId = "15"
            sendDataToListFragment(genreId)
        }
    }

    private fun sendDataToListFragment(genreId: String) {
        val action = MovieAndSeriesCategoryModel(genre, genreId, null)
        viewGoneAnimationForBottomNav(requireContext(), requireActivity())
        findNavController().navigate(
            DidinoFragmentDirections.actionDidinoFragmentToMovieListFragment(action)
        )
    }

    private fun sendMovieDataToVideoPlayer(data: String) {
        val intent = Intent(requireContext(), MovieAndSeriesPlayerActivity::class.java)
        intent.putExtra("PREVIEW_VIDEO_KEY", data)
        startActivity(intent)
    }

    private fun changeAlpha(color: Int, fraction: Double): Int {
        val red: Int = Color.red(color)
        val green: Int = Color.green(color)
        val blue: Int = Color.blue(color)
        val alpha: Int = (Color.alpha(color) * (fraction)).toInt()
        return Color.argb(alpha, red, green, blue)
    }

    private fun toolbarButtonClickListener() {

        binding.searchBtn.setOnClickListener {
            findNavController().navigate(DidinoFragmentDirections.actionDidinoFragmentToSearchFragment())
        }

        binding.accountBtn.setOnClickListener {
            showDialogAccountForFragments(requireContext())
        }
    }

    private fun sendDataToOtherFragments(data: MovieAndSeriesModel) {

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

        viewGoneAnimationForBottomNav(requireContext(), requireActivity())
        findNavController().navigate(
            DidinoFragmentDirections.actionDidinoFragmentToDetailFragment(
                allMovieData
            )
        )
    }
}