package com.consistence.pinyin.domain.study

import com.consistence.pinyin.domain.pinyin.Pinyin

data class Study(
    val uid: Int,
    val englishTranslation: String,
    val pinyin: List<Pinyin>
)