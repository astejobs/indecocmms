package com.aste.lsme.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


//@Entity
//@DiscriminatorValue(value=Constants.CIVILSUBSYSTEM)
public class Civil {


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CivilLevel3_id")
	@NotNull(message = "Please Select CivilLevel3")
	CivilLevel3 civilLevel3;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CivilLevel4_id")
	@NotNull(message = "Please Select CivilLevel4")
	CivilLevel4 civilLevel4;

	public CivilLevel3 getCivilLevel3() {
		return civilLevel3;
	}

	public void setCivilLevel3(CivilLevel3 civilLevel3) {
		this.civilLevel3 = civilLevel3;
	}

	public CivilLevel4 getCivilLevel4() {
		return civilLevel4;
	}

	public void setCivilLevel4(CivilLevel4 civilLevel4) {
		this.civilLevel4 = civilLevel4;
	}
	
	/*public Civil(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	*/
	public Civil() {
		super();
	}
}
