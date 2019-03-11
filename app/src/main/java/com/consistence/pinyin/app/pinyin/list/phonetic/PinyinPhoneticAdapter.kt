package com.consistence.pinyin.app.pinyin.list.phonetic

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.consistence.pinyin.R
import com.consistence.pinyin.domain.pinyin.Pinyin
import com.consistence.pinyin.kit.*
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.pinyin_phonetic_list_item.view.*

class PinyinPhoneticAdapter(
    context: Context,
    private val fullListStyle: Boolean,
    interaction: PublishSubject<Interaction<Pinyin>>
) : SimpleAdapter<Pinyin>(context, interaction) {

    override fun createViewHolder(parent: ViewGroup): SimpleAdapterViewHolder<Pinyin> {
        val viewHolder = PinyinPhoneticViewHolder(inflater.inflate(
                R.layout.pinyin_phonetic_list_item, parent, false), fullListStyle)

        RxView.clicks(viewHolder.audioButton).map {
            Interaction(viewHolder.audioButton.id, data[viewHolder.adapterPosition])
        }.subscribe(interaction)

        return viewHolder
    }
}

class PinyinPhoneticViewHolder(
    itemView: View,
    private val fullListStyle: Boolean
) : SimpleAdapterViewHolder<Pinyin>(itemView) {

    val audioButton: ImageButton = itemView.pinyin_list_audio_button

    override fun populate(position: Int, value: Pinyin) {
        itemView.pinyin_phonetic_list_item_value.text = value.phoneticScriptText
        itemView.pinyin_phonetic_list_item_english_translation_value.text = value.englishTranslationText
        itemView.pinyin_phonetic_list_item_chinese_character_value.text = value.chineseCharacters
        value.audioSrc?.let { audioButton.visible() } ?: audioButton.gone()

        if (fullListStyle) {
            itemView.pinyin_phonetic_list_item_english_translation.visible()
            itemView.pinyin_phonetic_list_item_english_translation_value.visible()
            itemView.pinyin_phonetic_list_item_chinese_character.visible()
            itemView.pinyin_phonetic_list_item_chinese_character_value.visible()
            itemView.pinyin_list_audio_button.visible()
        } else {
            itemView.pinyin_phonetic_list_item_english_translation.visible()
            itemView.pinyin_phonetic_list_item_english_translation_value.visible()
            itemView.pinyin_phonetic_list_item_chinese_character.gone()
            itemView.pinyin_phonetic_list_item_chinese_character_value.gone()
            itemView.pinyin_list_audio_button.gone()
        }
    }
}