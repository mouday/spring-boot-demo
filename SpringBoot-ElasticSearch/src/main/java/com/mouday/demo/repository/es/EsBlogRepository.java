package com.mouday.demo.repository.es;

import com.mouday.demo.entity.es.ESBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsBlogRepository extends ElasticsearchRepository<ESBlog, Integer> {
}
