package com.lanzuan.entity;

import com.lanzuan.common.support.Pair;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Date;

//db.mallUser.update({"phone":"18888888888"},{"$set":{"email":"haha12345678987456325565225525@qq.com"}},false,true)
//db.mallUser.update({"phone":"18888888888"},{"$set":{"name":"买光你茶叶"}},false,true)
//db.mallUser.update({},{"$set":{"becomeMemberDate":new Date("2016-10-03")}},false,true)
//db.mallUser.update({},{"$set":{"directSaleMember":true}},false,true)

//db.mallUser.insert({"password" : "96e79218965eb72c92a549dd5a330112","phone" : "13000000000","registerInviteCode" : "111111","activated" : true,"directSaleMember":true,"becomeMemberDate" : new Date("2016-10-03")})
//db.mallUser.insert({"password" : "96e79218965eb72c92a549dd5a330112","phone" : "13666666666","registerInviteCode" : "111111","activated" : true,"becomeMemberDate" : new Date("2016-10-03")})
//db.mallUser.update({"phone":"13666666666"},{"$set":{"membershipPath" : "/57ac237d2f02c8fa50a9b5f9/57b8c0ed2a0a9820f0a2e6cf/57f3d8413c46b7660c653942"}},false,true)
//db.mallUser.update({"phone":"13000000000"},{"$set":{"membershipPath" : "/57ac237d2f02c8fa50a9b5f9/57f3df3d3c46b7660c653943"}},false,true)
//db.mallUser.find({ "directSaleMember" : true , "becomeMemberDate" : { "$gte" : new Date("2016-10-13T03:59:59.996Z") , "$lt" : new Date("2016-10-13T04:00:00.996Z")}})
//db.mallUser.find({$where:"this.membershipPath == '/'+this._id"})
@Document(collection = "mallUser")
public class User {

    @Id private String id;
    @Field(value = "name")
    @Length(min=2,max=20)
    private String name;
    @Field
    private String realName;
    @Field(value = "sex")
    private String sex;
    @Field(value = "height")
    private Integer height;
    @Length(min=6)
    @Field("password")
    private String password;
    @Field("email")
    @Email
    @Indexed
    private String email;
    @Field(value = "userCategory")
    private String userCategory;//1注册用户 2,。经销商
//    @DBRef private Set<Address> address;
    @Field("registerTime")
    private Date registerTime;

    @Field("validateCode")
    private String validateCode;
    @Field("lastActivateTime")
    private Date lastActivateTime;
    @Field("phone")
    private String phone;
    private String loginStatus;

    @Field(value = "addresses")
    private String[] addresses;
    @Field private Boolean disabled;//禁用
    @Field(value = "idCardNo")
    private String idCardNo;
    @Field
    private String registerInviteCode;
    @Field
    private boolean directSaleMember;

    @Field
    private Boolean activated;//激活
    @Field
    private int market;//1:一市场 2：二市场
    private User directUpperUser;

    /**
     *     在会员系统中的路径，以id作为路径的一级，可以用文件夹路径类比，文件夹名就是用户的id
     *     每个下一级会员的membershipPath都会在上一级的membershipPath基础上加上自己的id连接，用"/"分隔
     */

    @Field
    private String membershipPath;
    @Field
    private Date becomeMemberDate;//成为会员的日期

    @Transient
    private String showName;

    @Transient
    private Pair<User> directLowerUsers;
    @Transient
    private String marketString;

    public String getMarketString() {
        return market==0?null:(market==1?"一市场":(market==2?"二市场":null));
    }

    public String[] getAddresses() {
        return addresses;
    }

    public String getMembershipPath() {
        return membershipPath;
    }

    public void setMembershipPath(String membershipPath) {
        this.membershipPath = membershipPath;
    }



    public Pair<User> getDirectLowerUsers() {
        return directLowerUsers;
    }

    public void setDirectLowerUsers(Pair<User> directLowerUsers) {
        this.directLowerUsers = directLowerUsers;
    }

//    public AuthorizeInfo getAuthorizeInfo() {
//        return authorizeInfo;
//    }
//
//    public void setAuthorizeInfo(AuthorizeInfo authorizeInfo) {
//        this.authorizeInfo = authorizeInfo;
//    }


    public int getMarket() {
        return market;
    }

    public void setMarket(int market) {
        this.market = market;
    }

    public User getDirectUpperUser() {
        return directUpperUser;
    }

    public void setDirectUpperUser(User directUpperUser) {
        this.directUpperUser = directUpperUser;
    }

    public Date getBecomeMemberDate() {
        return becomeMemberDate;
    }

    public void setBecomeMemberDate(Date becomeMemberDate) {
        this.becomeMemberDate = becomeMemberDate;
    }


    public String getRegisterInviteCode() {
        return registerInviteCode;
    }

    public void setRegisterInviteCode(String registerInviteCode) {
        this.registerInviteCode = registerInviteCode;
    }


    public void setAddresses(String[] addresses) {
        this.addresses = addresses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }


    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public Date getLastActivateTime() {
        return lastActivateTime;
    }

    public void setLastActivateTime(Date lastActivateTime) {
        this.lastActivateTime = lastActivateTime;
    }



    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }



    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }



    /**
     * 这些和登录相关，不保存
     */

    @Transient
    private Boolean remember;
    @NotNull
    @Transient
    private String rePassword;
    @Transient
    private String loginStr;
    @Transient
    private Boolean mergeCart;

    public boolean isDirectSaleMember() {
        return directSaleMember;
    }

    public void setDirectSaleMember(boolean directSaleMember) {
        this.directSaleMember = directSaleMember;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Boolean getMergeCart() {
        return mergeCart;
    }

    public void setMergeCart(Boolean mergeCart) {
        this.mergeCart = mergeCart;
    }

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getLoginStr() {
        return loginStr;
    }

    public void setLoginStr(String loginStr) {
        this.loginStr = loginStr;
    }


    public String getShowName() {
        return this.name!=null?this.name:(this.phone!=null?this.phone:null);
    }

}
