package com.example.springrestful.service.NewsService;
import com.example.springrestful.model.request.RequestNews.RequestNews;
import com.example.springrestful.model.response.ResponseNews.ResponseNews;

import java.util.List;

public interface NewsService {
    ResponseNews saveNews (RequestNews requestNews) throws Exception;
    ResponseNews editNews (RequestNews requestNews) throws Exception;
    void removeNewsById (int id) throws Exception;
    ResponseNews findNewsById (int id) throws Exception;
    List<ResponseNews> findAllNews () throws Exception;
    List<ResponseNews> findAllNewsByAccountId (int accountId, int limit, int offset) throws Exception;
    List<ResponseNews> findAllNewsByAccountIdAndTitle (int accountId, int limit, int offset, String title) throws Exception;
    List<ResponseNews> findAllNewsByAccountIdTopPopular (int accountId, int limit, int offset) throws Exception;
    List<ResponseNews> findAllNewsByAccountIdTopPopularAndTitle (int accountId, int limit, int offset, String title) throws Exception;
    List<ResponseNews> findAllNewsByAccountIdTopNewest (int accountId, int limit, int offset) throws Exception;
    List<ResponseNews> findAllNewsByAccountIdTopNewestAndTitle (int accountId, int limit, int offset, String title) throws Exception;


    List<ResponseNews> findAllNewsLimit (int limit, int offset) throws Exception;
    List<ResponseNews> findByTitleLimit (String title, int limit, int offset) throws Exception;
    List<ResponseNews> findTopPopular (int limit, int offset) throws Exception;
    List<ResponseNews> findTopPopularAndTitle (String title, int limit, int offset) throws Exception;

    List<ResponseNews> findTopNewest (int limit, int offset) throws Exception;
    List<ResponseNews> findTopNewestAndTitle (String title, int limit, int offset) throws Exception;

    ResponseNews plusNewsViewById (int id) throws Exception;
}
