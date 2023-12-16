package com.example.springrestful.service.NewsService;

import com.example.springrestful.model.entity.News.Comment;
import com.example.springrestful.model.mapper.CommentMapper;
import com.example.springrestful.model.request.RequestNews.RequestCreateComment;
import com.example.springrestful.model.request.RequestNews.RequestEditComment;
import com.example.springrestful.model.response.ResponseNews.ResponseComment;
import com.example.springrestful.repository.AccountRepository;
import com.example.springrestful.repository.CommentRepository;
import com.example.springrestful.repository.NewsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    CommentMapper commentMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ResponseComment saveComment(RequestCreateComment requestCreateComment) throws Exception {
        Comment comment = new Comment();
        comment.setContent(requestCreateComment.getContent());
        comment.setParentCommentId(0);
        comment.setDateCreated(new Date());
        comment.setDateUpdated(new Date());
        comment.setAccount(newsRepository.findById(requestCreateComment.getNewsId()).getAccount());
        comment.setNews(newsRepository.findById(requestCreateComment.getNewsId()));
        comment.setAccount(accountRepository.findById(requestCreateComment.getAccountId()));
        return commentMapper.toResponseComment(commentRepository.save(comment));
    }

    @Override
    public ResponseComment saveRepliedComment(RequestCreateComment requestCreateComment) throws Exception {
        Comment comment = new Comment();
        comment.setContent(requestCreateComment.getContent());
        comment.setParentCommentId(requestCreateComment.getParentCommentId());
        comment.setDateCreated(new Date());
        comment.setDateUpdated(new Date());
        comment.setAccount(newsRepository.findById(requestCreateComment.getNewsId()).getAccount());
        comment.setNews(newsRepository.findById(requestCreateComment.getNewsId()));
        comment.setAccount(accountRepository.findById(requestCreateComment.getAccountId()));
        return commentMapper.toResponseComment(commentRepository.save(comment));
    }

    @Override
    public ResponseComment editComment(RequestEditComment requestEditComment) throws Exception {
        Comment comment = commentRepository.findById(requestEditComment.getId());
        comment.setContent(requestEditComment.getContent());
        comment.setDateUpdated(new Date());
        return commentMapper.toResponseComment(commentRepository.save(comment));
    }

    @Override
    public void removeCommentById(int id) throws Exception {
        commentRepository.deleteById(id);
    }

    @Override
    public ResponseComment findCommentById(int id) throws Exception {
        return commentMapper.toResponseComment(commentRepository.findById(id));
    }

    @Override
    public List<ResponseComment> findAllComments() throws Exception {
        return commentMapper.toResponseCommentList(commentRepository.findAll());
    }

    @Override
    public List<ResponseComment> findCommentsByNewsId(int id) throws Exception {
        return commentMapper.toResponseCommentList(commentRepository.findByNewsId(id));
    }

    @Override
    public List<ResponseComment> findCommentsByNewsIdLimit(int id, int limit) throws Exception {
        String jpql = "SELECT c FROM Comment c Where c.news.id = :newsId AND c.parentCommentId = 0 ORDER BY c.dateUpdated DESC";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("newsId", id);
        query.setMaxResults(limit);

        return commentMapper.toResponseCommentList((List<Comment>) query.getResultList());
    }

    @Override
    public List<ResponseComment> findNewestCommentsLimit(int limit) throws Exception {
        String jpql = """
                SELECT c from Comment c\s
                ORDER BY c.dateUpdated DESC, c.parentCommentId
                """;

        Query query = entityManager.createQuery(jpql);
        query.setMaxResults(limit);

        return commentMapper.toResponseCommentList((List<Comment>) query.getResultList());
    }

    @Override
    public List<ResponseComment> findReplyCommentsByParentCommentId(int id) throws Exception {
        return commentMapper.toResponseCommentList(commentRepository.findByParentId(id));
    }

    @Override
    public List<ResponseComment> findReplyCommentsByParentCommentLimit(int id, int limit) throws Exception {
        String jpql = """
                SELECT c from Comment c\s
                WHERE c.parentCommentId = :id
                """;
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        query.setMaxResults(limit);

        return commentMapper.toResponseCommentList((List<Comment>) query.getResultList());
    }

    @Override
    public ResponseComment plusCommentLikeById(int id) throws Exception {
        Comment comment = commentRepository.findById(id);
        if (comment != null) {
            comment.setLikeNumber(comment.getLikeNumber() + 1);
            return commentMapper.toResponseComment(commentRepository.save(comment));
        }
        return null;
    }

    @Override
    public ResponseComment removeCommentLikeById(int id) throws Exception {
        Comment comment = commentRepository.findById(id);
        if (comment != null) {
            comment.setLikeNumber(comment.getLikeNumber() - 1);
            return commentMapper.toResponseComment(commentRepository.save(comment));
        }
        return null;
    }
}
