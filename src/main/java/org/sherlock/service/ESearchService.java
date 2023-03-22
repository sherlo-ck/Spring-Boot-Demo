package org.sherlock.service;

import org.sherlock.model.SearchModel;
import org.sherlock.utils.ReturnMsg;

public interface ESearchService {
    ReturnMsg search(SearchModel searchModel);

    ReturnMsg aggsSearch(SearchModel searchModel);

    ReturnMsg boolSearch(SearchModel searchModel);
}
