package poly.controller;



import java.util.List;

import javax.transaction.Transactional;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.entity.Users;

@Controller
@Transactional
public class logincontroller {
	@Autowired
	SessionFactory sf;
@RequestMapping("login")
public String login() {
	return "login";
}

@RequestMapping("index")
public String showindex(ModelMap model, 
		@RequestParam(value = "username") String username,
		@RequestParam(value = "password") String password) {
	Session session = sf.getCurrentSession();
	String sql = "FROM Users WHERE username LIKE " + "'" + username + "'" + " and password LIKE " + "'" + password+ "'";
	Query query = session.createQuery(sql);
	List<Users> list = query.list();
	if (list.size() > 0) {
		return "index";
	} else {
		model.addAttribute("thongbao", "dang nhap that bai");
		return "login";
	}

}
}
