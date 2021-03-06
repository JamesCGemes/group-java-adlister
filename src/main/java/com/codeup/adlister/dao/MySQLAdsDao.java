package com.codeup.adlister.dao;
import com.codeup.adlister.dao.Config;
import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(

                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");

            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    @Override
    public Ad individualAd(long adID) {
        String query = "SELECT * FROM ads WHERE id = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setLong(1, adID);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return extractAd(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving specific add", e);
        }
    }

    @Override
    public List<Ad> searchedAds(String searchInput, String searchCat) {
        System.out.println("searchInput = " + searchInput);
        System.out.println("searchCat = " + searchCat);
//        PreparedStatement pst = null;
//        try {
//            pst = connection.prepareStatement("SELECT *   FROM ads JOIN users ON ads.user_id = users.id JOIN pivot_categories pc     ON ads.id = pc.ads_id   JOIN categories c     ON pc.categories_id = c.id   join pivot_media     on ads.id = pivot_media.ad_id   join media     on pivot_media.media_id = media.id WHERE ads.title LIKE  ?  AND c.category_name = ?");
//            pst.setString(1,"%" + searchInput + "%");
//            pst.setString(2, searchCat);
//            ResultSet rs = pst.executeQuery();
//            return createAdsForMain(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error retrieving matching ads.", e);
//        }
//      delete after method has been finished
        return null;
    }



    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")

        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
}
