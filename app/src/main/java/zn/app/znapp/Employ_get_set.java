package zn.app.znapp;

public class Employ_get_set {
    public String name;
    public String address;
    public String aadhar_no;
    public String mobile;
    public String gender;
    public String heigest_qual;
    public String email;
    public String dof;
    public String description;
    public String role_id;
    public String faculty_for;
    public String salaried;
    public String salary;
    public String br_name;
    public String time_date;
    public String course;
    public String total_person;
    public String remark;
    public String collage_name;
    public String course_no;
    public String course_name;
    public String fee;
    public String duration;
    public String branch_no;

    public Employ_get_set(String course_no,String course_name,String fee,String duration,String branch_no){
        this.course_no = course_no;
        this.course_name = course_name;
        this.fee = fee;
        this.duration = duration;
        this.branch_no = branch_no;
    }

    public Employ_get_set(String course){
        this.course = course;
    }

    public Employ_get_set(String br_name,String name,String time_date,String course,String total_person,
                          String gender, String remark,String mobile,String description,String collage_name){
        this.br_name =br_name;
        this.name =name;
        this.time_date =time_date;
        this.course =course;
        this.total_person =total_person;
        this.gender =gender;
        this.remark =remark;
        this.mobile = mobile;
        this.description = description;
        this.collage_name =collage_name;
    }



    public Employ_get_set(String name,String address,String aadhar_no,String mobile,String gender,String heigest_qual
    ,String email,String dof,String description,String role_id,String faculty_for,String salaried,String salary){
        this.name =name;
        this.address =address;
        this.aadhar_no =aadhar_no;
        this.mobile =mobile;
        this.gender =gender;
        this.heigest_qual =heigest_qual;
        this.email =email;
        this.dof =dof;
        this.description =description;
        this.role_id =role_id;
        this.faculty_for =faculty_for;
        this.salaried =salaried;
        this.salary =salary;

    }

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

    public String getAadhar_no() {
        return aadhar_no;
    }

    public void setAadhar_no(String aadhar_no) {
        this.aadhar_no = aadhar_no;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeigest_qual() {
        return heigest_qual;
    }

    public void setHeigest_qual(String heigest_qual) {
        this.heigest_qual = heigest_qual;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDof() {
        return dof;
    }

    public void setDof(String dof) {
        this.dof = dof;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getFaculty_for() {
        return faculty_for;
    }

    public void setFaculty_for(String faculty_for) {
        this.faculty_for = faculty_for;
    }

    public String getSalaried() {
        return salaried;
    }

    public void setSalaried(String salaried) {
        this.salaried = salaried;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getBr_name() {
        return br_name;
    }

    public void setBr_name(String br_name) {
        this.br_name = br_name;
    }

    public String getTime_date() {
        return time_date;
    }

    public void setTime_date(String time_date) {
        this.time_date = time_date;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTotal_person() {
        return total_person;
    }

    public void setTotal_person(String total_person) {
        this.total_person = total_person;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCollage_name() {
        return collage_name;
    }

    public void setCollage_name(String collage_name) {
        this.collage_name = collage_name;
    }

    public String getCourse_no() {
        return course_no;
    }

    public void setCourse_no(String course_no) {
        this.course_no = course_no;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBranch_no() {
        return branch_no;
    }

    public void setBranch_no(String branch_no) {
        this.branch_no = branch_no;
    }
}
