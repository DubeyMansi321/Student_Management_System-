package com.mansi.service;

import java.util.List;

import com.mansi.binding.DashboardResponse;
import com.mansi.binding.EnquiryForm;
import com.mansi.binding.EnquirySearchCriteria;
import com.mansi.entity.StudentEnqEntity;

public interface EnquiryService {
	
	public DashboardResponse getDashboardData(Integer userId);

	public List<String> getCourse();
	
	public List<String> getEnqStatus();

	public boolean saveEnquiry(EnquiryForm form);

	List<StudentEnqEntity> getEnquries();
	
	public List<StudentEnqEntity> getFilteredEnq(EnquirySearchCriteria criteria,Integer userId );

	public StudentEnqEntity getEnq(Integer id);
	
	public String updateEnq(Integer enqid,EnquiryForm formObj);


}