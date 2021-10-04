package com.salveo.mysalveo.requestpojo;

import java.io.Serializable;
import java.util.List;

public class PrescriptionCreateRequest {


	/**
	 * user_id
	 * Appointment_ID : 60cc9ca0a632e544acd3d244
	 * Date : 18/06/2021 07:04 PM
	 * Doctor_Comments : Dolo
	 * PDF_format :
	 * Prescription_data : [{"Quantity":"2","Tablet_name":"dolo","consumption":{"night":false,"evening":false,"morning":true}},{"Quantity":"2","Tablet_name":"dolo","consumption":{"night":false,"evening":false,"morning":true}}]
	 * Prescription_img :
	 * Prescription_type : PDF
	 * Treatment_Done_by :
	 * diagnosis : Digestive System
	 * doctor_id : 60acb15868492a4567b3f508
	 * sub_diagnosis : Gastrointestinal obstruction due to foreign body ingestion
	 */

	private String Appointment_ID;
	private String Date;
	private String Doctor_Comments;
	private String PDF_format;
	private String Prescription_img;
	private String Prescription_type;
	private String Treatment_Done_by;
	private String diagnosis;
	private String doctor_id;
	private String sub_diagnosis;
	private String user_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * Quantity : 2
	 * Tablet_name : dolo
	 * consumption : {"night":false,"evening":false,"morning":true}
	 */

	private List<PrescriptionDataBean> Prescription_data;

	public String getAppointment_ID() {
		return Appointment_ID;
	}

	public void setAppointment_ID(String Appointment_ID) {
		this.Appointment_ID = Appointment_ID;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String Date) {
		this.Date = Date;
	}

	public String getDoctor_Comments() {
		return Doctor_Comments;
	}

	public void setDoctor_Comments(String Doctor_Comments) {
		this.Doctor_Comments = Doctor_Comments;
	}

	public String getPDF_format() {
		return PDF_format;
	}

	public void setPDF_format(String PDF_format) {
		this.PDF_format = PDF_format;
	}

	public String getPrescription_img() {
		return Prescription_img;
	}

	public void setPrescription_img(String Prescription_img) {
		this.Prescription_img = Prescription_img;
	}

	public String getPrescription_type() {
		return Prescription_type;
	}

	public void setPrescription_type(String Prescription_type) {
		this.Prescription_type = Prescription_type;
	}

	public String getTreatment_Done_by() {
		return Treatment_Done_by;
	}

	public void setTreatment_Done_by(String Treatment_Done_by) {
		this.Treatment_Done_by = Treatment_Done_by;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getSub_diagnosis() {
		return sub_diagnosis;
	}

	public void setSub_diagnosis(String sub_diagnosis) {
		this.sub_diagnosis = sub_diagnosis;
	}

	public List<PrescriptionDataBean> getPrescription_data() {
		return Prescription_data;
	}

	public void setPrescription_data(List<PrescriptionDataBean> Prescription_data) {
		this.Prescription_data = Prescription_data;
	}

	public static class PrescriptionDataBean implements Serializable {
		private String Quantity;
		private String Tablet_name;
		/**
		 * night : false
		 * evening : false
		 * morning : true
		 */

		private ConsumptionBean consumption;

		public String getQuantity() {
			return Quantity;
		}

		public void setQuantity(String Quantity) {
			this.Quantity = Quantity;
		}

		public String getTablet_name() {
			return Tablet_name;
		}

		public void setTablet_name(String Tablet_name) {
			this.Tablet_name = Tablet_name;
		}

		public ConsumptionBean getConsumption() {
			return consumption;
		}

		public void setConsumption(ConsumptionBean consumption) {
			this.consumption = consumption;
		}

		public static class ConsumptionBean implements Serializable {
			private boolean night;
			private boolean evening;
			private boolean morning;

			public boolean isNight() {
				return night;
			}

			public void setNight(boolean night) {
				this.night = night;
			}

			public boolean isEvening() {
				return evening;
			}

			public void setEvening(boolean evening) {
				this.evening = evening;
			}

			public boolean isMorning() {
				return morning;
			}

			public void setMorning(boolean morning) {
				this.morning = morning;
			}
		}
	}
}