package poly.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import poly.dao.UserDAO;
import poly.model.User;

@WebServlet({ "/account/sign-in", "/account/sign-up", "/account/sign-out", "/account/forgot-password",
		"/account/change-password", "/account/edit-profile" })
public class AccountServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();

		if (uri.contains("sign-in")) {
			this.doSignIn(req, resp);
		} else if (uri.contains("sign-up")) {
			this.doSignUp(req, resp);
		} else if (uri.contains("forgot-password")) {

		} else if (uri.contains("change-password")) {

		} else {
			this.doEditProfile(req, resp);
		}

	}

	private void doSignIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			String id = req.getParameter("id");
			String password = req.getParameter("password");

			UserDAO userDAO = new UserDAO();
			boolean check = userDAO.checkLogin(id, password);

			if (check == true) {
				req.getRequestDispatcher("/views/edit-profile.jsp").forward(req, resp);
			} else {
				req.setAttribute("message", "Đăng nhập thất bại nhá!");
			}
		}

		req.getRequestDispatcher("/views/sign-in.jsp").forward(req, resp);
	}

	private void doSignUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String method = req.getMethod();

		if (method.equalsIgnoreCase("POST")) {
			String id = req.getParameter("id");

			User user = new User();
			UserDAO dao = new UserDAO();
			List<User> users = new ArrayList<>();
			users = dao.findAll();

			users.forEach(u -> {
				if (id == u.getId()) {
					req.setAttribute("message", "Đăng ký thất bại!");
					return;
				} else {
					try {
						BeanUtils.populate(user, req.getParameterMap());
						dao.create(user);
						req.setAttribute("message", "Đăng ký thành công!");
						return;
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}

		req.getRequestDispatcher("/views/sign-up.jsp").forward(req, resp);
	}

	private void doEditProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String method = req.getMethod();

		if (method.equalsIgnoreCase("POST")) {
			User user = new User();
			try {
				BeanUtils.populate(user, req.getParameterMap());
				UserDAO dao = new UserDAO();
				dao.update(user);
				req.setAttribute("message", "Cập nhật tài khoản thành công!");
			} catch (Exception e) {
				req.setAttribute("message", "Lỗi cập nhật tài khoản!");
			}
		}

		req.getRequestDispatcher("/views/edit-profile.jsp").forward(req, resp);
	}
}
