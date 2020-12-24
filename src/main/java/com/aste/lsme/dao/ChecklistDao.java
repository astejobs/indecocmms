package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.ChecklistHeader;
import com.aste.lsme.domain.ChecklistPropertyTitle;
import com.aste.lsme.domain.ChecklistSearch;
import com.aste.lsme.domain.Workspace;

public interface ChecklistDao {
	
	public void addChecklist(ChecklistHeader checklist) throws Exception;
	public Long countSearchPage(ChecklistSearch qsc);
	public List<ChecklistHeader> getSearchPage(ChecklistSearch qsc);
	public ChecklistHeader find(Long id);
	public void update(ChecklistHeader checklist) throws Exception;
	public void removeChecklistTitle(Long id);
	public void removeChecklistproperties(Long id);
	public List<ChecklistHeader> getAllWorkspaceBased(Workspace w);
	public List<ChecklistHeader> listAll(Long id);
	public List<ChecklistPropertyTitle> findPropertyTitles(ChecklistHeader chklistHeader);
}
