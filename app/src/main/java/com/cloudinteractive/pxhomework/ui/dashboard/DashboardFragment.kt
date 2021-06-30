package com.cloudinteractive.pxhomework.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cloudinteractive.pxhomework.R
import com.cloudinteractive.pxhomework.databinding.FragmentDashboardBinding
import com.cloudinteractive.pxhomework.ui.viewBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val binding by viewBinding(FragmentDashboardBinding::bind)

    private val dashBoardViewModel by viewModels<DashboardViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashBoardViewModel.getBanner()
        dashBoardViewModel.banners.observe(viewLifecycleOwner) { banners ->

        }
    }
}