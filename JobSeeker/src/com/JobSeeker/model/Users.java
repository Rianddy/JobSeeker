package com.JobSeeker.model;
public class Users {
	private String username;
	private String repassword;
	private String passwordMD5;
	private String email;
	private String validationCode;
	private String official;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRepassword() throws Exception{
		return common.Encrypter.md5Encrypt(repassword);
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getPasswordMD5() throws Exception{
		return common.Encrypter.md5Encrypt(passwordMD5);
	}
	public void setPasswordMD5(String passwordMD5) {
		this.passwordMD5 = passwordMD5;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getValidationCode() {
		return validationCode;
	}
	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}
	public String getOfficial() {
		return official;
	}
	public void setOfficial(String official) {
		this.official = official;
	}
}
