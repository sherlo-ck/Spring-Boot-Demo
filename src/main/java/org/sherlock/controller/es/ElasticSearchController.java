package org.sherlock.controller.es;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.sherlock.model.SearchModel;
import org.sherlock.service.ESearchService;
import org.sherlock.utils.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springBoot/es")
@Slf4j
@Api(value = "springDemoEs", tags = {"springDemoEs"})
public class ElasticSearchController {

    @Autowired
    private ESearchService esService;

    @PostMapping("/termQuery")
    public ReturnMsg searchEs(@RequestBody SearchModel searchModel) {
        ReturnMsg returnMsg = esService.search(searchModel);
        return returnMsg;
    }

    @PostMapping("/boolQuery")
    public ReturnMsg boolSearch(@RequestBody SearchModel searchModel) {
        ReturnMsg returnMsg = esService.boolSearch(searchModel);
        return returnMsg;
    }

    @PostMapping("/aggsQuery")
    public ReturnMsg aggsSearch(@RequestBody SearchModel searchModel) {
        ReturnMsg returnMsg = esService.aggsSearch(searchModel);
        return returnMsg;
    }
}
