package org.sherlock.service;

import org.apache.http.NoHttpResponseException;

public interface RetryService {
    void callPost() throws NoHttpResponseException;

}
