package zn.app.znapp.adapter;

public class Branch_GetSet {
    public String branch_name;
    public String branch_no;
    public String start_date;
    public String address;
    public String contact_no;
    public String mobile_no;
    public String whatsApp_no;
    public String email;
    public String status;
    public String course_no;
    public String course_name;
    public String fee;
    public String duration;
    public String collage_code;
    public String collage_name;
    public String course;
    public String TPOfficer;
    public String website;
    public String description;
    public String enquiry;

    public  Branch_GetSet(String branch_name){
        this.branch_name = branch_name;
    }
    public Branch_GetSet(String branch_no,String branch_name,String start_date,String address,String contact_no,
                            String mobile_no,String whatsApp_no,String email,String status){
        this.branch_no = branch_no;
        this.branch_name = branch_name;
        this.start_date = start_date;
        this.address = address;
        this.contact_no = contact_no;
        this.mobile_no = mobile_no;
        this.whatsApp_no = whatsApp_no;
        this.email = email;
        this.status = status;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }
    public String getBranch_no() {
        return branch_no;
    }

    public void setBranch_no(String branch_no) {
        this.branch_no = branch_no;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getWhatsApp_no() {
        return whatsApp_no;
    }

    public void setWhatsApp_no(String whatsApp_no) {
        this.whatsApp_no = whatsApp_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCollage_code() {
        return collage_code;
    }

    public void setCollage_code(String collage_code) {
        this.collage_code = collage_code;
    }

    public String getCollage_name() {
        return collage_name;
    }

    public void setCollage_name(String collage_name) {
        this.collage_name = collage_name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTPOfficer() {
        return TPOfficer;
    }

    public void setTPOfficer(String TPOfficer) {
        this.TPOfficer = TPOfficer;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(String enquiry) {
        this.enquiry = enquiry;
    }
}
