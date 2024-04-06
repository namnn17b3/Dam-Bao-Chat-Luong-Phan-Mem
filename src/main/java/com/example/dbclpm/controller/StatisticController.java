package com.example.dbclpm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dbclpm.dao.StatisticDao;

import com.example.dbclpm.dao.impl.StatisticDaoImpl;
import com.example.dbclpm.model.Term;

@WebServlet("/statistic")
public class StatisticController extends HttpServlet {
	private StatisticDao statisticDao;
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatisticDao statisticDao = new StatisticDaoImpl();
		List<Term> terms = statisticDao.getTerms();
		req.getSession().setAttribute("terms", terms);
        req.getRequestDispatcher("/statistic.jsp").forward(req, resp);
	}

}
