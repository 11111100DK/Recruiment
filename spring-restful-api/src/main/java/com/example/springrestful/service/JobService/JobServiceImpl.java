package com.example.springrestful.service.JobService;

import com.example.springrestful.model.entity.Job.Job;
import com.example.springrestful.model.mapper.JobMapper;
import com.example.springrestful.model.request.RequestJob.RequestJob;
import com.example.springrestful.model.response.ResponseJob.ResponseJob;
import com.example.springrestful.repository.AccountRepository;
import com.example.springrestful.repository.CategoryRepository;
import com.example.springrestful.repository.JobRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService{
    @Autowired
    JobMapper jobMapper;
    @Autowired
    JobRepository jobRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ResponseJob saveJob(RequestJob requestJob) throws Exception {
        Job job = new Job();
        job.setTitle(requestJob.getTitle());
        job.setImage(requestJob.getImage());
        job.setJob(requestJob.getJob());
        job.setEmploymentStatus(requestJob.getEmploymentStatus());
        job.setCity(requestJob.getCity());
        job.setRegion(requestJob.getRegion());
        job.setLocationDetail(requestJob.getLocationDetail());
        job.setVacancy(requestJob.getVacancy());
        job.setDateBegin(requestJob.getDateBegin());
        job.setDateEnd(requestJob.getDateEnd());
        job.setApplicationDeadline(requestJob.getApplicationDeadline());
        job.setSalary(requestJob.getSalary());
        job.setExperienceYear(requestJob.getExperienceYear());
        job.setDescription(requestJob.getDescription());
        job.setResponsibility(requestJob.getResponsibility());
        job.setEducation(requestJob.getEducation());
        job.setBenefit(requestJob.getBenefit());
        job.setSkillRequired(requestJob.getSkillRequired());
        job.setAccount(accountRepository.findById(requestJob.getAccountId()));
        job.setCategory(categoryRepository.findById(requestJob.getCategoryId()));

        return jobMapper.toResponseJob(jobRepository.save(job));
    }

    @Override
    public void removeJobById(int id) throws Exception {
        jobRepository.deleteById(id);
    }

    @Override
    public List<ResponseJob> findAllJobs() throws Exception {
        return jobMapper.toResponseJobList(jobRepository.findAll());
    }

    @Override
    public ResponseJob findJobById(int id) throws Exception {
        return jobMapper.toResponseJob(jobRepository.findById(id));
    }

    @Override
    public List<ResponseJob> findRandomSomeJobs(int limit) throws Exception {
        // JPQL query to retrieve random jobs with a limit
        String jpql = "SELECT j FROM Job j ORDER BY FUNCTION('RAND')";

        Query query = entityManager.createQuery(jpql, Job.class);
        query.setMaxResults(limit); // Set the maximum number of results

        return jobMapper.toResponseJobList((List<Job>)query.getResultList());
    }

    @Override
    public List<ResponseJob> findJobByCriteria(String title, String region, String category) throws Exception {
        String jpql = "SELECT j FROM Job j JOIN j.category c WHERE 1=1";

        if (title != null && !title.isEmpty()) {
            jpql += " AND j.title LIKE :title";
        }
        if (region != null && !region.isEmpty()) {
            jpql += " AND j.city LIKE :region";
        }
        if (category != null && !category.isEmpty()) {
            jpql += " AND c.title LIKE :category";
        }

        Query query = entityManager.createQuery(jpql, Job.class);

        // Set parameters if they are not null
        if (title != null && !title.isEmpty()) {
            query.setParameter("title", "%" + title + "%");
        }
        if (region != null && !region.isEmpty()) {
            query.setParameter("region", "%" + region + "%");
        }
        if (category != null && !category.isEmpty()) {
            query.setParameter("category", "%" + category + "%");
        }

        return jobMapper.toResponseJobList((List<Job>) query.getResultList());

    }
}
