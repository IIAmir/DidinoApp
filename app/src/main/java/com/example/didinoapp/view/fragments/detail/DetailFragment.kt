package com.example.didinoapp.view.fragments.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.didinoapp.R
import com.example.didinoapp.data.api.ApiClient
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.databinding.FragmentDetailBinding
import com.example.didinoapp.utils.viewGoneAnimationForBottomNav
import com.example.didinoapp.utils.viewVisibleAnimationForBottomNav
import com.example.didinoapp.view.activities.videoplayer.MovieAndSeriesPlayerActivity
import com.example.didinoapp.view.fragments.detail.adapter.ProposalAdapter
import com.example.didinoapp.view.fragments.didino.DidinoFragment
import com.example.didinoapp.view.fragments.didino.adapter.DefaultMovieAdapter
import com.example.didinoapp.viewmodel.MovieAndSeriesViewModel
import com.example.didinoapp.viewmodel.MovieAndSeriesViewModelFactory
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class DetailFragment : Fragment() {

    private lateinit var movieAndSeriesViewModel: MovieAndSeriesViewModel
    private lateinit var binding: FragmentDetailBinding
    private lateinit var proposalMovieAdapter: ProposalAdapter
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        setUpViewModel()

        binding.apply {

            movieNamePersian.text = args.movieDetail.name_persian_SE
            movieNameForeign.text = args.movieDetail.name_foreign_SE
            movieAbout.text = args.movieDetail.about
            movieVote.text = args.movieDetail.vote_average
            productionYear.text = args.movieDetail.production_year
            movieDescription.text = args.movieDetail.story
            movieMovieCast.text = args.movieDetail.actors
            movieDirector.text = args.movieDetail.director
            movieProducer.text = args.movieDetail.producer
            movieTheWriter.text = args.movieDetail.the_writer

            Picasso.get().load(args.movieDetail.background_posturl).fit().into(vidMovieDetail)
            Picasso.get().load(args.movieDetail.main_posturl).fit().into(imageViewMain)
            Picasso.get().load(args.movieDetail.background_posturl).fit().into(imageViewBackground)
            when (args.movieDetail.grouping_id) {
                "1" -> setUpGalleryObservers()
                "2" -> setUpSpecialObservers()
                "3" -> setUpNewObservers()
                "4" -> setUpFreeObservers()
                "6" -> setUpPersianObservers()
                "5" -> setUpForeignObservers()
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

            binding.materialToolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
                viewVisibleAnimationForBottomNav(requireContext(),requireActivity())
            }
        }

        binding.vidMovieDetail.setOnClickListener {
            val intent = Intent(requireContext(), MovieAndSeriesPlayerActivity::class.java)
            intent.putExtra("PREVIEW_VIDEO_KEY", args.movieDetail.preview_video)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        viewVisibleAnimationForBottomNav(requireContext(),requireActivity())
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////


    private fun setUpViewModel() {
        movieAndSeriesViewModel = ViewModelProvider(
            this,
            MovieAndSeriesViewModelFactory(ApiClient.apiService)
        )[MovieAndSeriesViewModel::class.java]
    }

    private fun setUpGalleryObservers() {
        movieAndSeriesViewModel.getGalleryViewModel()
        movieAndSeriesViewModel.sliderLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpSpecialObservers() {
        movieAndSeriesViewModel.getSpecialViewModel()
        movieAndSeriesViewModel.specialMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpNewObservers() {
        movieAndSeriesViewModel.getNewViewModel()
        movieAndSeriesViewModel.newMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpFreeObservers() {
        movieAndSeriesViewModel.getFreeViewModel()
        movieAndSeriesViewModel.freeMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpPersianObservers() {
        movieAndSeriesViewModel.getPersianSeriesViewModel()
        movieAndSeriesViewModel.persianMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpForeignObservers() {
        movieAndSeriesViewModel.getForeignSeriesViewModel()
        movieAndSeriesViewModel.foreignMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpActionObservers() {
        movieAndSeriesViewModel.getActionViewModel()
        movieAndSeriesViewModel.actionMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpHistoricalObservers() {
        movieAndSeriesViewModel.getHistoricalViewModel()
        movieAndSeriesViewModel.historicalMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpRomanticObservers() {
        movieAndSeriesViewModel.getRomanticViewModel()
        movieAndSeriesViewModel.romanticMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpFamilyObservers() {
        movieAndSeriesViewModel.getFamilyViewModel()
        movieAndSeriesViewModel.familyMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpDocumentaryObservers() {
        movieAndSeriesViewModel.getDocumentaryViewModel()
        movieAndSeriesViewModel.documentaryMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpComedyObservers() {
        movieAndSeriesViewModel.getComedyViewModel()
        movieAndSeriesViewModel.comedyMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpScienceObservers() {
        movieAndSeriesViewModel.getScienceViewModel()
        movieAndSeriesViewModel.scienceMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpScaryObservers() {
        movieAndSeriesViewModel.getScaryViewModel()
        movieAndSeriesViewModel.scaryMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setUpAnimationObservers() {
        movieAndSeriesViewModel.getAnimationViewModel()
        movieAndSeriesViewModel.animationMovieLiveData.observe(viewLifecycleOwner) {
            setSocialMovie(it)
        }
    }

    private fun setSocialMovie(data: List<MovieAndSeriesModel>) {

        proposalMovieAdapter =
            ProposalAdapter(requireContext(), IItemClickListener = { _, data ->
                binding.apply {
                    detailAnimationFadeOut()
                    detailAnimationFadeIn(data)
                }
            })

        binding.proposalRecyclerViewID.layoutManager = object : LinearLayoutManager(requireContext(),LinearLayout.HORIZONTAL,false) {
            override fun supportsPredictiveItemAnimations(): Boolean {
                return true
            }
        }
        proposalMovieAdapter.setProducts(data)
        (binding.proposalRecyclerViewID.layoutManager as LinearLayoutManager).supportsPredictiveItemAnimations()
        binding.proposalRecyclerViewID.setHasFixedSize(true)
        binding.proposalRecyclerViewID.adapter = proposalMovieAdapter
    }

    private fun detailAnimationFadeIn(data: MovieAndSeriesModel) {
        val animationFadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        binding.apply {
            movieNamePersian.text = data.name_persian_SE
            movieNamePersian.startAnimation(animationFadeIn)
            movieNameForeign.text = data.name_foreign_SE
            movieNameForeign.startAnimation(animationFadeIn)
            movieAbout.text = data.about
            movieAbout.startAnimation(animationFadeIn)
            movieVote.text = data.vote_average
            movieVote.startAnimation(animationFadeIn)
            productionYear.text = data.production_year
            productionYear.startAnimation(animationFadeIn)
            movieDescription.text = data.story
            movieDescription.startAnimation(animationFadeIn)
            movieMovieCast.text = data.actors
            movieMovieCast.startAnimation(animationFadeIn)
            movieDirector.text = data.director
            movieDirector.startAnimation(animationFadeIn)
            movieProducer.text = data.producer
            movieProducer.startAnimation(animationFadeIn)
            movieTheWriter.text = data.the_writer
            movieTheWriter.startAnimation(animationFadeIn)

            Picasso.get().load(data.background_posturl).fit().into(vidMovieDetail)
            vidMovieDetail.startAnimation(animationFadeIn)
            Picasso.get().load(data.main_posturl).fit().into(imageViewMain)
            imageViewMain.startAnimation(animationFadeIn)
            Picasso.get().load(data.background_posturl).fit().into(imageViewBackground)
            imageViewBackground.startAnimation(animationFadeIn)
        }
    }

    private fun detailAnimationFadeOut() {
        val animationFadeOut = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out)
        binding.apply {
            movieNamePersian.text = ""
            movieNamePersian.startAnimation(animationFadeOut)
            movieNameForeign.text = ""
            movieNameForeign.startAnimation(animationFadeOut)
            movieAbout.text = ""
            movieAbout.startAnimation(animationFadeOut)
            movieVote.text = ""
            movieVote.startAnimation(animationFadeOut)
            productionYear.text = ""
            productionYear.startAnimation(animationFadeOut)
            movieDescription.text = ""
            movieDescription.startAnimation(animationFadeOut)
            movieMovieCast.text = ""
            movieMovieCast.startAnimation(animationFadeOut)
            movieDirector.text = ""
            movieDirector.startAnimation(animationFadeOut)
            movieProducer.text = ""
            movieProducer.startAnimation(animationFadeOut)
            movieTheWriter.text = ""
            movieTheWriter.startAnimation(animationFadeOut)

            vidMovieDetail.startAnimation(animationFadeOut)
            imageViewMain.startAnimation(animationFadeOut)
            imageViewBackground.startAnimation(animationFadeOut)

            nestedScrollViewDetail.fullScroll(ScrollView.FOCUS_UP)
        }
    }
}