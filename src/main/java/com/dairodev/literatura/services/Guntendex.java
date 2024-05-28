package com.dairodev.literatura.services;

import com.dairodev.literatura.models.ResponseData;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Guntendex {
    private ParseData parseData;
    private static final String GUTENDEX_URL = "https://gutendex.com/books/";

    public  Guntendex() {
        parseData = new ParseData();
    }
    private String encodeQuery (String query) {
        return URLEncoder.encode(query, StandardCharsets.UTF_8);
    }

    public ResponseData searchBooks(String bookTitle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GUTENDEX_URL);
        stringBuilder.append("?search=");
        stringBuilder.append(encodeQuery(bookTitle));
        Fetch fetch = new Fetch();
        return  parseData.getData(fetch.get(stringBuilder.toString()), ResponseData.class);
    }
}
