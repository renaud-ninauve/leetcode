package fr.ninauve.renaud.leetcode.problems.wordbreak;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WordBreakTest {

    @ParameterizedTest
    @CsvSource(delimiter = ' ', value = {
            "leetcode [leet,code] true",
            "applepenapple [apple,pen] true",
            "catsandog [cats,dog,sand,and,cat] false",
            "aaaaaaa [aaaa,aaa] true",
            "acaaaaabbbdbcccdcdaadcdccacbcccabbbbcdaaaaaadb [abbcbda,cbdaaa,b,dadaaad,dccbbbc,dccadd,ccbdbc,bbca,bacbcdd,a,bacb,cbc,adc,c,cbdbcad,cdbab,db,abbcdbd,bcb,bbdab,aa,bcadb,bacbcb,ca,dbdabdb,ccd,acbb,bdc,acbccd,d,cccdcda,dcbd,cbccacd,ac,cca,aaddc,dccac,ccdc,bbbbcda,ba,adbcadb,dca,abd,bdbb,ddadbad,badb,ab,aaaaa,acba,abbb] true",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab [a,aa,aaa,aaaa,aaaaa,aaaaaa,aaaaaaa,aaaaaaaa,aaaaaaaaa,aaaaaaaaaa] false"
    })
    void should_word_break(String s, String wordDictParam, boolean expected) {
        List<String> wordDict =
                Arrays.stream(wordDictParam.substring(1, wordDictParam.length() - 1).split(","))
                        .toList();

        boolean actual = new WordBreak().wordBreak(s, wordDict);

        assertThat(actual).isEqualTo(expected);
    }
}