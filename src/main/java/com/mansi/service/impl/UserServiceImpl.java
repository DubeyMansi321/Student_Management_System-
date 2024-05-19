package com.mansi.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mansi.binding.LoginForm;
import com.mansi.binding.SignUpForm;
import com.mansi.binding.UnlockForm;
import com.mansi.entity.UserDtlsEntity;
import com.mansi.repository.UserDtlsRepo;
import com.mansi.service.UserService;
import com.mansi.utils.EmailUtils;
import com.mansi.utils.PwdUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDtlsRepo userRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private HttpSession session;
	@Override
	public String login(LoginForm form) {

		UserDtlsEntity entity = userRepo.findByEmailAndPwd(form.getEmail(), form.getPwd());
		if (entity == null) {
			return "Invalid Credentials";
		}
		if (entity.getAccStatus().equals("LOCKED")) {
			return "Your Account Locked";
		}
		session.setAttribute("userId", entity.getUserId());
		return "SUCCESS";
	}

	@Override
	public boolean signUp(SignUpForm form) {

		UserDtlsEntity user = userRepo.findByEmail(form.getEmail());
		if (user != null) {
			return false;
		}
		UserDtlsEntity entity = new UserDtlsEntity();
		BeanUtils.copyProperties(form, entity);
		String tempPwd = PwdUtils.randomPwd(6);
		entity.setPwd(tempPwd);
		entity.setAccStatus("LOCKED");
		userRepo.save(entity);

		String to = form.getEmail();

		String subject = "Unlock your Account";
		StringBuffer body = new StringBuffer("");
		body.append("<h1> Use below temporary pwd to unlock your account</h1>");

		body.append("Temporary Pwd: " + tempPwd);
		body.append("<br/>");

		body.append("<a href=\"http://localhost:9090/unlock?email=" + to + "\">Click here to unlock your Account</a>");
		emailUtils.sendEmail(to, subject, body.toString());
		return true;
	}

	@Override
	public boolean unlockAccount(UnlockForm form) {

		String email = form.getEmail();
		UserDtlsEntity entity = userRepo.findByEmail(email);
		if (entity.getPwd().equals(form.getTempPwd())) {
			entity.setPwd(form.getNewPwd());
			entity.setAccStatus("UNLOCKED");
			userRepo.save(entity);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean forgotPwd(String email) {

		UserDtlsEntity entity = userRepo.findByEmail(email);
		if (entity == null) {
			return false;
		}

		String sublect = "Recover password";
		String body = "Your Password:: " + entity.getPwd();
		emailUtils.sendEmail(email, sublect, body);

		return true;
	}
}
