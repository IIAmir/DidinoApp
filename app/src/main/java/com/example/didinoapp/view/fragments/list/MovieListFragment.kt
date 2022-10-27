package com.example.didinoapp.view.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.didinoapp.data.api.ApiClient
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.databinding.FragmentMovieListBinding
import com.example.didinoapp.utils.viewGoneAnimationForBottomNav
import com.example.didinoapp.utils.viewVisibleAnimationForBottomNav
import com.example.didinoapp.view.fragments.didino.adapter.DefaultMovieAdapter
import com.example.didinoapp.view.fragments.list.adapter.MovieListPagerAdapter
import com.example.didinoapp.viewmodel.MovieAndSeriesViewModel
import com.example.didinoapp.viewmodel.MovieAndSeriesViewModelFactory
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {


    private lateinit var movieAndSeriesViewModel: MovieAndSeriesViewModel
    private lateinit var defaultMovieAdapter: DefaultMovieAdapter
    private val args by navArgs<MovieListFragmentArgs>()
    private lateinit var binding: FragmentMovieListBinding
    var limitForLoadData = true

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
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        setUpViewModel()

        binding.apply {
            setData()
            when (args.movieDetail.genre_id) {
                "16" -> setUpAllSeriesObservers()
                "2" -> setUpSpecialObservers()
                "1" -> setUpGalleryObservers()
                "3" -> setUpNewObservers()
                "4" -> setUpFreeObservers()
                "5" -> setUpForeignObservers()
                "6" -> setUpPersianObservers()
                "7" -> setUpActionObservers()
                "8" -> setUpHistoricalObservers()
                "9" -> setUpRomanticObservers()
                "10" -> setUpFamilyObservers()
                "11" -> setUpDocumentaryObservers()
                "12" -> setUpComedyObservers()
                "13" -> setUpScienceObservers()
                "14" -> setUpScaryObservers()
                "15" -> setUpAnimationObservers()
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
            viewVisibleAnimationForBottomNav(requireContext(),requireActivity())
        }

        return binding.root
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////


    private fun setData(){
        if (limitForLoadData){
            movieAndSeriesViewModel.getAllMoviesPersianViewModel()
            movieAndSeriesViewModel.getGalleryViewModel()
            movieAndSeriesViewModel.getAllSeriesViewModel()
            movieAndSeriesViewModel.getSpecialViewModel()
            movieAndSeriesViewModel.getNewViewModel()
            movieAndSeriesViewModel.getFreeViewModel()
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

    private fun setUpViewModel() {
        movieAndSeriesViewModel = ViewModelProvider(
            this,
            MovieAndSeriesViewModelFactory(ApiClient.apiService)
        )[MovieAndSeriesViewModel::class.java]
    }

    private fun setUpGalleryObservers(){
        binding.nameCategory.text = "گالری دیدینو"
        movieAndSeriesViewModel.allMovieAndSeriesLiveData.observe(viewLifecycleOwner){
            setCategoryList(it)
        }
    }

    private fun setUpAllSeriesObservers() {
        binding.nameCategory.text = "سریال"
        movieAndSeriesViewModel.allSeriesLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setUpSpecialObservers() {
        binding.nameCategory.text = "ویژه"
        movieAndSeriesViewModel.specialMovieLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setUpNewObservers() {
        binding.nameCategory.text = "تازه های دیدینو"
        movieAndSeriesViewModel.newMovieLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setUpFreeObservers() {
        binding.nameCategory.text = "تماشای رایگان"
        movieAndSeriesViewModel.freeMovieLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setUpForeignObservers() {
        binding.nameCategory.text = "خارجی"
        movieAndSeriesViewModel.foreignMovieLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setUpPersianObservers() {
        binding.nameCategory.text = "ایرانی"
        movieAndSeriesViewModel.allMoviePersianLiveData.observe(viewLifecycleOwner){
            setCategoryList(it)
        }
    }

    private fun setUpActionObservers() {
        binding.nameCategory.text = "اکشن"
        movieAndSeriesViewModel.actionMovieLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setUpHistoricalObservers() {
        binding.nameCategory.text = "تاریخی | مذهبی"
        movieAndSeriesViewModel.historicalMovieLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setUpRomanticObservers() {
        binding.nameCategory.text = "عاشقانه"
        movieAndSeriesViewModel.romanticMovieLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setUpFamilyObservers() {
        binding.nameCategory.text = "خانوادگی"
        movieAndSeriesViewModel.familyMovieLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setUpDocumentaryObservers() {
        binding.nameCategory.text = "مستند"
        movieAndSeriesViewModel.documentaryMovieLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setUpComedyObservers() {
        binding.nameCategory.text = "کمدی"
        movieAndSeriesViewModel.comedyMovieLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setUpScienceObservers() {
        binding.nameCategory.text = "علمی تخیلی"
        movieAndSeriesViewModel.scienceMovieLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setUpScaryObservers() {
        binding.nameCategory.text = "وحشت"
        movieAndSeriesViewModel.scaryMovieLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setUpAnimationObservers() {
        binding.nameCategory.text = "انیمیشن"
        movieAndSeriesViewModel.animationMovieLiveData.observe(viewLifecycleOwner) {
            setCategoryList(it)
        }
    }

    private fun setCategoryList(data: List<MovieAndSeriesModel>) {

        defaultMovieAdapter =
            DefaultMovieAdapter(requireContext(), data, IItemClickListener = { _, data ->
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
                    MovieListFragmentDirections.actionMovieListFragmentToDetailFragment(
                        allMovieData
                    )
                )
            })
        binding.movieAndSeriesCategory.layoutManager = GridLayoutManager(requireContext(),3)
        binding.movieAndSeriesCategory.adapter = defaultMovieAdapter
    }
}