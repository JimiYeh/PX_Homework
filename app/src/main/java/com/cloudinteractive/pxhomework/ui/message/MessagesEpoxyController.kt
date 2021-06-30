package com.cloudinteractive.pxhomework.ui.message

import com.airbnb.epoxy.*
import com.cloudinteractive.pxhomework.model.GetMessagesResult

class MessagesEpoxyController(
    private val onMessageItemClick: (GetMessagesResult.Message) -> Unit,
) : EpoxyController() {



    private var messages = listOf<GetMessagesResult.Message>()
    private var isSelectMode = false

    // 紀錄已經選擇的訊息
    private val checkSet = mutableSetOf<GetMessagesResult.Message>()


    override fun buildModels() {

        messages.forEach { message ->
            messageItem {
                id(message.ts)
                message(message)
                checked(this@MessagesEpoxyController.isChecked(message))
                selectMode(this@MessagesEpoxyController.isSelectMode)
                onMessageItemClick(this@MessagesEpoxyController.onMessageItemClick)
                onCheckChange(this@MessagesEpoxyController::onCheckChange)
            }
        }
    }



    fun setMessages(messages: List<GetMessagesResult.Message>) {
        this.messages = messages
        requestModelBuild()
    }

    private fun isChecked(message: GetMessagesResult.Message) = checkSet.contains(message)

    private fun onCheckChange(message: GetMessagesResult.Message, isChecked: Boolean) {
        if (isChecked)
            checkSet.add(message)
        else
            checkSet.remove(message)
    }

    fun setSelectMode(enabled: Boolean) {
        isSelectMode = enabled

        if (!enabled)
            checkSet.clear()
        requestModelBuild()
    }

    fun getCheckList() = checkSet.toList()

    fun clearCheckSet() = checkSet.clear()

}