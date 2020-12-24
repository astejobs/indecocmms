package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.aste.lsme.domain.Constants;

//@Entity
//@DiscriminatorValue(value = Constants.ACMVSUBSYSTEM)
public class ACMV {




	@Column(name = "Brand")
	String brand;

	@Column(name = "Tonnage")
	Double tonnage;
	
	@Column(name = "Refrigerant")
	String refrigerant;

	@Column(name = "Design_Work_Pressure")
	Double designWorkPressure;

	@Column(name = "Volume")
	Double volume;

	@Column(name = "Motor")
	Double motor;

	@Column(name = "No_Of_Passes")
	Long noOfPasses;
	
	@Column(name="Fan_Model")
	String fanModel;
	
	@Column(name = "Mfg_No")
	String mfgNo;
	
	@Column(name = "Motor_Brand")
	String motorBrand;

	@Column(name = "Motor_Current")
	String motorCurrent;
	
	@Column(name = "Motor_Typ")
	String motorType;
	
	@Column(name = "Motor_Frame")
	String motorFrame;
	
	@Column(name = "Motor_Horse_Power")
	String motorHorsePower;
	
	@Column(name = "Motor_Model")
	String motorModel;
	
	@Column(name = "Motor_Voltage")
	String motorVoltage;

	@Column(name = "Motor_Power")
	String motorPower;
	
	@Column(name = "Motor_Pulley")
	String motorPulley;
	
	@Column(name = "Motor_Pole")
	String motorPole;
	
	@Column(name = "Motor_RPM")
	String motorRpm;
	
	@Column(name = "Motor_sl_no")
	String motorSrlNo;
	
	@Column(name = "Motor_Speed")
	String motorSpeed;
	
	@Column(name = "Pre_Filter_Type")
	String preFilterType;
	
	@Column(name = "Pre_Filter_Size")
	String preFilterSize;
	
	@Column(name = "Pre_Filter_Brand")
	String preFilterBrand;
	
	@Column(name = "Sec_Filter_Type")
	String secFilterType;
	
	@Column(name = "Belt_Size")
	String beltSize;
	
	@Column(name = "Power_Supply")
	String powerSupply;
	
	@Column(name = "Min_Circuit_Amps")
	String minCircuitAmps;
	
	@Column(name = "Max_Fuse")
	String maxFuse;
	
	@Column(name = "Fan_Motor_Ph")
	String fanMotorPh;
	
	@Column(name = "Fan_Motor_Qty")
	String fanMotorQty;
	
	@Column(name = "Fan_Motor_FLA")
	String fanMotorFLA;
	
	@Column(name = "Fan_Motor_WOut")
	String fanMotorWOut;
	
	@Column(name = "Weight")
	String weight;
	
	@Column(name = "Serial_No")
	String serialNo;
	
	
	@Column(name = "Work_Pressure")
	String workPressure;
	
	@Column(name = "Design_Pressure")
	String designPressure;
	
	@Column(name = "Model")
	String model;
	
	@Column(name = "Ahu_No")
	String ahuNo;
	
	@Column(name = "Operating_Weight")
	String operatingWeight;
	
	@Column(name = "Type_Of_Blower_Fans")
	String typeOfBlowerFans;
	
	@Column(name = "No_Of_Fans")
	String noOfFans;
	
	@Column(name = "Type_Of_Fan_Drive")
	String typeOfFanDrive;
	
	@Column(name = "Chilled_Water_Qty")
	String chilledWaterQty;
	
	@Column(name = "Total_Cooling_Capacity")
	String totalCoolingCapacity;
	
	@Column(name = "Electrical_Characteristics")
	String electricalCharacteristics;
	
	@Column(name = "Full_Load_Power_Input")
	String fullLoadPowerInput;
	
	@Column(name = "Mvf_No")
	String mvfNo;
	
	@Column(name = "Capacity_Or_Rating")
	String capacityOrRating;
	
	@Column(name = "Fcu_No")
	String fcuNo;
	
	@Column(name = "Fan_Motor_Make")
	String fanMotorMake;
	

