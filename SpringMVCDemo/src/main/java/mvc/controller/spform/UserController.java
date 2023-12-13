package mvc.controller.spform;

import java.util.List;

import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.bean.spform.EducationData;
import mvc.bean.spform.InterestData;
import mvc.bean.spform.SexData;
import mvc.bean.spform.User;
import mvc.dao.spform.DataDao;
import mvc.dao.spform.UserDao;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	//@Qualifier("dataDaoImplInMemory")
	private DataDao dataDao;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/")
	public String index(@ModelAttribute User user, Model model) {
		List<EducationData> educations = dataDao.findAllEducationDatas();
		List<SexData> sexs = dataDao.findAllSexDatas();
		List<InterestData> interests = dataDao.finAllInterestDatas();
		
		model.addAttribute("educations", educations); // 將教育程度資料傳給 jsp
		model.addAttribute("sexs", sexs); // 將性別資料傳給 jsp
		model.addAttribute("interests", interests); // 將興趣資料傳給 jsp
		model.addAttribute("users", userDao.findAllUsers()); // 取得目前最新 users 資料
		
		return "spform/user";
	}
	
	@PostMapping("/")
	public String addUser(User user) {
		int rowcount = userDao.addUser(user);
		System.out.println("add User rowcount = " + rowcount);
		return "redirect:/mvc/user/"; // 重導到 user 首頁
	}
	
	
}