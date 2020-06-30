package com.dl.text7;

import java.util.List;

import com.dl.util.Employee;

public class GetImportance {

    public int getImportance(List<Employee> employees, int id) {
        if (employees.size() == 0) {
            return 0;
        } 
        Employee[] es = new Employee[2001];
        for (Employee e : employees) {
            es[e.id] = e;
        }
        return get(es, id);
    }

    private int get(Employee[] es, int id) {
        List<Integer> subordinates = es[id].subordinates;
        int re = es[id].importance;
        for (int i : subordinates) {
            re += get(es, i);
        }
        return re;
    }
	
}
