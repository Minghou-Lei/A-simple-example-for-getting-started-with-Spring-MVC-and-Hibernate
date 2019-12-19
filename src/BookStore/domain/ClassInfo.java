package BookStore.domain;

import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "ClassInfo")
public class ClassInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ClassID;

	private String ClassName;
	private String Des;

	public ClassInfo() {
	}

	public ClassInfo(int ID) {
		this.ClassID = ID;
	}

	public int getClassID() {
		return ClassID;
	}

	public void setClassID(int classID) {
		ClassID = classID;
	}

	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public String getDes() {
		return Des;
	}

	public void setDes(String des) {
		Des = des;
	}
}
