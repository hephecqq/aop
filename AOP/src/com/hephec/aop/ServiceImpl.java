package com.hephec.aop;

import java.util.HashMap;
import java.util.Map;

public class ServiceImpl implements Service {

	private Map<Integer,Person> pMap=new HashMap<Integer, Person>();
	
	@Override
	public void addNew(Person p) {
		pMap.put(p.getId(), p);
	}

	public ServiceImpl() {
		pMap.put(1, new Person(1,"lisi"));
		pMap.put(1, new Person(2,"zhangsan"));
	}

	@Override
	public void delete(Integer id) {
		pMap.remove(id);
	}

	@Override
	public void update(Person p) {
		pMap.replace(p.getId(), p);
	}

}
