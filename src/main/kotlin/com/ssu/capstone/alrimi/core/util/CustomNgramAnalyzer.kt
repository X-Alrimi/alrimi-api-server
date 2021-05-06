package com.ssu.capstone.alrimi.core.util

import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.ko.KoreanAnalyzer
import org.apache.lucene.analysis.ko.KoreanPartOfSpeechStopFilter
import org.apache.lucene.analysis.ko.KoreanTokenizer
import org.apache.lucene.analysis.ko.dict.UserDictionary

object CustomNgramAnalyzer {
    private var analyzer: Analyzer? = null

    fun createInstance(userDictionary: UserDictionary) {
        analyzer = KoreanAnalyzer(userDictionary,
                KoreanTokenizer.DecompoundMode.NONE,
                KoreanPartOfSpeechStopFilter.DEFAULT_STOP_TAGS,
                false)
    }

    fun getInstance(): Analyzer {
        if (this.analyzer == null) {
            analyzer = KoreanAnalyzer()

        }
        return analyzer!!
    }
}