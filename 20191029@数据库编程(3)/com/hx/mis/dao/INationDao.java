package com.hx.mis.dao;

import java.util.List;
import com.hx.mis.model.NationModel;
import com.hx.mis.model.NativeModel;

public interface INationDao {
	List<NationModel> getNations();
	List<NativeModel> getNatives();
}
