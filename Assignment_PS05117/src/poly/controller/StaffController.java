package poly.controller;

import java.util.List;

import poly.entity.Departs;
import poly.entity.Staffs;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Transactional
@RequestMapping("tables1")
public class StaffController {
	@Autowired
	SessionFactory factory;

	@ModelAttribute("staffs")
	public List<Staffs> getStaff1() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staffs";
		Query query = session.createQuery(hql);
		List<Staffs> list = query.list();
		return list;
	}

	@RequestMapping()
	public String gotoStaff(ModelMap model) {
		model.addAttribute("staff", new Staffs());
		return "tables1";
	}

	@RequestMapping(params = "insert")
	public String insert(ModelMap model, @ModelAttribute("staff") Staffs staff) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(staff);
			transaction.commit();
			model.addAttribute("message", "Insert successfully !");
		} catch (Exception e) {
			model.addAttribute("message", "Insert fails !");
			transaction.rollback();
		}
		session.close();
		model.addAttribute("staff", new Staffs());
		model.addAttribute("staffs", getStaff1());
		return "tables1";
	}

	@RequestMapping(params = "update")
	public String update(ModelMap model, @ModelAttribute("staff") Staffs staff) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(staff);
			transaction.commit();
			model.addAttribute("message", "Update successfully !");
		} catch (Exception e) {
			model.addAttribute("message", "Update fails !");
			transaction.rollback();
		}
		session.close();
		model.addAttribute("staffs", getStaff1());
		return "tables1";
	}

	@RequestMapping(params = "delete")
	public String delete(ModelMap model, Staffs staff) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(staff);
			transaction.commit();
			model.addAttribute("message", "Delete successfully !");
		} catch (Exception e) {
			model.addAttribute("message", "Delete fails !");
			transaction.rollback();
		}
		session.close();

		model.addAttribute("staff", new Staffs());
		model.addAttribute("staffs", getStaff1());
		return "tables1";
	}

	@RequestMapping(params = "lnkEdit")
	public String edit(ModelMap model, @RequestParam("id") String id) {
		Session session = factory.getCurrentSession();
		Staffs staff = (Staffs) session.get(Staffs.class, id);
		model.addAttribute("staff", staff);
		return "tables1";
	}

	@ModelAttribute("departs")
	public List<Departs> getDepart() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Departs";
		Query query = session.createQuery(hql);
		List<Departs> list = query.list();
		return list;
	}
}
