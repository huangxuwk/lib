package com.mec.cs_framework.action;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mec.test_cs.server.model.Complex;
import com.mec.test_cs.server.model.UserModel;

public class Test {

	public static void main(String[] args) {
		Gson gson = new GsonBuilder().create();
		
		UserModel user = new UserModel("03177020", "离明", "123456", true);
		String userString = gson.toJson(user);
		System.out.println(userString);
		
		UserModel otherUser = gson.fromJson(userString, UserModel.class);
		System.out.println(otherUser);
		
		Map<String, String> paraMap = new HashMap<>();
		String c1String = gson.toJson(new Complex(1.0, 2.0));
		String c2String = gson.toJson(new Complex(7.0, 8.0));
		
		paraMap.put("c1", c1String);
		paraMap.put("c2", c2String);
		System.out.println("{\"c1\":{\"real\":1.0,\"vir\":2.0},\"c2\":{\"real\":7.0,\"vir\":8.0}}");
		
		String mapString = gson.toJson(paraMap);
		System.out.println(mapString);
		Type type = new TypeToken<Map<String, String>>() {}.getType();
		
		Map<String, String> m = gson.fromJson(mapString, type);
		System.out.println("m:" + m);
		String c1Str = m.get("c1");
		Complex c1 = gson.fromJson(c1Str, Complex.class);
		System.out.println(c1);
	}

}
