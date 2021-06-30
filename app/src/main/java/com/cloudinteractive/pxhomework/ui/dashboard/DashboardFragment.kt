package com.cloudinteractive.pxhomework.ui.dashboard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cloudinteractive.pxhomework.R
import com.cloudinteractive.pxhomework.databinding.FragmentDashboardBinding
import com.cloudinteractive.pxhomework.databinding.ItemDashboardPagerBannerBinding
import com.cloudinteractive.pxhomework.model.GetBannersResult
import com.cloudinteractive.pxhomework.ui.MainViewModel
import com.cloudinteractive.pxhomework.ui.payment.QRCodeBottomSheetFragment
import com.cloudinteractive.pxhomework.ui.viewBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val binding by viewBinding(FragmentDashboardBinding::bind)

    private val mainViewModel: MainViewModel by activityViewModels()
    private val dashBoardViewModel: DashboardViewModel by viewModels()

    private lateinit var bannerAdapter: BannerAdapter

    private val qrCodeBottomSheetFragment by lazy { QRCodeBottomSheetFragment() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (dashBoardViewModel.banners.value == null)
            dashBoardViewModel.getBanner()

        dashBoardViewModel.banners.observe(viewLifecycleOwner) { banners ->
            bannerAdapter = BannerAdapter(banners)
            binding.vpBanner.adapter = bannerAdapter
            if (binding.vpBanner.currentItem == 0)
                binding.vpBanner.setCurrentItem(bannerAdapter.getDefaultPosition(), false)
        }


        if (mainViewModel.messages.value == null)
            mainViewModel.getMessages()


        mainViewModel.messages.observe(viewLifecycleOwner) { messages ->
            val count: String = if (messages.size > 99) "99+" else messages.size.toString()
            binding.tvMessageCount.text = count
            binding.tvMessageCount.isVisible = messages.isNotEmpty()
        }



        binding.ivMail.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToMessageFragment())
        }

        binding.ivMoney.setOnClickListener {
            if (!qrCodeBottomSheetFragment.isAdded)
                qrCodeBottomSheetFragment.show(
                    parentFragmentManager,
                    QRCodeBottomSheetFragment::class.simpleName
                )
        }

//        binding.vpBanner.registerOnPageChangeCallback(
//
//        )
    }


    class BannerAdapter(private val banners: List<GetBannersResult.Banner>) :
        RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

        class ViewHolder(private val binding: ItemDashboardPagerBannerBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(banner: GetBannersResult.Banner) {
                Glide.with(binding.ivBanner)
                    .load(banner.image)
                    .into(binding.ivBanner)

                binding.tvTitle.text = banner.title
                binding.clBanner.setOnClickListener {
                    binding.clBanner.context.startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse(banner.targetUrl))
                    )
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ItemDashboardPagerBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(banners[position % banners.size])
        }

        // 沒有 banner 或只有一組 banner 不需要特別做無窮滑動處理
        override fun getItemCount(): Int = if (banners.size <= 1) banners.size else Int.MAX_VALUE


        fun getDefaultPosition(): Int {
            return if (itemCount <= 1)
                0
            else
            // 取整數起始位置
                (Int.MAX_VALUE / 2) / itemCount * itemCount
        }
    }
}