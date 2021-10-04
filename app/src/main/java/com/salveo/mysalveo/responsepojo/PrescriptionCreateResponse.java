package com.salveo.mysalveo.responsepojo;

import java.util.List;

public class PrescriptionCreateResponse{

	/**
	 * Status : Success
	 * Message : Added successfully
	 * Data : {"Prescription_data":[{"Quantity":"3","Tablet_name":"dolo","consumption":"twice"}],"_id":"5fc8d7015a976d673ec15e27","doctor_id":"5ef3472a4b9bd73eb1cff539","Appointment_ID":"","Treatment_Done_by":"Self","user_id":"5ef2c092c006bb0ed174c771","Prescription_type":"Image / PDF","PDF_format":"http://mysalveo.com/api/public/prescriptions/231afd32-6d68-4288-a8e5-1c599833c0e8.pdf","Prescription_img":"http://mysalveo.com/api/public/prescriptions/231afd32-6d68-4288-a8e5-1c599833c0e8.pdf","Doctor_Comments":"test","Date":"23-10-2020 12:00 AM","__v":0}
	 * Code : 200
	 */

	private String Status;
	private String Message;
	private DataBean Data;
	private int Code;


	public String getStatus() {
		return Status;
	}

	public void setStatus(String Status) {
		this.Status = Status;
	}


	public String getMessage() {
		return Message;
	}

	public void setMessage(String Message) {
		this.Message = Message;

	}


	public DataBean getData() {
		return Data;
	}

	public void setData(DataBean Data) {
		this.Data = Data;

	}


	public int getCode() {
		return Code;
	}

	public void setCode(int Code) {
		this.Code = Code;

	}

	public static class DataBean {
		/**
		 * Prescription_data : [{"Quantity":"3","Tablet_name":"dolo","consumption":"twice"}]
		 * _id : 5fc8d7015a976d673ec15e27
		 * doctor_id : 5ef3472a4b9bd73eb1cff539
		 * Appointment_ID :
		 * Treatment_Done_by : Self
		 * user_id : 5ef2c092c006bb0ed174c771
		 * Prescription_type : Image / PDF
		 * PDF_format : http://mysalveo.com/api/public/prescriptions/231afd32-6d68-4288-a8e5-1c599833c0e8.pdf
		 * Prescription_img : http://mysalveo.com/api/public/prescriptions/231afd32-6d68-4288-a8e5-1c599833c0e8.pdf
		 * Doctor_Comments : test
		 * Date : 23-10-2020 12:00 AM
		 * __v : 0
		 */

		private String _id;
		private String doctor_id;
		private String Appointment_ID;
		private String Treatment_Done_by;
		private String user_id;
		private String Prescription_type;
		private String PDF_format;
		private String Prescription_img;
		private String Doctor_Comments;
		private String Date;
		private int __v;
		private List<PrescriptionDataBean> Prescription_data;


		public String get_id() {
			return _id;
		}

		public void set_id(String _id) {
			this._id = _id;

		}


		public String getDoctor_id() {
			return doctor_id;
		}

		public void setDoctor_id(String doctor_id) {
			this.doctor_id = doctor_id;

		}


		public String getAppointment_ID() {
			return Appointment_ID;
		}

		public void setAppointment_ID(String Appointment_ID) {
			this.Appointment_ID = Appointment_ID;

		}


		public String getTreatment_Done_by() {
			return Treatment_Done_by;
		}

		public void setTreatment_Done_by(String Treatment_Done_by) {
			this.Treatment_Done_by = Treatment_Done_by;

		}


		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;

		}


		public String getPrescription_type() {
			return Prescription_type;
		}

		public void setPrescription_type(String Prescription_type) {
			this.Prescription_type = Prescription_type;

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


		public String getDoctor_Comments() {
			return Doctor_Comments;
		}

		public void setDoctor_Comments(String Doctor_Comments) {
			this.Doctor_Comments = Doctor_Comments;

		}


		public String getDate() {
			return Date;
		}

		public void setDate(String Date) {
			this.Date = Date;

		}


		public int get__v() {
			return __v;
		}

		public void set__v(int __v) {
			this.__v = __v;

		}


		public List<PrescriptionDataBean> getPrescription_data() {
			return Prescription_data;
		}

		public void setPrescription_data(List<PrescriptionDataBean> Prescription_data) {
			this.Prescription_data = Prescription_data;

		}

		public static class PrescriptionDataBean  {
			/**
			 * Quantity : 3
			 * Tablet_name : dolo
			 * consumption : twice
			 */

			private String Quantity;
			private String Tablet_name;
			private String consumption;


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


			public String getConsumption() {
				return consumption;
			}

			public void setConsumption(String consumption) {
				this.consumption = consumption;
			}
		}
	}
}
