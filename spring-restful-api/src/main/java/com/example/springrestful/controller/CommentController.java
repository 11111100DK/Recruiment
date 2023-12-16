package com.example.springrestful.controller;

import com.example.springrestful.exception.ApplicationException;
import com.example.springrestful.exception.NotFoundException;
import com.example.springrestful.exception.ValidationException;
import com.example.springrestful.model.request.RequestNews.RequestCreateComment;
import com.example.springrestful.model.request.RequestNews.RequestEditComment;
import com.example.springrestful.model.response.ApiResponse.ApiResponse;
import com.example.springrestful.model.response.ApiResponse.StatusEnum;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;
import com.example.springrestful.model.response.ResponseNews.ResponseComment;
import com.example.springrestful.service.AccountService.AccountService;
import com.example.springrestful.service.NewsService.CommentService;
import com.example.springrestful.service.NewsService.NewsService;
import com.example.springrestful.util.ValidatorUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    NewsService newsService;
    @Autowired
    ValidatorUtil validatorUtil;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ResponseComment>>> returnAllComments() throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(commentService.findAllComments());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseComment>> returnCommentById (@PathVariable int id) {
        try {
            ApiResponse apiResponse = new ApiResponse();
            ResponseComment responseComment = commentService.findCommentById(id);
            if (responseComment == null) {
                throw new NotFoundException("Comment not found");
            }
            apiResponse.ok(responseComment);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping("/newsId/{newsId}/{limit}")
    public ResponseEntity<ApiResponse<List<ResponseComment>>> returnCommentByNewsId (@PathVariable int newsId, @PathVariable int limit) {
        try {
            ApiResponse apiResponse = new ApiResponse();
            List<ResponseComment> responseComment = commentService.findCommentsByNewsIdLimit(newsId, limit);
            apiResponse.ok(responseComment);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping("/reply/{id}")
    public ResponseEntity<ApiResponse<List<ResponseComment>>> returnAllReplyComments(@PathVariable int id) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(commentService.findReplyCommentsByParentCommentId(id));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping("/reply/{id}/{limit}")
    public ResponseEntity<ApiResponse<List<ResponseComment>>> returnAllReplyCommentsLimit(@PathVariable int id, @PathVariable int limit) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(commentService.findReplyCommentsByParentCommentLimit(id, limit));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping(value = "/newest/{limit}")
    public ResponseEntity<ApiResponse<List<ResponseComment>>> returnCommentsNewest(@PathVariable int limit, BindingResult bindingResult) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            if (limit < 0) {
                throw new ApplicationException("Limit cannot be negative");
            }

            apiResponse.ok(commentService.findNewestCommentsLimit(limit));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping(value = "/add-like/{id}")
    public ResponseEntity<ApiResponse<List<ResponseComment>>> addLike(@PathVariable int id) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            if (commentService.findCommentById(id) == null) {
                throw new NotFoundException("Cannot find comment to like");
            }
            apiResponse.ok(commentService.plusCommentLikeById(id));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping(value = "/remove-like/{id}")
    public ResponseEntity<ApiResponse<List<ResponseComment>>> removeLike(@PathVariable int id) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            if (commentService.findCommentById(id) == null) {
                throw new NotFoundException("Cannot find comment to remove like");
            }

            if (commentService.findCommentById(id).getLikeNumber() > 0) {
                apiResponse.ok(commentService.removeCommentLikeById(id));
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<ResponseComment>> addComments(
            @Valid @RequestBody RequestCreateComment requestCreateComment, BindingResult bindingResult) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            if (requestCreateComment == null) {
                throw new NotFoundException("request comment null");
            }

            if (newsService.findNewsById(requestCreateComment.getNewsId()) == null) {
                throw new NotFoundException("news not found");
            }

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            ResponseComment responseComment = commentService.saveComment(requestCreateComment);
            apiResponse.ok(responseComment);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/save-reply")
    public ResponseEntity<ApiResponse<ResponseComment>> addReplyComments(
            @Valid @RequestBody RequestCreateComment requestCreateComment, BindingResult bindingResult) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            if (requestCreateComment == null) {
                throw new NotFoundException("request comment null");
            }

            if (newsService.findNewsById(requestCreateComment.getNewsId()) == null) {
                throw new NotFoundException("news not found");
            }

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            ResponseComment responseComment = commentService.saveRepliedComment(requestCreateComment);
            apiResponse.ok(responseComment);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseComment>> editComment(
            @PathVariable int id, @Valid @RequestBody RequestEditComment requestEditComment,
            BindingResult bindingResult) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            requestEditComment.setId(id);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            if (commentService.findCommentById(id) == null) {
                throw new NotFoundException("Comment not found");
            }

            ResponseComment responseComment = commentService.editComment(requestEditComment);
            apiResponse.ok(responseComment);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable int id) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();
            ResponseComment responseComment = commentService.findCommentById(id);
            if (responseComment == null) {
                throw new NotFoundException("Comment not found");
            }
            commentService.removeCommentById(id);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }
}
