package com.example.springrestful.service.NewsService;

import com.example.springrestful.model.entity.News.News;
import com.example.springrestful.model.mapper.NewsMapper;
import com.example.springrestful.model.request.RequestNews.RequestNews;
import com.example.springrestful.model.response.ResponseNews.ResponseNews;
import com.example.springrestful.repository.AccountRepository;
import com.example.springrestful.repository.NewsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    AccountRepository accountRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ResponseNews saveNews(RequestNews requestNews) throws Exception {
        News news = new News();
        news.setId(requestNews.getId());
        news.setTitle(requestNews.getTitle());
        news.setContent(requestNews.getContent());
        news.setThumbnail(requestNews.getThumbnail());
        var accountId = requestNews.getAccountId();
        news.setAccount(accountRepository.findById(accountId));
        news.setDateCreated(new Date());
        news.setDateUpdated(new Date());

        return newsMapper.toResponseNews(newsRepository.save(news));
    }

    @Override
    public ResponseNews editNews(RequestNews requestNews) throws Exception {
        News news = newsRepository.findById(requestNews.getId());
        news.setTitle(requestNews.getTitle());
        news.setContent(requestNews.getContent());
        news.setThumbnail(requestNews.getThumbnail());
        news.setDateUpdated(new Date());

        return newsMapper.toResponseNews(newsRepository.save(news));
    }

    @Override
    public void removeNewsById(int id) throws Exception {
        News news = newsRepository.findById(id);
        news.setStatus("inactive");
        newsRepository.save(news);
    }

    @Override
    public ResponseNews findNewsById(int id) throws Exception {
        return newsMapper.toResponseNews(newsRepository.findById(id));
    }

    @Override
    public List<ResponseNews> findAllNews() {
        return newsMapper.toResponseNewsList(newsRepository.findAll());
    }

    @Override
    public List<ResponseNews> findAllNewsByAccountId(int accountId, int limit, int offset) throws Exception {
        String jpql = "SELECT n FROM News n WHERE n.account.id = :accountId";

        Query query = entityManager.createQuery(jpql);

        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);
        query.setParameter("accountId", accountId);

        return newsMapper.toResponseNewsList((List<News>) query.getResultList());
    }

    @Override
    public List<ResponseNews> findAllNewsByAccountIdAndTitle(int accountId, int limit, int offset, String title) throws Exception {
        String jpql = "SELECT n FROM News n WHERE n.account.id = :accountId AND n.title LIKE :title";

        Query query = entityManager.createQuery(jpql);

        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);
        query.setParameter("accountId", accountId);
        query.setParameter("title", "%" + title + "%");

        return newsMapper.toResponseNewsList((List<News>) query.getResultList());
    }

    @Override
    public List<ResponseNews> findAllNewsByAccountIdTopPopular(int accountId, int limit, int offset) throws Exception {
        String jpql = "SELECT n FROM News n WHERE n.account.id = :accountId ORDER BY n.viewNumber DESC ";

        Query query = entityManager.createQuery(jpql);

        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);
        query.setParameter("accountId", accountId);

        return newsMapper.toResponseNewsList((List<News>) query.getResultList());
    }

    @Override
    public List<ResponseNews> findAllNewsByAccountIdTopPopularAndTitle(int accountId, int limit, int offset, String title) throws Exception {
        String jpql = "SELECT n FROM News n WHERE n.account.id = :accountId AND n.title LIKE :title ORDER BY n.viewNumber DESC";

        Query query = entityManager.createQuery(jpql);

        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);
        query.setParameter("accountId", accountId);
        query.setParameter("title", "%" + title + "%");

        return newsMapper.toResponseNewsList((List<News>) query.getResultList());
    }

    @Override
    public List<ResponseNews> findAllNewsByAccountIdTopNewest(int accountId, int limit, int offset) throws Exception {
        String jpql = "SELECT n FROM News n WHERE n.account.id = :accountId ORDER BY n.dateCreated DESC";

        Query query = entityManager.createQuery(jpql);

        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);
        query.setParameter("accountId", accountId);

        return newsMapper.toResponseNewsList((List<News>) query.getResultList());
    }

    @Override
    public List<ResponseNews> findAllNewsByAccountIdTopNewestAndTitle(int accountId, int limit, int offset, String title) throws Exception {
        String jpql = "SELECT n FROM News n WHERE n.account.id = :accountId AND n.title LIKE :title ORDER BY n.dateCreated DESC";

        Query query = entityManager.createQuery(jpql);

        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);
        query.setParameter("accountId", accountId);
        query.setParameter("title", "%" + title + "%");

        return newsMapper.toResponseNewsList((List<News>) query.getResultList());
    }

    @Override
    public List<ResponseNews> findAllNewsLimit(int limit, int offset) throws Exception {
        // Use the JPQL string directly instead of repository method
        String jpql = "SELECT n FROM News n";

        Query query = entityManager.createQuery(jpql);

        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);

        return newsMapper.toResponseNewsList((List<News>) query.getResultList());
    }

    @Override
    public List<ResponseNews> findByTitleLimit(String title, int limit, int offset) throws Exception {
        String jpql = "SELECT n FROM News n WHERE n.title LIKE :title";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("title", "%" + title + "%");
        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);

        return newsMapper.toResponseNewsList((List<News>) query.getResultList());
    }

    @Override
    public List<ResponseNews> findTopPopular(int limit, int offset) throws Exception {
        String jpql = "SELECT n FROM News n ORDER BY n.viewNumber DESC";

        Query query = entityManager.createQuery(jpql);

        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);

        return newsMapper.toResponseNewsList((List<News>) query.getResultList());
    }

    @Override
    public List<ResponseNews> findTopPopularAndTitle(String title, int limit, int offset) throws Exception {
        String jpql = "SELECT n FROM News n WHERE n.title LIKE :title ORDER BY n.viewNumber DESC ";

        Query query = entityManager.createQuery(jpql);

        query.setParameter("title", "%" + title + "%");
        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);

        return newsMapper.toResponseNewsList((List<News>) query.getResultList());
    }

    @Override
    public List<ResponseNews> findTopNewest(int limit, int offset) throws Exception {
        String jpql = "SELECT n FROM News n ORDER BY n.dateCreated DESC";

        Query query = entityManager.createQuery(jpql);

        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);

        return newsMapper.toResponseNewsList((List<News>) query.getResultList());
    }

    @Override
    public List<ResponseNews> findTopNewestAndTitle(String title, int limit, int offset) throws Exception {
        String jpql = "SELECT n FROM News n WHERE n.title LIKE :title ORDER BY n.dateCreated DESC ";

        Query query = entityManager.createQuery(jpql);

        query.setParameter("title", "%" + title + "%");
        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);

        return newsMapper.toResponseNewsList((List<News>) query.getResultList());
    }

    @Override
    public ResponseNews plusNewsViewById(int id) throws Exception {
        News news = newsRepository.findById(id);
        if (news == null) return null;

        news.setViewNumber(news.getViewNumber() + 1);
        return newsMapper.toResponseNews(newsRepository.save(news));
    }
}
