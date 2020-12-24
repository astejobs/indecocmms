package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.ACMV;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;

public interface AcmvServiceInterface {
public  ACMV save(ACMV acmv);
public  Boolean update(ACMV acmv);
public ACMV  delete(ACMV acmv);
public  ACMV  find(Long id);
public  ACMV  find(Long id,Workspace workspace);
public String  generateEquipmentCode(String type);
public List<ACMV> getSearchList(int from,EquipmentSearchCriteria acmvSearchCriteria);
public  void  updateEquimentBaseLine(ACMV acmv);
public Long getAcmvCount(Workspace w);





}
