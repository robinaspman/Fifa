package com.example.fifa;

import com.example.fifa.models.FixtureResponse;

public interface ResponseListener {
    void didFetch(FixtureResponse response, String message);
    void didError(String message);
}
