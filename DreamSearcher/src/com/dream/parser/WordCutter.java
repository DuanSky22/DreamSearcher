package com.dream.parser;

import java.io.IOException;

public abstract class WordCutter {

	abstract String[] getWordList(String url) throws IOException;
}
