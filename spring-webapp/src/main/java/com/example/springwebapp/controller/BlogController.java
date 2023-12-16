package com.example.springwebapp.controller;

import com.example.springwebapp.Service.NewsService.CommentService;
import com.example.springwebapp.Service.NewsService.NewsService;
import com.example.springwebapp.model.Mapper.CommentMapper;
import com.example.springwebapp.model.Mapper.NewsMapper;
import com.example.springwebapp.model.request.RequestNews.RequestCreateComment;
import com.example.springwebapp.model.request.RequestNews.RequestEditComment;
import com.example.springwebapp.model.request.RequestNews.RequestNews;
import com.example.springwebapp.model.request.RequestNews.RequestPaginationNews;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ApiResponse.StatusEnum;
import com.example.springwebapp.model.response.ResponseNews.ResponseComment;
import com.example.springwebapp.model.response.ResponseNews.ResponseNews;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    NewsService newsService;
    @Autowired
    CommentService commentService;

    @GetMapping(value = "/blog")
    public String getBlogView(Model model, @RequestParam(required = false) String title) throws Exception {
        RequestPaginationNews requestPaginationNews = new RequestPaginationNews(1000, 1);

        if (title != null && !title.equalsIgnoreCase("")) {
            ApiResponse<List<ResponseNews>> apiResponse = newsService.findByTitleLimit(title, requestPaginationNews);
            if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
                return "blog";
            }

            model.addAttribute("blogs", apiResponse.getPayload());
            return "blog";
        }

        ApiResponse<List<ResponseNews>> apiResponse = newsService.findAllNewsLimit(requestPaginationNews);
        if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
            return "blog";
        }

        model.addAttribute("blogs", apiResponse.getPayload());
        return "blog";
    }

    @GetMapping(value = "/blog/popular")
    public String getMostPopularBlogs(Model model, @RequestParam(required = false) String title) throws Exception {
        RequestPaginationNews requestPaginationNews = new RequestPaginationNews(1000, 1);

        if (title != null && !title.equalsIgnoreCase("")) {
            ApiResponse<List<ResponseNews>> apiResponse = newsService.findTopPopularAndTitle(title, requestPaginationNews);
            if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
                return "blog";
            }

            model.addAttribute("blogs", apiResponse.getPayload());
            return "blog";
        }

        ApiResponse<List<ResponseNews>> apiResponse = newsService.findTopPopular(requestPaginationNews);
        if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
            return "blog";
        }

        model.addAttribute("blogs", apiResponse.getPayload());
        return "blog";
    }

    @GetMapping(value = "/blog/last-updated")
    public String getNewestBlogs(Model model, @RequestParam(required = false) String title) throws Exception {
        RequestPaginationNews requestPaginationNews = new RequestPaginationNews(1000, 1);

        if (title != null && !title.equalsIgnoreCase("")) {
            ApiResponse<List<ResponseNews>> apiResponse = newsService.findTopNewestAndTitle(title, requestPaginationNews);
            if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
                return "blog";
            }

            model.addAttribute("blogs", apiResponse.getPayload());
            return "blog";
        }

        ApiResponse<List<ResponseNews>> apiResponse = newsService.findTopNewest(requestPaginationNews);
        if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
            return "blog";
        }

        model.addAttribute("blogs", apiResponse.getPayload());
        return "blog";
    }

    @GetMapping(value = "/blog-detail{id}")
    public String getBlogSingleView(Model model, @RequestParam int id) throws Exception {
        ApiResponse<ResponseNews> apiResponseNews = newsService.findNewsById(id);
        if (apiResponseNews == null || apiResponseNews.getStatus() != StatusEnum.SUCCESS) {
            model.addAttribute("messageError", "Cannot get News detail");
            return "blog";
        }

        newsService.plusNewsViewById(id);
        ResponseNews responseNews = NewsMapper.toResponseNews(apiResponseNews.getPayload());
        model.addAttribute("blog", responseNews);

        List<ResponseComment> apiResponseComment = CommentMapper.toResponseCommentList(commentService.findCommentByNewsIdLimit(id, 100));
        if (apiResponseComment != null) {
            model.addAttribute("comments", apiResponseComment);
        } else {
            model.addAttribute("comments", null);
        }
        model.addAttribute("listMostView", newsService.findTopPopular(new RequestPaginationNews(5, 1)).getPayload());
        return "blog-single";
    }

    @GetMapping(value = "/post-blog")
    public String getPostBlogView(Model model) {
        model.addAttribute("requestNews", new RequestNews());
        return "post-news";
    }

    @PostMapping(value = "/post-blog")
    public String postNews(Model model, @ModelAttribute("requestNews")  RequestNews requestNews, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "post-news"; // render view again
        }

        requestNews.setAccountId(2);
        ApiResponse<ResponseNews> apiResponse = newsService.saveNews(requestNews);
        if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
            return "post-news";
        }

        model.addAttribute("successMessage", "Post news successfully");
        return "redirect:/personal-blog";
    }

    @GetMapping(value = "/delete-blog{id}")
    public String deleteNews(Model model, @RequestParam Integer id) throws Exception {

        ApiResponse<ResponseNews> apiResponse = newsService.removeNewsById(id);

        if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
            model.addAttribute("error", "Cannot find news to delete");
            return "redirect:/personal-blog";
        }

        model.addAttribute("successMessage", "Delete news successfully");
        return "redirect:/personal-blog";
    }

    @GetMapping(value = "/edit-blog/{id}")
    public String getEditBlogView(@PathVariable int id, Model model) throws Exception {
        ResponseNews responseNews = NewsMapper.toResponseNews(newsService.findNewsById(id).getPayload());
        if (responseNews == null) {
            return "redirect:/personal-blog";
        }

        RequestNews requestNews = new RequestNews();
        requestNews.setId(responseNews.getId());
        requestNews.setAccountId(responseNews.getAccountId());
        requestNews.setContent(responseNews.getContent());
        requestNews.setTitle(responseNews.getTitle());
        requestNews.setThumbnail(responseNews.getThumbnail());

        model.addAttribute("requestNews", requestNews);
        return "edit-news";
    }

    @PostMapping(value = "/edit-blog")
    public String editBlog(Model model, @ModelAttribute @Valid RequestNews requestNews, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "redirect:/edit-blog/" + requestNews.getId();
        }

        ApiResponse<ResponseNews> apiResponse = newsService.editNews(requestNews);

        if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
            model.addAttribute("error", "Cannot find news to delete");
            return "redirect:/edit-blog/" + requestNews.getId();
        }

        return "redirect:/personal-blog";
    }

    // comments handle

    @PostMapping(value = "/post-comment")
    public String postComment(@RequestParam String content, @RequestParam int newsId) throws Exception {

        if (content == null || content.equalsIgnoreCase("")) {
            return "redirect:/blog-detail?id=" + newsId;
        }

        RequestCreateComment requestCreateComment = new RequestCreateComment();
        requestCreateComment.setContent(content);
        requestCreateComment.setParentCommentId(0);
        requestCreateComment.setNewsId(newsId);
        requestCreateComment.setAccountId(2);

        commentService.saveComment(requestCreateComment);
        return "redirect:/blog-detail?id=" + newsId;
    }

    @GetMapping(value = "/edit-comment")
    public String editComment(@RequestParam int commentId, @RequestParam String content, @RequestParam int newsId) throws Exception {
        RequestEditComment requestEditComment = new RequestEditComment();
        requestEditComment.setId(commentId);
        requestEditComment.setContent(content);
        commentService.editComment(requestEditComment);
        return "redirect:/blog-detail?id=" + newsId;
    }

    @GetMapping(value = "/delete-comment{commentId}{newsId}")
    public String deleteComment(@RequestParam int commentId, @RequestParam int newsId, Model model) throws Exception {
        ApiResponse apiResponse = commentService.removeCommentById(commentId);
        if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
            model.addAttribute("Error", "Cannot delete");
            return "redirect:/blog-detail?id=" + newsId;
        }
        return "redirect:/blog-detail?id=" + newsId;
    }
    // personal blogs

    @GetMapping(value = "/personal-blog")
    public String getPersonalBlog(Model model, @RequestParam(required = false) String title) throws Exception {

        RequestPaginationNews requestPaginationNews = new RequestPaginationNews(1000, 1);

        if (title != null && !title.equalsIgnoreCase("")) {
            ApiResponse<List<ResponseNews>> apiResponse = newsService.findAllNewsByAccountIdAndTitle(2, requestPaginationNews, title);
            if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
                return "personal-blog";
            }

            model.addAttribute("blogs", apiResponse.getPayload());
            return "personal-blog";
        }

        ApiResponse<List<ResponseNews>> apiResponse = newsService.findAllNewsByAccountId(2, requestPaginationNews);
        if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
            return "personal-blog";
        }

        model.addAttribute("blogs", apiResponse.getPayload());
        return "personal-blog";
    }

    @GetMapping(value = "/personal-blog/popular")
    public String getPersonalBlogPopular(Model model, @RequestParam(required = false) String title) throws Exception {

        RequestPaginationNews requestPaginationNews = new RequestPaginationNews(1000, 1);

        if (title != null && !title.equalsIgnoreCase("")) {
            ApiResponse<List<ResponseNews>> apiResponse = newsService.findAllNewsByAccountIdPopularAndTitle(2, requestPaginationNews, title);
            if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
                return "personal-blog";
            }

            model.addAttribute("blogs", apiResponse.getPayload());
            return "personal-blog";
        }

        ApiResponse<List<ResponseNews>> apiResponse = newsService.findAllNewsByAccountIdPopular(2, requestPaginationNews);
        if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
            return "personal-blog";
        }

        model.addAttribute("blogs", apiResponse.getPayload());
        return "personal-blog";
    }

    @GetMapping(value = "/personal-blog/newest")
    public String getPersonalBlogNewest(Model model, @RequestParam(required = false) String title) throws Exception {

        RequestPaginationNews requestPaginationNews = new RequestPaginationNews(1000, 1);

        if (title != null && !title.equalsIgnoreCase("")) {
            ApiResponse<List<ResponseNews>> apiResponse = newsService.findAllNewsByAccountIdTopNewestAndTitle(2, requestPaginationNews, title);
            if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
                return "personal-blog";
            }

            model.addAttribute("blogs", apiResponse.getPayload());
            return "personal-blog";
        }

        ApiResponse<List<ResponseNews>> apiResponse = newsService.findAllNewsByAccountIdTopNewest(2, requestPaginationNews);
        if (apiResponse == null || apiResponse.getStatus() != StatusEnum.SUCCESS) {
            return "personal-blog";
        }

        model.addAttribute("blogs", apiResponse.getPayload());
        return "personal-blog";
    }
}
