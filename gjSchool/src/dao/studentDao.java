//先列摘要，studentDaoImpl 實作
package dao;

import java.util.List;

import modal.student;

public interface studentDao {
	
	//create
		void add(student s);
		
		//read
		String queryAll1();
		List<student> queryAll2();
		
		//update
		
		
		//delete

}
