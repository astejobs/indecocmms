
package com.aste.lsme.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



@Entity
@Table(name = "task_checklist")
public class TaskChecklist {

   String  filltheblank = "<input type='text' name='blanks' style='width:60px' value='%s'/> ";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "task")
  Task task;

  /*@OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "checklist_property")
  ChecklistProperty checklistProperty;*/

  @Column(name = "description")
  String description;
	
  @Column(name = "type")
  private String descriptionType;
  
  @ElementCollection
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<String> blanks = new ArrayList<String>();

  @Column(name = "status")
  String status;

  @Column(name = "remarks")
  String remarks;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /*public ChecklistProperty getChecklistProperty() {

    int x = StringUtils.countMatches(checklistProperty.getDescription(), "___");
    
    if (blanks.isEmpty())
      return checklistProperty;
    else {
      for (int i = 0; i < x; ++i) {

         checklistProperty.setDescription(checklistProperty.getDescription().replaceFirst("___",
         String.format(filltheblank, blanks.get(i))));
      }
    }
    return checklistProperty;
  }

  public void setChecklistProperty(ChecklistProperty checklistProperty) {
    this.checklistProperty = checklistProperty;
  }
*/
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public List<String> getBlanks() {
    return blanks;
  }

  public void setBlanks(List<String> blanks) {
    this.blanks = blanks;
  }

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getDescriptionType() {
	return descriptionType;
}

public void setDescriptionType(String descriptionType) {
	this.descriptionType = descriptionType;
}
  
  

}