	/*public ACMV() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ACMV(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}*/

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getTonnage() {
		return tonnage;
	}

	public void setTonnage(Double tonnage) {
		this.tonnage = tonnage;
	}

	public String getRefrigerant() {
		return refrigerant;
	}

	public void setRefrigerant(String refrigerant) {
		this.refrigerant = refrigerant;
	}

	public Double getDesignWorkPressure() {
		return designWorkPressure;
	}

	public void setDesignWorkPressure(Double designWorkPressure) {
		this.designWorkPressure = designWorkPressure;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getMotor() {
		return motor;
	}

	public void setMotor(Double motor) {
		this.motor = motor;
	}

	public Long getNoOfPasses() {
		return noOfPasses;
	}

	public void setNoOfPasses(Long noOfPasses) {
		this.noOfPasses = noOfPasses;
	}

	public String getFanModel() {
		return fanModel;
	}

	public void setFanModel(String fanModel) {
		this.fanModel = fanModel;
	}

	public String getMfgNo() {
		return mfgNo;
	}

	public void setMfgNo(String mfgNo) {
		this.mfgNo = mfgNo;
	}

	public String getMotorBrand() {
		return motorBrand;
	}

	public void setMotorBrand(String motorBrand) {
		this.motorBrand = motorBrand;
	}

	public String getMotorCurrent() {
		return motorCurrent;
	}

	public void setMotorCurrent(String motorCurrent) {
		this.motorCurrent = motorCurrent;
	}

	public String getMotorType() {
		return motorType;
	}

	public void setMotorType(String motorType) {
		this.motorType = motorType;
	}

	public String getMotorFrame() {
		return motorFrame;
	}

	public void setMotorFrame(String motorFrame) {
		this.motorFrame = motorFrame;
	}

	public String getMotorHorsePower() {
		return motorHorsePower;
	}

	public void setMotorHorsePower(String motorHorsePower) {
		this.motorHorsePower = motorHorsePower;
	}

	public String getMotorModel() {
		return motorModel;
	}

	public void setMotorModel(String motorModel) {
		this.motorModel = motorModel;
	}

	public String getMotorVoltage() {
		return motorVoltage;
	}

	public void setMotorVoltage(String motorVoltage) {
		this.motorVoltage = motorVoltage;
	}

	public String getMotorPower() {
		return motorPower;
	}

	public void setMotorPower(String motorPower) {
		this.motorPower = motorPower;
	}

	public String getMotorPulley() {
		return motorPulley;
	}

	public void setMotorPulley(String motorPulley) {
		this.motorPulley = motorPulley;
	}

	public String getMotorPole() {
		return motorPole;
	}

	public void setMotorPole(String motorPole) {
		this.motorPole = motorPole;
	}

	public String getMotorRpm() {
		return motorRpm;
	}

	public void setMotorRpm(String motorRpm) {
		this.motorRpm = motorRpm;
	}

	public String getMotorSrlNo() {
		return motorSrlNo;
	}

	public void setMotorSrlNo(String motorSrlNo) {
		this.motorSrlNo = motorSrlNo;
	}

	public String getMotorSpeed() {
		return motorSpeed;
	}

	public void setMotorSpeed(String motorSpeed) {
		this.motorSpeed = motorSpeed;
	}

	public String getPreFilterType() {
		return preFilterType;
	}

	public void setPreFilterType(String preFilterType) {
		this.preFilterType = preFilterType;
	}

	public String getPreFilterSize() {
		return preFilterSize;
	}

	public void setPreFilterSize(String preFilterSize) {
		this.preFilterSize = preFilterSize;
	}

	public String getPreFilterBrand() {
		return preFilterBrand;
	}

	public void setPreFilterBrand(String preFilterBrand) {
		this.preFilterBrand = preFilterBrand;
	}

	public String getSecFilterType() {
		return secFilterType;
	}

	public void setSecFilterType(String secFilterType) {
		this.secFilterType = secFilterType;
	}

	public String getBeltSize() {
		return beltSize;
	}

	public void setBeltSize(String beltSize) {
		this.beltSize = beltSize;
	}

	public String getPowerSupply() {
		return powerSupply;
	}

	public void setPowerSupply(String powerSupply) {
		this.powerSupply = powerSupply;
	}

	public String getMinCircuitAmps() {
		return minCircuitAmps;
	}

	public void setMinCircuitAmps(String minCircuitAmps) {
		this.minCircuitAmps = minCircuitAmps;
	}

	public String getMaxFuse() {
		return maxFuse;
	}

	public void setMaxFuse(String maxFuse) {
		this.maxFuse = maxFuse;
	}

	public String getFanMotorPh() {
		return fanMotorPh;
	}

	public void setFanMotorPh(String fanMotorPh) {
		this.fanMotorPh = fanMotorPh;
	}

	public String getFanMotorQty() {
		return fanMotorQty;
	}

	public void setFanMotorQty(String fanMotorQty) {
		this.fanMotorQty = fanMotorQty;
	}

	public String getFanMotorFLA() {
		return fanMotorFLA;
	}

	public void setFanMotorFLA(String fanMotorFLA) {
		this.fanMotorFLA = fanMotorFLA;
	}

	public String getFanMotorWOut() {
		return fanMotorWOut;
	}

	public void setFanMotorWOut(String fanMotorWOut) {
		this.fanMotorWOut = fanMotorWOut;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getWorkPressure() {
		return workPressure;
	}

	public void setWorkPressure(String workPressure) {
		this.workPressure = workPressure;
	}

	public String getDesignPressure() {
		return designPressure;
	}

	public void setDesignPressure(String designPressure) {
		this.designPressure = designPressure;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAhuNo() {
		return ahuNo;
	}

	public void setAhuNo(String ahuNo) {
		this.ahuNo = ahuNo;
	}

	public String getOperatingWeight() {
		return operatingWeight;
	}

	public void setOperatingWeight(String operatingWeight) {
		this.operatingWeight = operatingWeight;
	}

	public String getTypeOfBlowerFans() {
		return typeOfBlowerFans;
	}

	public void setTypeOfBlowerFans(String typeOfBlowerFans) {
		this.typeOfBlowerFans = typeOfBlowerFans;
	}

	public String getNoOfFans() {
		return noOfFans;
	}

	public void setNoOfFans(String noOfFans) {
		this.noOfFans = noOfFans;
	}

	public String getTypeOfFanDrive() {
		return typeOfFanDrive;
	}

	public void setTypeOfFanDrive(String typeOfFanDrive) {
		this.typeOfFanDrive = typeOfFanDrive;
	}

	public String getChilledWaterQty() {
		return chilledWaterQty;
	}

	public void setChilledWaterQty(String chilledWaterQty) {
		this.chilledWaterQty = chilledWaterQty;
	}

	public String getTotalCoolingCapacity() {
		return totalCoolingCapacity;
	}

	public void setTotalCoolingCapacity(String totalCoolingCapacity) {
		this.totalCoolingCapacity = totalCoolingCapacity;
	}

	public String getElectricalCharacteristics() {
		return electricalCharacteristics;
	}

	public void setElectricalCharacteristics(String electricalCharacteristics) {
		this.electricalCharacteristics = electricalCharacteristics;
	}

	public String getFullLoadPowerInput() {
		return fullLoadPowerInput;
	}

	public void setFullLoadPowerInput(String fullLoadPowerInput) {
		this.fullLoadPowerInput = fullLoadPowerInput;
	}

	public String getMvfNo() {
		return mvfNo;
	}

	public void setMvfNo(String mvfNo) {
		this.mvfNo = mvfNo;
	}

	public String getCapacityOrRating() {
		return capacityOrRating;
	}

	public void setCapacityOrRating(String capacityOrRating) {
		this.capacityOrRating = capacityOrRating;
	}

	public String getFcuNo() {
		return fcuNo;
	}

	public void setFcuNo(String fcuNo) {
		this.fcuNo = fcuNo;
	}

	public String getFanMotorMake() {
		return fanMotorMake;
	}

	public void setFanMotorMake(String fanMotorMake) {
		this.fanMotorMake = fanMotorMake;
	}
	
}
