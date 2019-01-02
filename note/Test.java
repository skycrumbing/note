package test;



import javabean.Customer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class Test {
	
	@Test
	public void fun1(){
		//加载并读取配置文件，orm元数据，conf.configure()默认读取src下的hibernate.cfg.xml
		Configuration conf = new Configuration().configure();
		
		/*
		 * 扩展，手动加载orm元数据
		 * conf.addClass(persistentClass);
		 * conf.addResource(resourceName);
		 */
		
		//根据配置信息创建sessionFactory对象，用于创建操作数据库核心对象session
		//负责保存和使用所有配置信息消耗内存非常大，是线程安全的，所以要保证在使用过程中值创建一个sessionfactory
		SessionFactory sessionFactory = conf.buildSessionFactory();
		//session功能：表达hibernate与数据库的对话，相当于jdbc的connection对象还可以完成数据库中的增删改查
		//打开一个新的session
		Session session = sessionFactory.openSession();
		/*
		 * 获得一个与线程绑定的session
		 * sessionFactory.getCurrentSession();
		 */
		//获得操作数据事务的Transaction对象并操作事务
		Transaction tx = session.beginTransaction();
		//-------------------------------------
		//增
		Customer customer = new Customer();
		customer.setCust_name("abc");
		session.save(customer);
		//查
		Customer customer1 = session.get(Customer.class, 1l);
		System.out.println(customer1.getCust_name());
		
		//-------------------------------------
		//提交事务
		tx.commit();
		/*
		 * 回滚事务
		 * tx.rollback()
		 */
		session.close();
		sessionFactory.close();
	
	}

}
