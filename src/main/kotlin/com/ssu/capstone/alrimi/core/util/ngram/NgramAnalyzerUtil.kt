package com.ssu.capstone.alrimi.core.util.ngram

import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.ko.KoreanAnalyzer
import org.apache.lucene.analysis.ngram.NGramTokenFilter
import org.apache.lucene.analysis.ngram.NGramTokenizer

class NgramAnalyzerUtil : Analyzer() {
    override fun createComponents(fieldName: String?): TokenStreamComponents {
        val tokenizer = NGramTokenizer(1, 3) //min 2 && max 3
        val nGramTokenFilter = NGramTokenFilter(tokenizer, 2)
        return Analyzer.TokenStreamComponents(tokenizer, nGramTokenFilter)
    }
}