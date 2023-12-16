package com.example.springwebapp.model.Mapper;

import com.example.springwebapp.model.response.ResponseNews.ResponseComment;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
@Component
public class CommentMapper {

    public static String convertDate(String isoDateString) {
        ZonedDateTime zdt = ZonedDateTime.parse(isoDateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        return zdt.format(formatter);
    }

    public static ResponseComment toResponseComment (Object object) {
        try {
            LinkedHashMap<String, Object> comment = (LinkedHashMap<String, Object>) object;

            ResponseComment responseComment = new ResponseComment();
            responseComment.setId((Integer) comment.get("id"));
            responseComment.setContent( (String) comment.get("content"));
            responseComment.setDateCreated(convertDate((String) comment.get("dateCreated")));
            responseComment.setDateUpdated(convertDate((String) comment.get("dateUpdated")));
            responseComment.setLikeNumber((Integer) comment.get("likeNumber"));
            responseComment.setParentCommentId((Integer) comment.get("parentCommentId"));
            responseComment.setAccountId((Integer) comment.get("accountId"));
            responseComment.setAccountUsername((String) comment.get("accountUsername"));
            responseComment.setNewsId((Integer) comment.get("newsId"));

            return responseComment;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<ResponseComment> toResponseCommentList (List<Object> objects) {
        try {
            List<ResponseComment> commentList = new ArrayList<>();
            for (Object object : objects) {
                commentList.add(toResponseComment(object));
            }
            return commentList;
        } catch (Exception e) {
            return null;
        }
    }
}
