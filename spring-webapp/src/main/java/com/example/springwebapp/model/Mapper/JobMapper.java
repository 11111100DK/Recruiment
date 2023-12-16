package com.example.springwebapp.model.Mapper;

import com.example.springwebapp.model.response.ResponseJob.ResponseJob;
import com.example.springwebapp.model.response.ResponseNews.ResponseNews;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class JobMapper {
    public static ResponseJob toResponseJob (Object object) {
        try {
            LinkedHashMap<String, Object> news = (LinkedHashMap<String, Object>) object;

            ResponseJob responseJob = new ResponseJob();
            responseJob.setId((Integer) news.get("id"));
            responseJob.setTitle((String) news.get("title"));
            responseJob.setImage((String) news.get("image"));
            responseJob.setJob((String) news.get("job"));
            responseJob.setEmploymentStatus((String) news.get("employmentStatus")); // Thiết lập giá trị từ dữ liệu news
            responseJob.setCity((String) news.get("city")); // Thiết lập giá trị từ dữ liệu news
            responseJob.setRegion((String) news.get("region")); // Thiết lập giá trị từ dữ liệu news
            responseJob.setLocationDetail((String) news.get("locationDetail")); // Thiết lập giá trị từ dữ liệu news
            responseJob.setVacancy((Integer) news.get("vacancy")); // Thiết lập giá trị từ dữ liệu news
            responseJob.setDateBegin((String) news.get("dateBegin")); // Chuyển đổi giá trị ngày từ dữ liệu news
            responseJob.setDateEnd((String) news.get("dateEnd")); // Chuyển đổi giá trị ngày từ dữ liệu news
            responseJob.setApplicationDeadline((String) news.get("applicationDeadline")); // Chuyển đổi giá trị ngày từ dữ liệu news
            responseJob.setSalary((Double) news.get("salary")); // Thiết lập giá trị từ dữ liệu news
            responseJob.setExperienceYear((Integer) news.get("experienceYear")); // Thiết lập giá trị từ dữ liệu news
            responseJob.setDescription((String) news.get("description")); // Thiết lập giá trị từ dữ liệu news
            responseJob.setResponsibility((String) news.get("responsibility")); // Thiết lập giá trị từ dữ liệu news
            responseJob.setEducation((String) news.get("education")); // Thiết lập giá trị từ dữ liệu news
            responseJob.setBenefit((String) news.get("benefit")); // Thiết lập giá trị từ dữ liệu news
            responseJob.setSkillRequired((String) news.get("skillRequired")); // Thiết lập giá trị từ dữ liệu news
            responseJob.setAccountId((Integer) news.get("accountId")); // Thiết lập giá trị từ dữ liệu news
            responseJob.setCategoryId((Integer) news.get("categoryId")); // Thiết lập giá trị từ dữ liệu news

            return responseJob;
        } catch (Exception e) {
            return null;
        }
    }
}
