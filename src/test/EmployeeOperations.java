package test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import domain.Employee;

public class EmployeeOperations {

	public static void main(String[] args) {
		
		Configuration cfg=null;
		Session ses=null;
		SessionFactory factory=null;
		Transaction tx= null;
		
		
		cfg= new Configuration().configure("cfg/hibernate.cfg.xml").addAnnotatedClass(Employee.class);
		
		factory=cfg.buildSessionFactory();
		
		ses=factory.openSession();
		
		tx=ses.beginTransaction();
		
		//Form Clause
		
		String hql="FROM Employee";
		Query query= ses.createQuery(hql);
		List<Employee> result= query.list();
		System.out.println("FirstName\tLastName\tSalary");
		System.out.println("--------------------------------------------------------------------");
		for(Employee e:result){
			
			System.out.println(e.getFirstName()+"\t"+e.getLastName()+"\t"+e.getSalary());
			
		}
		
		//As Clause
		//The AS clause can be used to assign aliases to the classes in your HQL queries, 
		//especially when you have the long queries.
		String hql1="From Employee as E";
		
		//Where Clause
		
		System.out.println("=================================================================");
		System.out.println("2nd Query");
		String hql2="From Employee E where E.salary<15560.12";
		Query q=ses.createQuery(hql2);
		List<Employee> result1=q.list();
		for(Employee e:result1){
			System.out.println(e.getFirstName()+"\t"+e.getLastName()+"\t"+e.getSalary());
		}
	
	
	  //Order By Clause
		System.out.println("===================================================================");
		System.out.println("3rd Query");
		String q1 ="From Employee E WHERE E.id>10 "+"ORDER BY E.firstName, E.salary DESC";
		Query query2=ses.createQuery(q1);
		List<Employee> r1=query2.list();
		
		for(Employee e:r1){
			System.out.println(e.getId()+"\t"+e.getFirstName()+"\t"+e.getLastName()+"\t"+e.getSalary());
		}
	
	
	//Group By Clause
	/***	
		System.out.println("======================================================================");
	    System.out.println("4th Query ");
	    
	    String q3="SELECT SUM(E.salary),E.firstName FROM Empolyee E"+"group by E.firstName";
	     Query Q=ses.createQuery(q3);
	     List<Employee> l1=Q.list();
	      
	     System.out.println(l1.size());
	***/
	//Update 
	     
	   System.out.println("=======================================================================");  
	  System.out.println("5th Query");
	  String Q1="Update Employee E set E.salary= :salary WHERE E.id = :employee_id";
	  Query q4=ses.createQuery(Q1);
	  q4.setParameter("salary", 1000.12);
	  q4.setParameter("employee_id", 5);
	  int status=q4.executeUpdate();
	  System.out.println("Row Affected "+status);
	  
	}
	
}
