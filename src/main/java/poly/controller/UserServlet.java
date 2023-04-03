package poly.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import poly.dao.UserDAO;
import poly.model.User;

@WebServlet(urlPatterns = { "/user/index", "/user/edit", "/user/create", "/user/update", "/user/delete",
		"/user/reset" })
public class UserServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		UserDAO userDAO = new UserDAO();
		User user;

		if (uri.contains("delete")) {
			String id = req.getParameter("id");
			userDAO.delete(id);
		} else if (uri.contains("edit")) {
			String id = req.getParameter("id");
			user = userDAO.findByID(id);
			req.setAttribute("user", user);
		}

		req.setAttribute("users", userDAO.findAll());
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		
		String uri = req.getRequestURI();
		UserDAO userDAO = new UserDAO();
		User user;

		if (uri.contains("create")) {
			try {
				user = new User();
				BeanUtils.populate(user, req.getParameterMap());
				userDAO.create(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (uri.contains("update")) {
			try {
				user = new User();
				BeanUtils.populate(user, req.getParameterMap());
				userDAO.update(user);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (uri.contains("delete")) {
			String id = req.getParameter("id");
			userDAO.delete(id);
		}

		req.setAttribute("users", userDAO.findAll());
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
