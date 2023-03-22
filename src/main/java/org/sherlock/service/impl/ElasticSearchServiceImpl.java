package org.sherlock.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.sherlock.model.SearchModel;
import org.sherlock.service.ESearchService;
import org.sherlock.utils.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ElasticSearchServiceImpl implements ESearchService {
    @Autowired
    @Qualifier("myRestHighLevelClient")
    private RestHighLevelClient client;

    @Override
    public ReturnMsg search(SearchModel searchModel) {
        // 构建查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("Name.keyword", searchModel.getName()));
        // 创建查询请求对象
        SearchRequest index = new SearchRequest("index_db2");
        index.source(searchSourceBuilder);
        // 执行查询
        SearchResponse search = new SearchResponse();
        try {
            search = client.search(index);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<SearchModel> searchModels = new ArrayList<>();
        for (SearchHit hit : search.getHits()) {
            SearchModel searchModelhit = JSONObject.parseObject(JSONObject.toJSONString(hit.getSourceAsMap()), SearchModel.class);
            searchModels.add(searchModelhit);
        }
        return new ReturnMsg(searchModels);
    }

    @Override
    public ReturnMsg aggsSearch(SearchModel searchModel) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsAggregationBuilder id = AggregationBuilders.terms("aggs_Id").field("Id");
        searchSourceBuilder.aggregation(id);
        SearchRequest index = new SearchRequest("index_db2");
        index.source(searchSourceBuilder);
        SearchResponse search = new SearchResponse();
        try {
            search= client.search(index);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Aggregations aggregations = search.getAggregations();
        Terms aggsId = aggregations.get("aggs_Id");
        List<? extends Terms.Bucket> buckets = aggsId.getBuckets();
        Map<String, Long> bucketsMap = new HashMap<>();
        for (Terms.Bucket bucket : buckets) {
            bucketsMap.put(bucket.getKeyAsString(),bucket.getDocCount());
        }
        return new ReturnMsg(bucketsMap);
    }

    @Override
    public ReturnMsg boolSearch(SearchModel searchModel) {
        // 构造准备
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest();
        searchSourceBuilder.trackScores(true);
        // 构造检索条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // Id > 2
        if (searchModel.getId() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("Id").gt(searchModel.getId()));
        }
        //
        return null;
    }
}
