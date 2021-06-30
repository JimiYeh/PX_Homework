package com.cloudinteractive.pxhomework.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.cloudinteractive.network.ApiResponse
import com.cloudinteractive.pxhomework.R
import com.cloudinteractive.pxhomework.databinding.BottomFragmentQrCodeBinding
import com.cloudinteractive.pxhomework.model.BaseResponse
import com.cloudinteractive.pxhomework.model.GetQRCodeResult
import com.cloudinteractive.pxhomework.ui.viewBinding
import com.cloudinteractive.repository.PxRepository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.coroutines.launch

class QRCodeBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var ivClose: ImageView
    lateinit var pbLoading: ProgressBar
    lateinit var ivQRCode: ImageView

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.bottom_fragment_qr_code, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivClose = view.findViewById<ImageView>(R.id.ivClose).apply {
            setOnClickListener {
                dismiss()
            }
        }

        ivQRCode = view.findViewById(R.id.ivQRCode)
        pbLoading = view.findViewById(R.id.pbLoading)
    }


    override fun onStart() {
        super.onStart()

        ivQRCode.visibility = INVISIBLE
        pbLoading.visibility = VISIBLE

        lifecycleScope.launch {
            when (val resp = PxRepository().getQRCode()) {

                is ApiResponse.ApiSuccess<BaseResponse<GetQRCodeResult>> -> {
                    Glide.with(ivQRCode)
                        .load(
                            BarcodeEncoder().encodeBitmap(
                                resp.data.result.qrCode,
                                BarcodeFormat.QR_CODE,
                                400,
                                400)
                        ).fitCenter()
                        .into(ivQRCode)



                    ivQRCode.visibility = VISIBLE
                }
            }
            pbLoading.visibility = INVISIBLE
        }
    }
}