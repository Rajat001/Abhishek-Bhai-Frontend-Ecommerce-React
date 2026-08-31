package com.dev.service;

import com.dev.model.Home;
import com.dev.model.HomeCategory;

import java.util.List;

public interface HomeService {
    public Home createHomePageData(List<HomeCategory> allCategories);
}
