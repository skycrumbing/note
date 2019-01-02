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
		//���ز���ȡ�����ļ���ormԪ���ݣ�conf.configure()Ĭ�϶�ȡsrc�µ�hibernate.cfg.xml
		Configuration conf = new Configuration().configure();
		
		/*
		 * ��չ���ֶ�����ormԪ����
		 * conf.addClass(persistentClass);
		 * conf.addResource(resourceName);
		 */
		
		//����������Ϣ����sessionFactory�������ڴ����������ݿ���Ķ���session
		//���𱣴��ʹ������������Ϣ�����ڴ�ǳ������̰߳�ȫ�ģ�����Ҫ��֤��ʹ�ù�����ֵ����һ��sessionfactory
		SessionFactory sessionFactory = conf.buildSessionFactory();
		//session���ܣ����hibernate�����ݿ�ĶԻ����൱��jdbc��connection���󻹿���������ݿ��е���ɾ�Ĳ�
		//��һ���µ�session
		Session session = sessionFactory.openSession();
		/*
		 * ���һ�����̰߳󶨵�session
		 * sessionFactory.getCurrentSession();
		 */
		//��ò������������Transaction���󲢲�������
		Transaction tx = session.beginTransaction();
		//-------------------------------------
		//��
		Customer customer = new Customer();
		customer.setCust_name("abc");
		session.save(customer);
		//��
		Customer customer1 = session.get(Customer.class, 1l);
		System.out.println(customer1.getCust_name());
		
		//-------------------------------------
		//�ύ����
		tx.commit();
		/*
		 * �ع�����
		 * tx.rollback()
		 */
		session.close();
		sessionFactory.close();
	
	}

}
