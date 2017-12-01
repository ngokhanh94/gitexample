package poly.controller;

import java.util.List;

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

import poly.entity.Departs;



	@Controller
	@Transactional
	@RequestMapping("test")
	public class testController {
		@Autowired
		SessionFactory sf;
		
		@ModelAttribute("departs")
		public List<Departs> getDepart1(){
			Session session = sf.getCurrentSession();
			String hql ="FROM Departs";
			Query query=session.createQuery(hql);
			List<Departs> list = query.list();
			return list;
	}
		
		@RequestMapping()
		public String gotoDepart(ModelMap model) {
			model.addAttribute("depart", new Departs());
			return "test";
		}
		
		
		@RequestMapping(params="insert") 
		public String insert(ModelMap model,
							@ModelAttribute("depart") Departs depart) {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction(); 
		try {
			session.save(depart);
			transaction.commit(); 
			model.addAttribute("message", "Insert successfully !");
		}
		catch (Exception e) {
			model.addAttribute("message", "Insert fails !"); 
			transaction.rollback();
		} session.close();
		model.addAttribute("depart", new Departs()); 
		model.addAttribute("departs", getDepart1());
		return "test";
	}

	@RequestMapping(params="update") 
			public String update(ModelMap model,
								@ModelAttribute("depart") Departs depart) {
				Session session = sf.openSession();
				Transaction transaction = session.beginTransaction(); 
				try {
					session.update(depart);
					transaction.commit();
					model.addAttribute("message", "Update successfully !");
				}
				catch (Exception e) {
					model.addAttribute("message", "Update fails !"); 
					transaction.rollback();
				} session.close();
				model.addAttribute("depart", getDepart1()); 
				return "test";
			} 
			
			@RequestMapping(params="delete")
			public String delete(ModelMap model,Departs depart) {
					Session session = sf.openSession();
					Transaction transaction = session.beginTransaction(); 
					try {
						session.delete(depart);
						transaction.commit();
						model.addAttribute("message", "Delete successfully !");
					}
					catch (Exception e) {
						model.addAttribute("message", "Delete fails !"); 
						transaction.rollback();
					} session.close();

			model.addAttribute("depart", new Departs()); 
			model.addAttribute("departs", getDepart1()); 
			return "test";
			}

			
			@RequestMapping(params="lnkEdit")
			public String edit(ModelMap model, @RequestParam("id") String id) {
				Session session = sf.getCurrentSession();
				Departs depart = (Departs) session.get(Departs.class, id);
				model.addAttribute("depart", depart); 
				return "test";
			}
		
	}

