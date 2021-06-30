package com.cloudinteractive.pxhomework.ui.message

import android.view.View
import android.widget.TextView
import android.widget.ToggleButton
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

import com.cloudinteractive.pxhomework.R
import com.cloudinteractive.pxhomework.model.GetMessagesResult

@EpoxyModelClass
abstract class MessageItemEpoxyModel : EpoxyModelWithHolder<MessageItemEpoxyModel.Holder>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onMessageItemClick: (GetMessagesResult.Message) -> Unit

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onCheckChange: (GetMessagesResult.Message, isChecked: Boolean) -> Unit

//        @EpoxyAttribute
//        var title = ""
//
//        @EpoxyAttribute
//        var message = ""
//
//        @EpoxyAttribute
//        var ts = ""

    @EpoxyAttribute
    lateinit var message: GetMessagesResult.Message

    @EpoxyAttribute
    var selectMode = false

    @EpoxyAttribute
    var checked = false

    override fun getDefaultLayout(): Int = R.layout.item_message

    override fun bind(holder: Holder) {
        super.bind(holder)

        holder.tvTitle.text = message.title
        holder.tvMessage.text = message.msg
        holder.tvTs.text = message.ts
        holder.clMessage.setOnClickListener {
            onMessageItemClick.invoke(message)
        }

        holder.tbSelect.isChecked = checked
        holder.tbSelect.setOnCheckedChangeListener { _, isChecked ->
            onCheckChange.invoke(message, isChecked)
        }


        holder.tbSelect.visibility = if (selectMode) View.VISIBLE else View.GONE
    }


    class Holder : EpoxyHolder() {
        lateinit var tvTitle: TextView
        lateinit var tvMessage: TextView
        lateinit var tvTs: TextView
        lateinit var tbSelect: ToggleButton
        lateinit var clMessage: View

        override fun bindView(itemView: View) {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvMessage = itemView.findViewById(R.id.tvMessage)
            tvTs = itemView.findViewById(R.id.tvTs)
            tbSelect = itemView.findViewById(R.id.tbSelect)
            clMessage = itemView.findViewById(R.id.clMessage)
        }
    }
}