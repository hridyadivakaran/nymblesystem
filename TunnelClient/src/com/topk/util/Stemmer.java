package com.topk.util;

import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.ClassicFilter;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Harris Bastin
 */
public class Stemmer {

    private Version LUCENE_VERSION = Version.LUCENE_34;

    public void gussFromString(String input) throws IOException {
        input = input.replaceAll("-+", "-0");
        // replace any punctuation char but dashes and apostrophes and by a space
        input = input.replaceAll("[\\p{Punct}&&[^'-]]+", " ");
        // replace most common english contractions
        input = input.replaceAll("(?:'(?:[tdm]|[vr]e|ll))+\\b", "");
        TokenStream tokenStream = new ClassicTokenizer(LUCENE_VERSION, new StringReader(input));
        // to lower case
        tokenStream = new LowerCaseFilter(LUCENE_VERSION, tokenStream);
        // remove dots from acronyms (and "'s" but already done manually above)
        tokenStream = new ClassicFilter(tokenStream);
        // convert any char to ASCII
        tokenStream = new ASCIIFoldingFilter(tokenStream);
        // remove english stop words
        tokenStream = new StopFilter(LUCENE_VERSION, tokenStream, EnglishAnalyzer.getDefaultStopSet());
        CharTermAttribute token = tokenStream.getAttribute(CharTermAttribute.class);

        while (tokenStream.incrementToken()) {
            String term = token.toString();
            System.out.println(term);
            Keyword keyword = new Keyword(term);
            keyword.add(term);
            // stemmize
            //     String stem = stemmize(term);
            //  if (stem != null) {

            //  }
        }
    }

  

    public static void main(String[] args) throws IOException {
        Stemmer stemmer = new Stemmer();
        String content = " is an American multinational technology company "
                + "headquartered in Cupertino, California, that designs, develops,"
                + " and sells consumer electronics, computer software, and online "
                + "services. Its hardware - products are the iPhone smartphone, the "
                + "iPad tablet computer, the Mac personal computer, the iPod portable "
                + "media player, and the Apple Watch smartwatch. Apple's consumer "
                + "software includes the OS X and iOS operating systems, the iTunes "
                + "media player, the Safari web browser, and the iLife and iWork "
                + "creativity and productivity suites. Its online services include "
                + "the iTunes Store,"
                + " the iOS App Store and Mac App Store, and iCloud.";
        stemmer.gussFromString(content);
    }
}
