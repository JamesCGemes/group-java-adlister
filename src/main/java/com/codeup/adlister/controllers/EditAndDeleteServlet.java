package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "EditAndDeleteServlet", urlPatterns = "/ads/editAd")
public class EditAndDeleteServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String adId = request.getParameter("ad-id");
        String editTitle = request.getParameter("titleInput");
        String editDescription = request.getParameter("descriptionInput");
        String deleteAd = request.getParameter("deleteAd");

        if (editTitle != null && !editTitle.isEmpty()) {
            DaoFactory.getAdsDao().editTitle(editTitle, adId);
        }

        if (editDescription != null && !editDescription.isEmpty()) {
            DaoFactory.getAdsDao().editDescription(editDescription, adId);
        }

        if (deleteAd != null && !deleteAd.isEmpty()) {
            DaoFactory.getAdsDao().deleteAd(adId);
        }
    }


}
