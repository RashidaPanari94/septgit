package com.categoryProduct;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categoryApi")
public class CategoryController {
	
	@Autowired
	SessionFactory factory;
	
	@GetMapping("category/{cid}")
	public Category getCategory(@PathVariable int cid) {
		Session session=factory.openSession();
		Category category=session.load(Category.class, cid);
		return category;
		}
	
	@GetMapping("category")
	public List<Category> getAllCategory(){
		Session session=factory.openSession();
		Query query=session.createQuery("from Category");
		
		List<Category> list=query.list();
		return list;
		
	}
	@PostMapping("category")
	public Category addCategory(@RequestBody Category category) {
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		session.save(category);
		transaction.commit();
		System.out.println("Category Added in Database");
		return category;
		
	}
	@DeleteMapping("category/{cid}")
	public String deleteCategory(@PathVariable int cid) {
		Session session=factory.openSession();	
		Category category=session.load(Category.class, cid);
		Transaction transaction=session.beginTransaction();
		session.delete(category);
		transaction.commit();
		
		return "record deleted";
		
	}
	
	@PutMapping("category")
	public String updateCategory(@RequestBody Category clientCategory) {
	Session session=factory.openSession();
	Category category=session.load(Category.class,clientCategory.getCategory_id());
	category.setName(clientCategory.getName());
	
	Transaction transaction=session.beginTransaction();
	session.update(category);
	transaction.commit();
	return "Record Updated";
		
	}
	
	

}
