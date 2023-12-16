package com.example.springwebapp.model.Mapper;

import com.example.springwebapp.model.response.ResponseNews.ResponseNews;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class NewsMapper {
    public static ResponseNews toResponseNews (Object object) {
        try {
            LinkedHashMap<String, Object> news = (LinkedHashMap<String, Object>) object;

            ResponseNews responseNews = new ResponseNews();
            responseNews.setId((Integer) news.get("id"));
            responseNews.setTitle((String) news.get("title"));
            responseNews.setContent((String) news.get("content"));
            responseNews.setThumbnail((String) news.get("thumbnail"));
            responseNews.setDateCreated((String) news.get("dateCreated"));
            responseNews.setDateUpdated((String) news.get("dateUpdated"));
            responseNews.setViewNumber((Integer) news.get("viewNumber"));
            responseNews.setAccountId((Integer) news.get("accountId"));

            return responseNews;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<ResponseNews> toResponseNewsList (List<Object> objects) {
        try {
            List<ResponseNews> newsList = new ArrayList<>();
            for (Object object : objects) {
                newsList.add(toResponseNews(object));
            }
            return newsList;
        } catch (Exception e) {
            return null;
        }
    }
}
