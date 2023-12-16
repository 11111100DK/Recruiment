package com.example.springrestful.model.mapper;

import com.example.springrestful.model.entity.News.News;
import com.example.springrestful.model.response.ResponseNews.ResponseNews;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    @Mapping(source = "news.id", target = "id")
    @Mapping(source = "news.title", target = "title")
    @Mapping(source = "news.content", target = "content")
    @Mapping(source = "news.thumbnail", target = "thumbnail")
    @Mapping(source = "news.dateCreated", target = "dateCreated", qualifiedByName = "formatDate")
    @Mapping(source = "news.dateUpdated", target = "dateUpdated", qualifiedByName = "formatDate")
    @Mapping(source = "news.viewNumber", target = "viewNumber")
    @Mapping(source = "news.status", target = "status")
    @Mapping(source = "news.account.id", target = "accountId")
    ResponseNews toResponseNews(News news);
    List<ResponseNews> toResponseNewsList(List<News> news);
    @Named("formatDate")
    default String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
}
