package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);
    Ad individualAd(long adID);

    List<Ad> searchedAds(String s, String c );

    void editTitle(String title, String adId);

    void editDescription(String description, String adId);

    void deleteAd(String adId);

    List<Ad> getAdsByUser(long user_id);
}
