package com.mansi.service;

import com.mansi.binding.LoginForm;
import com.mansi.binding.SignUpForm;
import com.mansi.binding.UnlockForm;

public interface UserService {
	
	public String login(LoginForm form);
	
	public boolean signUp(SignUpForm form);
	
	public boolean unlockAccount(UnlockForm form);
	
	public boolean forgotPwd(String  email);
}
