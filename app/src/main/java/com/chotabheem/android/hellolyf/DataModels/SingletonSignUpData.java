package com.chotabheem.android.hellolyf.DataModels;

/**
 * Created by chota_bheem on 26/8/16.
 */
public class SingletonSignUpData {
    private String patientType;
    private String emailId;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dob;
    private String age;
    private String gender;
    private String mobileNo;

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }


    public static void setOurInstance(SingletonSignUpData ourInstance) {
        SingletonSignUpData.ourInstance = ourInstance;
    }



    private static SingletonSignUpData ourInstance = null;

    public static SingletonSignUpData getInstance() {
        if(ourInstance==null)
            ourInstance = new SingletonSignUpData();
        return ourInstance;
    }

    private SingletonSignUpData() {
        patientType= "other";
        emailId= " ";
        password= " ";
        password=" ";
        firstName= " ";
        middleName= " ";
        lastName=" ";
        dob= " ";
        age= "0";
        gender= " ";
        mobileNo= " ";

    }
}
