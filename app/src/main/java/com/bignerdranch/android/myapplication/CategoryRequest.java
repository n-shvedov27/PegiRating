package com.bignerdranch.android.myapplication;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Iterator;

public class CategoryRequest implements Runnable {


    private static final String TAG = "CategoryRequest";
    private String appName;
    private String rating;

    public String getRating() {
        return rating;
    }

    public CategoryRequest(String appName) {
        this.appName = appName;
    }

    @Override
    public void run() {
        String queryUrl =
                "https://play.google.com/store/apps/details?id=" +
                        appName +
                        "&hl=ru";

        rating = getRating(queryUrl);
    }

    private String getRating(String queryUrl) {
        try {
            Connection connect = Jsoup.connect(queryUrl);

            Document document = connect.get();

            Iterator<Element> elementIterator = document.select("img[class=\"T75of E1GfKc\"]").iterator();
//            elementIterator.next();

            return elementIterator.next().attr("alt");
        } catch (HttpStatusException e) {
            return "default";
        } catch (IOException e) {
            return null;

        }

    }
}
