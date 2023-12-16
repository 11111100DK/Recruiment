package com.example.springwebapp.Service.NewsService;

import com.example.springwebapp.model.request.RequestNews.RequestNews;
import com.example.springwebapp.model.request.RequestNews.RequestPaginationNews;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseNews.ResponseNews;

import java.util.List;

public interface NewsService {

    // basic news services
    ApiResponse<ResponseNews> saveNews (RequestNews requestNews) throws Exception;
    ApiResponse<ResponseNews> editNews (RequestNews requestNews) throws Exception;
    ApiResponse<ResponseNews> removeNewsById (int id) throws Exception;
    ApiResponse<ResponseNews> findNewsById (int id) throws Exception;
    List<ResponseNews> findAllNews () throws Exception;

    // personal news
    ApiResponse<List<ResponseNews>> findAllNewsByAccountId (int accountId, RequestPaginationNews requestPaginationNews) throws Exception;
    ApiResponse<List<ResponseNews>> findAllNewsByAccountIdAndTitle (int accountId, RequestPaginationNews requestPaginationNews, String title) throws Exception;
    ApiResponse<List<ResponseNews>> findAllNewsByAccountIdPopular (int accountId, RequestPaginationNews requestPaginationNews) throws Exception;
    ApiResponse<List<ResponseNews>> findAllNewsByAccountIdPopularAndTitle (int accountId, RequestPaginationNews requestPaginationNews, String title) throws Exception;
    ApiResponse<List<ResponseNews>> findAllNewsByAccountIdTopNewest (int accountId, RequestPaginationNews requestPaginationNews) throws Exception;
    ApiResponse<List<ResponseNews>> findAllNewsByAccountIdTopNewestAndTitle (int accountId, RequestPaginationNews requestPaginationNews, String title) throws Exception;

    // news
    ApiResponse<List<ResponseNews>> findAllNewsLimit (RequestPaginationNews requestPaginationNews) throws Exception;
    ApiResponse<List<ResponseNews>> findByTitleLimit (String title, RequestPaginationNews requestPaginationNews) throws Exception;
    ApiResponse<List<ResponseNews>> findTopPopular (RequestPaginationNews requestPaginationNews) throws Exception;
    ApiResponse<List<ResponseNews>> findTopPopularAndTitle (String title, RequestPaginationNews requestPaginationNews) throws Exception;

    ApiResponse<List<ResponseNews>> findTopNewest (RequestPaginationNews requestPaginationNews) throws Exception;
    ApiResponse<List<ResponseNews>> findTopNewestAndTitle (String title, RequestPaginationNews requestPaginationNews) throws Exception;

    ApiResponse<ResponseNews> plusNewsViewById (int id) throws Exception;
}
