package com.selimsahin.recommendationservice.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * @author selimsahindev
 */
@Configuration
@EnableSolrRepositories(
        basePackages = "com.selimsahin.recommendationservice"
)
@ComponentScan
public class SolrConfig {

    @Value("${spring.data.solr.host}")
    private String baseSolrUrl;

    @Bean
    public SolrClient solrClient(){
        return new HttpSolrClient.Builder(baseSolrUrl).build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient solrClient){
        return new SolrTemplate(solrClient);
    }
}
