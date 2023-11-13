package zn.app.znapp;

public class Role_add_get_set {
    public String role;
    public String branch_no;
    public String branch_name;
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


    public Role_add_get_set(String branch_name,String enquiry,String course_name,String mobile_no){
        this.branch_name = branch_name;
        this.enquiry = enquiry;
        this.course_name = course_name;
        this.mobile_no = mobile_no;
    }

    public String getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(String enquiry) {
        this.enquiry = enquiry;
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

    public String getBranch_no() {
        return branch_no;
    }

    public void setBranch_no(String branch_no) {
        this.branch_no = branch_no;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getBranchNo() {
        return branch_no;
    }

    public void setBranchNo(String branchNo) {
        this.branch_no = branchNo;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public Role_add_get_set(String role){
        this.role = role;
    }


    public Role_add_get_set(String collage_code,String collage_name,String address,String email,String course,
                            String contact_no,String TPOfficer,String website,String description,String branch_no){
        this.collage_code = collage_code;
        this.collage_name = collage_name;
        this.address = address;
        this.email = email;
        this.course = course;
        this.contact_no = contact_no;
        this.TPOfficer = TPOfficer;
        this.website = website;
        this.description = description;
        this.branch_no = branch_no;
    }
}
