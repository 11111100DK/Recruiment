package com.example.springwebapp.Service.NewsService;

import com.example.springwebapp.model.request.RequestNews.RequestNews;
import com.example.springwebapp.model.request.RequestNews.RequestPaginationNews;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseNews.ResponseNews;
import com.example.springwebapp.restapi.CommonRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    CommonRestClient commonRestClient;
    private final String apiNews = "http://localhost:8080/api/news";
    private final String apiNewsPagination = "http://localhost:8080/api/news/pagination";
    private final String apiNewsAddLike = "http://localhost:8080/api/news/add-view";
    @Override
    public ApiResponse<ResponseNews> saveNews(RequestNews requestNews) throws Exception {
        return commonRestClient.post(apiNews, requestNews);
    }

    @Override
    public ApiResponse<ResponseNews> editNews(RequestNews requestNews) throws Exception {
        return commonRestClient.put(apiNews, requestNews, requestNews.getId());
    }

    @Override
    public ApiResponse<ResponseNews> removeNewsById(int id) throws Exception {
        return commonRestClient.deleteByCondition(apiNews, id);
    }

    @Override
    public ApiResponse<ResponseNews> findNewsById(int id) throws Exception {
        return commonRestClient.getByConditionUnique(apiNews, id);
    }

    @Override
    public List<ResponseNews> findAllNews() throws Exception {
        return commonRestClient.getAll(apiNews);
    }

    @Override
    public ApiResponse<List<ResponseNews>> findAllNewsByAccountId(int accountId, RequestPaginationNews requestPaginationNews) throws Exception {
        return commonRestClient.postPaginationAndCondition(apiNewsPagination, requestPaginationNews, accountId);
    }

    @Override
    public ApiResponse<List<ResponseNews>> findAllNewsByAccountIdAndTitle(int accountId, RequestPaginationNews requestPaginationNews, String title) throws Exception {
        return commonRestClient.postPaginationAndCondition(apiNewsPagination, requestPaginationNews, accountId + "/" + title);
    }

    @Override
    public ApiResponse<List<ResponseNews>> findAllNewsByAccountIdPopular(int accountId, RequestPaginationNews requestPaginationNews) throws Exception {
        return commonRestClient.postPaginationAndCondition(apiNewsPagination + "/popular/account", requestPaginationNews, accountId);
    }

    @Override
    public ApiResponse<List<ResponseNews>> findAllNewsByAccountIdPopularAndTitle(int accountId, RequestPaginationNews requestPaginationNews, String title) throws Exception {
        return commonRestClient.postPaginationAndCondition(apiNewsPagination + "/popular/account", requestPaginationNews, accountId + "/" + title);
    }

    @Override
    public ApiResponse<List<ResponseNews>> findAllNewsByAccountIdTopNewest(int accountId, RequestPaginationNews requestPaginationNews) throws Exception {
        return commonRestClient.postPaginationAndCondition(apiNewsPagination + "/newest/account", requestPaginationNews, accountId);
    }

    @Override
    public ApiResponse<List<ResponseNews>> findAllNewsByAccountIdTopNewestAndTitle(int accountId, RequestPaginationNews requestPaginationNews, String title) throws Exception {
        return commonRestClient.postPaginationAndCondition(apiNewsPagination + "/newest/account", requestPaginationNews, accountId + "/" + title);

    }

    @Override
    public ApiResponse<List<ResponseNews>> findAllNewsLimit(RequestPaginationNews requestPaginationNews) throws Exception {
        return commonRestClient.postPagination(apiNewsPagination, requestPaginationNews);
    }

    @Override
    public ApiResponse<List<ResponseNews>> findByTitleLimit(String title, RequestPaginationNews requestPaginationNews) throws Exception {
        return commonRestClient.postPaginationAndCondition(apiNewsPagination + "/title", requestPaginationNews, title);
    }

    @Override
    public ApiResponse<List<ResponseNews>> findTopPopular(RequestPaginationNews requestPaginationNews) throws Exception {
        return commonRestClient.postPagination(apiNewsPagination + "/popular", requestPaginationNews);
    }

    @Override
    public ApiResponse<List<ResponseNews>> findTopPopularAndTitle(String title, RequestPaginationNews requestPaginationNews) throws Exception {
        return commonRestClient.postPaginationAndCondition(apiNewsPagination + "/popular", requestPaginationNews, title);
    }

    @Override
    public ApiResponse<List<ResponseNews>> findTopNewest(RequestPaginationNews requestPaginationNews) throws Exception {
        return commonRestClient.postPagination(apiNewsPagination + "/newest", requestPaginationNews);
    }

    @Override
    public ApiResponse<List<ResponseNews>> findTopNewestAndTitle(String title, RequestPaginationNews requestPaginationNews) throws Exception {
        return commonRestClient.postPaginationAndCondition(apiNewsPagination + "/newest", requestPaginationNews, title);
    }

    @Override
    public ApiResponse<ResponseNews> plusNewsViewById(int id) throws Exception {
        return commonRestClient.postCondition(apiNewsAddLike, id);
    }
}
