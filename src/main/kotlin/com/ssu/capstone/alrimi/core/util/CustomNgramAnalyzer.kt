package com.ssu.capstone.alrimi.core.util

import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.ko.KoreanAnalyzer
import org.apache.lucene.analysis.ko.KoreanPartOfSpeechStopFilter
import org.apache.lucene.analysis.ko.KoreanTokenizer
import org.apache.lucene.analysis.ko.dict.UserDictionary
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute

object CustomNgramAnalyzer {
    private var analyzer: Analyzer? = null

    fun createInstance(userDictionary: UserDictionary) {
        analyzer = KoreanAnalyzer(userDictionary,
                KoreanTokenizer.DecompoundMode.NONE,
                KoreanPartOfSpeechStopFilter.DEFAULT_STOP_TAGS,
                false)
    }

    fun getNgram(text: String): List<String> {
        val list: MutableList<String> = mutableListOf()
        val tokenStream: TokenStream = analyzer!!.tokenStream("text", text)
        val tokenAttr: CharTermAttribute = tokenStream.addAttribute(CharTermAttribute::class.java)
        try {
            tokenStream.reset()
            while (tokenStream.incrementToken())
                list.add(tokenAttr.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        tokenStream.close()
        return list
    }
}