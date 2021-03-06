package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.SOR;
import com.aste.lsme.domain.SORSearchCriteria;
import com.aste.lsme.domain.Workspace;

public interface SORDao {

	List<Equipment> getEqpName(String eqpType, Workspace w);

	public void save(SOR sor) throws Exception;

	List<SOR> getSearchList(int from, SORSearchCriteria sorSearch, Workspace w);

	long count(SORSearchCriteria sorSearch, Workspace w);
	
	List<SOR> getWorkspaceSor(Workspace w);
	
	public List<SOR> find(Long[] id);
	
	SOR find(Long id);

	boolean remove(SOR sor);

	public void update(SOR sor,Workspace w) throws Exception ;

	List<AssetSubtype> getAssetSubtype(long assetId);

	List<AssetType> getAsset(Long id);

	

	
}
