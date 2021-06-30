package com.cloudinteractive.pxhomework.ui.message

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.cloudinteractive.pxhomework.R
import com.cloudinteractive.pxhomework.databinding.FragmentMessageBinding
import com.cloudinteractive.pxhomework.model.GetMessagesResult
import com.cloudinteractive.pxhomework.ui.MainViewModel
import com.cloudinteractive.pxhomework.ui.viewBinding

class MessageFragment : Fragment(R.layout.fragment_message) {

    private val binding by viewBinding(FragmentMessageBinding::bind)
    private val mainViewModel: MainViewModel by activityViewModels()

    private var isSelectMode = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val messageEpoxyController = MessagesEpoxyController(this::onMessageItemClick)

        binding.ervMessages.setController(messageEpoxyController)
        mainViewModel.messages.observe(viewLifecycleOwner) { messages ->
            messageEpoxyController.setMessages(messages)
        }

        binding.ivBack.setOnClickListener {
            if (isSelectMode) {
                isSelectMode = false
                binding.btnDelete.visibility = GONE
                messageEpoxyController.setSelectMode(isSelectMode)
            } else
                findNavController().popBackStack()
        }


        binding.tvEdit.setOnClickListener {
            if (!isSelectMode) {
                isSelectMode = true
                binding.btnDelete.visibility = VISIBLE
                messageEpoxyController.setSelectMode(isSelectMode)
            }
        }

        binding.btnDelete.setOnClickListener {
            mainViewModel.deleteMessages(messageEpoxyController.getCheckList())
            messageEpoxyController.clearCheckSet()
        }

    }


    private fun onMessageItemClick(message: GetMessagesResult.Message) {
        // TODO: 2021/6/30 如果需要新增點擊後的動作
    }
}