package com.dl.consumer.core;

import java.util.List;

public interface IAction {
	List<String> updataServiceTags();
	List<String> updataNews(String serviceTag);
}
