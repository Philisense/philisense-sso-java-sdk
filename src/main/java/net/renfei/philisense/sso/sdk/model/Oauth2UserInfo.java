package net.renfei.philisense.sso.sdk.model;

public class Oauth2UserInfo {
    private String id;
    private String username;
    private String email;
    private String api_token;
    private String avatar;
    private String status;
    private String created_at;
    private String updated_at;
    private String dep_id;
    private String creator;
    private String changer;
    private String up_dep_id;
    private String cr_dep_id;
    private String realname;
    private String entryTime;
    private String tel;
    private String department;
    private String skin;
    private String company;
    private String duties;
    private String sex;
    private String in_service_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDep_id() {
        return dep_id;
    }

    public void setDep_id(String dep_id) {
        this.dep_id = dep_id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getChanger() {
        return changer;
    }

    public void setChanger(String changer) {
        this.changer = changer;
    }

    public String getUp_dep_id() {
        return up_dep_id;
    }

    public void setUp_dep_id(String up_dep_id) {
        this.up_dep_id = up_dep_id;
    }

    public String getCr_dep_id() {
        return cr_dep_id;
    }

    public void setCr_dep_id(String cr_dep_id) {
        this.cr_dep_id = cr_dep_id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIn_service_time() {
        return in_service_time;
    }

    public void setIn_service_time(String in_service_time) {
        this.in_service_time = in_service_time;
    }

    @Override
    public String toString() {
        return "Oauth2UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", api_token='" + api_token + '\'' +
                ", avatar='" + avatar + '\'' +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", dep_id='" + dep_id + '\'' +
                ", creator='" + creator + '\'' +
                ", changer='" + changer + '\'' +
                ", up_dep_id='" + up_dep_id + '\'' +
                ", cr_dep_id='" + cr_dep_id + '\'' +
                ", realname='" + realname + '\'' +
                ", entryTime='" + entryTime + '\'' +
                ", tel='" + tel + '\'' +
                ", department='" + department + '\'' +
                ", skin='" + skin + '\'' +
                ", company='" + company + '\'' +
                ", duties='" + duties + '\'' +
                ", sex='" + sex + '\'' +
                ", in_service_time='" + in_service_time + '\'' +
                '}';
    }
}
