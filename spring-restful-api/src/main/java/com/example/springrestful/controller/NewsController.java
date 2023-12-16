package com.example.springrestful.controller;

import com.example.springrestful.exception.ApplicationException;
import com.example.springrestful.exception.NotFoundException;
import com.example.springrestful.exception.ValidationException;
import com.example.springrestful.model.request.RequestNews.RequestNews;
import com.example.springrestful.model.request.RequestNews.RequestPaginationNews;
import com.example.springrestful.model.response.ApiResponse.ApiResponse;
import com.example.springrestful.model.response.ApiResponse.StatusEnum;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;
import com.example.springrestful.model.response.ResponseNews.ResponseNews;
import com.example.springrestful.service.AccountService.AccountService;
import com.example.springrestful.service.NewsService.NewsService;
import com.example.springrestful.util.ValidatorUtil;
import com.example.springrestful.validator.PaginationValidator.PaginationValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    NewsService newsService;
    @Autowired
    AccountService accountService;
    @Autowired
    ValidatorUtil validatorUtil;
    @Autowired
    PaginationValidator paginationValidator;

    @GetMapping(value = "")
    public ResponseEntity<ApiResponse<List<ResponseNews>>> returnAllNews() throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.ok(newsService.findAllNews());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<ResponseNews>> returnNewsById(@PathVariable int id) throws Exception {
        try {
            if (newsService.findNewsById(id) == null) {
                throw new NotFoundException("News not found");
            }
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(newsService.findNewsById(id));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<ResponseNews>> addNews(@Valid @RequestBody RequestNews requestNews, BindingResult bindingResult) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            if (requestNews == null) {
                throw new NotFoundException("News not found");
            }

            ResponseNews responseNews = newsService.saveNews(requestNews);
            apiResponse.ok(responseNews);
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
    public ResponseEntity<ApiResponse<ResponseNews>> editNews(
            @PathVariable int id, @Valid @RequestBody RequestNews requestNews,
            BindingResult bindingResult) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            requestNews.setId(id);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            if (newsService.findNewsById(id) == null) {
                throw new NotFoundException("News not found");
            }

            ResponseNews responseNews = newsService.editNews(requestNews);
            apiResponse.ok(responseNews);
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
    public ResponseEntity<ApiResponse> deleteNews(@PathVariable int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            ResponseNews responseNews = newsService.findNewsById(id);
            if (responseNews == null || !responseNews.getStatus().equals("active")) {
                throw new NotFoundException("News not found");
            }
            newsService.removeNewsById(id);
            apiResponse.ok("Delete successfully");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/pagination/{accountId}")
    public ResponseEntity<ApiResponse<List<ResponseNews>>> returnNewsByAccountId(
            @PathVariable int accountId,
            @Valid @RequestBody RequestPaginationNews requestPaginationNews,
            BindingResult bindingResult) throws Exception {

        ApiResponse apiResponse = new ApiResponse();
        try {
            paginationValidator.validate(requestPaginationNews, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }
            if (accountService.findAccountById(accountId) == null) {
                throw new NotFoundException("Account not found");
            }

            apiResponse.ok(newsService.findAllNewsByAccountId(accountId, requestPaginationNews.getLimit(), requestPaginationNews.getOffset()));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/pagination/{accountId}/{title}")
    public ResponseEntity<ApiResponse<List<ResponseNews>>> returnNewsByAccountIdAndTitle(
            @PathVariable int accountId,
            @PathVariable String title,
            @Valid @RequestBody RequestPaginationNews requestPaginationNews,
            BindingResult bindingResult) throws Exception {

        ApiResponse apiResponse = new ApiResponse();
        try {
            paginationValidator.validate(requestPaginationNews, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }
            if (accountService.findAccountById(accountId) == null) {
                throw new NotFoundException("Account not found");
            }

            apiResponse.ok(newsService.findAllNewsByAccountIdAndTitle(accountId, requestPaginationNews.getLimit(), requestPaginationNews.getOffset(), title));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/pagination/popular/account/{accountId}")
    public ResponseEntity<ApiResponse<List<ResponseNews>>> returnNewsByAccountIdPopular(
            @PathVariable int accountId,
            @Valid @RequestBody RequestPaginationNews requestPaginationNews,
            BindingResult bindingResult) throws Exception {

        ApiResponse apiResponse = new ApiResponse();
        try {
            paginationValidator.validate(requestPaginationNews, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }
            if (accountService.findAccountById(accountId) == null) {
                throw new NotFoundException("Account not found");
            }

            apiResponse.ok(newsService.findAllNewsByAccountIdTopPopular(accountId, requestPaginationNews.getLimit(), requestPaginationNews.getOffset()));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/pagination/popular/account/{accountId}/{title}")
    public ResponseEntity<ApiResponse<List<ResponseNews>>> returnNewsByAccountIdPopularAndTitle(
            @PathVariable int accountId,
            @PathVariable String title,
            @Valid @RequestBody RequestPaginationNews requestPaginationNews,
            BindingResult bindingResult) throws Exception {

        ApiResponse apiResponse = new ApiResponse();
        try {
            paginationValidator.validate(requestPaginationNews, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }
            if (accountService.findAccountById(accountId) == null) {
                throw new NotFoundException("Account not found");
            }

            apiResponse.ok(newsService.findAllNewsByAccountIdTopPopularAndTitle(accountId, requestPaginationNews.getLimit(), requestPaginationNews.getOffset(), title));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/pagination/newest/account/{accountId}")
    public ResponseEntity<ApiResponse<List<ResponseNews>>> returnNewsByAccountIdNewest(
            @PathVariable int accountId,
            @Valid @RequestBody RequestPaginationNews requestPaginationNews,
            BindingResult bindingResult) throws Exception {

        ApiResponse apiResponse = new ApiResponse();
        try {
            paginationValidator.validate(requestPaginationNews, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }
            if (accountService.findAccountById(accountId) == null) {
                throw new NotFoundException("Account not found");
            }

            apiResponse.ok(newsService.findAllNewsByAccountIdTopNewest(accountId, requestPaginationNews.getLimit(), requestPaginationNews.getOffset()));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/pagination/newest/account/{accountId}/{title}")
    public ResponseEntity<ApiResponse<List<ResponseNews>>> returnNewsByAccountIdNewestAndTitle(
            @PathVariable int accountId,
            @PathVariable String title,
            @Valid @RequestBody RequestPaginationNews requestPaginationNews,
            BindingResult bindingResult) throws Exception {

        ApiResponse apiResponse = new ApiResponse();
        try {
            paginationValidator.validate(requestPaginationNews, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }
            if (accountService.findAccountById(accountId) == null) {
                throw new NotFoundException("Account not found");
            }

            apiResponse.ok(newsService.findAllNewsByAccountIdTopNewestAndTitle(accountId, requestPaginationNews.getLimit(), requestPaginationNews.getOffset(), title));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }


    @PostMapping("/pagination")
    public ResponseEntity<ApiResponse<List<ResponseNews>>> returnNewsLimits(
            @Valid @RequestBody RequestPaginationNews requestPaginationNews,
            BindingResult bindingResult) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            paginationValidator.validate(requestPaginationNews, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            apiResponse.ok(newsService.findAllNewsLimit(requestPaginationNews.getLimit(), requestPaginationNews.getOffset()));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/pagination/popular")
    public ResponseEntity<ApiResponse<List<ResponseNews>>> returnNewsPopular(
            @Valid @RequestBody RequestPaginationNews requestPaginationNews,
            BindingResult bindingResult) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            paginationValidator.validate(requestPaginationNews, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            apiResponse.ok(newsService.findTopPopular(requestPaginationNews.getLimit(), requestPaginationNews.getOffset()));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/pagination/popular/{title}")
    public ResponseEntity<ApiResponse<List<ResponseNews>>> returnNewsPopularAndTitle(
            @Valid @RequestBody RequestPaginationNews requestPaginationNews,
            @PathVariable String title,
            BindingResult bindingResult) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            paginationValidator.validate(requestPaginationNews, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            apiResponse.ok(newsService.findTopPopularAndTitle(title, requestPaginationNews.getLimit(), requestPaginationNews.getOffset()));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/pagination/newest")
    public ResponseEntity<ApiResponse<List<ResponseNews>>> returnTopNewest(
            @Valid @RequestBody RequestPaginationNews requestPaginationNews,
            BindingResult bindingResult) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            paginationValidator.validate(requestPaginationNews, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            apiResponse.ok(newsService.findTopNewest(requestPaginationNews.getLimit(), requestPaginationNews.getOffset()));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/pagination/newest/{title}")
    public ResponseEntity<ApiResponse<List<ResponseNews>>> returnTopNewestAndTitle(
            @Valid @RequestBody RequestPaginationNews requestPaginationNews,
            @PathVariable String title,
            BindingResult bindingResult) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            paginationValidator.validate(requestPaginationNews, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            apiResponse.ok(newsService.findTopNewestAndTitle(title, requestPaginationNews.getLimit(), requestPaginationNews.getOffset()));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/pagination/title/{title}")
    public ResponseEntity<ApiResponse<List<ResponseNews>>> returnNewsByTitle(
            @PathVariable String title,
            @Valid @RequestBody RequestPaginationNews requestPaginationNews,
            BindingResult bindingResult) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            paginationValidator.validate(requestPaginationNews, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            apiResponse.ok(newsService.findByTitleLimit(title, requestPaginationNews.getLimit(), requestPaginationNews.getOffset()));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/add-view/{id}")
    public ResponseEntity<ApiResponse<ResponseNews>> addView(@PathVariable int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (newsService.findNewsById(id) == null) {
                throw new NotFoundException("News not found");
            }

            apiResponse.ok(newsService.plusNewsViewById(id));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }
}
