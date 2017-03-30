import java.io.Serializable;

public class Student implements Serializable
{
	private String name;
	private String address;
	private String dateOfBirth;
	private String monthOfBirth;
	private String yearOfBirth;
	private String phoneNumber;
	private String gradeLevel;
	private String idNum;
	
	public Student(String name, String idNum, String address, String phoneNumber, String gradeLevel,String monthOfBirth, String dateOfBirth, String yearOfBirth)
	{
		super();
		this.name = name;
		this.idNum = idNum;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.gradeLevel = gradeLevel;
		this.monthOfBirth = monthOfBirth;
		this.dateOfBirth = dateOfBirth;
		this.yearOfBirth = yearOfBirth;
	}
	public Student (){}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMonthOfBirth() {
		return monthOfBirth;
	}
	public void setMonthOfBirth(String monthOfBirth) {
		this.monthOfBirth = monthOfBirth;
	}
	public String getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGradeLevel() {
		return gradeLevel;
	}
	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) 
	{
		this.idNum = idNum;
	}
	
	public int compareTo(String targetKey)
	{
		return(name.compareTo(targetKey));
	}
	
	public Student deepCopy()
	{
		Student clone = new Student(name, idNum, address,  phoneNumber,  gradeLevel, monthOfBirth,  dateOfBirth,  yearOfBirth);
		return clone;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", monthOfBirth="
				+ monthOfBirth + ", yearOfBirth=" + yearOfBirth + ", phoneNumber=" + phoneNumber + ", gradeLevel="
				+ gradeLevel + ", idNum=" + idNum + "]";
	}
	

	
}
