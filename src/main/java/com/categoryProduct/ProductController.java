package com.categoryProduct;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
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
@RequestMapping("productApi")
public class ProductController {
	@Autowired
	SessionFactory factory;
	
	@PostMapping("product/{cid}")
	public Product addProduct(@RequestBody Product product,@PathVariable int cid) {
		System.out.println("Category id is "+cid);
		
		Session session=factory.openSession();
		Category category=session.load(Category.class,cid);
		List<Product> productlist =category.getProducts();
		Transaction transaction=session.beginTransaction();
		productlist.add(product);
		transaction.commit();
		System.out.println("product Added into database");
		return product;
		
	}
	@DeleteMapping("product/{pid}")
	public String deleteProduct(@PathVariable int pid)
	{
		
		// To get Category Id of Product , we need to write join query 
		
		Session session=factory.openSession();
		
		NativeQuery<Object[]> query=session.createSQLQuery("select category.category_id  , category.name from category,product where product.category_id=category.category_id and product_id="+pid);

		List<Object[]> list = query.list(); 
		
		// list [ (array having 2 values) only this single array is present in list ] 
		
		System.out.println(list.size());
		
		Object[] array=list.get(0);
		
		int cid=(int)array[0];
		
	System.out.println(pid + " " + cid);
	 		Category category=session.load(Category.class,cid);
	 		List<Product> productlist=category.getProducts();
	        Product product=session.load(Product.class,pid);
				Transaction transaction=session.beginTransaction();
			   productlist.remove(product);
			transaction.commit();
			return "record deleted";
					
		}
	@GetMapping("product/{pid}")
	public String viewProduct(@PathVariable int pid)
	{
		
				Session session=factory.openSession();
				
				NativeQuery<Object[]> query=session.createSQLQuery("select product.product_id,product.name,product.price, category.category_id,category.name as cname from category,product where product.category_id=category.category_id and product_id="+pid);

				List<Object[]> list = query.list(); 
				
				Object[] array=list.get(0);
				
				return Arrays.toString(array);
	}
	
	@GetMapping("products")
	public String getAllProducts()
	{
		
				Session session=factory.openSession();
				
				NativeQuery<Object[]> query=session.createSQLQuery("select product.product_id,product.name,product.price, category.category_id ,category.name as cname from category,product where product.category_id=category.category_id");

				List<Object[]> list = query.list(); 
					
				StringBuffer stringBuffer=new StringBuffer();
				
				for(int i=0;i<list.size();i++)
				{
					Object[] array=list.get(i);
					
					stringBuffer.append(Arrays.toString(array) + " , ");
					
				}
				
				return stringBuffer.toString();
	}


	
	
	@PutMapping("product")
	public String updateProduct(@RequestBody Product clientProduct)
	{
		
				Session session=factory.openSession();
				
				
				Product product=session.load(Product.class,clientProduct.getProduct_id());
				
				product.setName(clientProduct.getName());
				
				product.setPrice(clientProduct.getPrice());
				

				Transaction transaction=session.beginTransaction();
				
					session.update(product);
								
				transaction.commit();
				
				
				return "Record Updated";

	}
	

		
	
}
