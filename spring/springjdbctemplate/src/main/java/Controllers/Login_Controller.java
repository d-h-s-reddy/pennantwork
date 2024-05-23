package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.DAO_Login;
import Models.Users;

@Controller
public class Login_Controller {

	@Autowired
	DAO_Login dl;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logs", method = RequestMethod.POST)
	public String home(@ModelAttribute("user") Users user, Model model) {
		System.out.println("hi");
		System.out.println(user.getUserName());
		System.out.println(user.getPassWord());

		boolean flag = dl.LoginInfo(user.getUserName(), user.getPassWord());
		if (flag == true) {
			return "redirect:/allproducts";
		} else {
			return "error";
		}
	}
}
