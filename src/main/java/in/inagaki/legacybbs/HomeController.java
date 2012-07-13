package in.inagaki.legacybbs;

import in.inagaki.legacybbs.bean.Entry;
import in.inagaki.legacybbs.bean.Pages;
import in.inagaki.legacybbs.dao.EntryDao;
import in.inagaki.legacybbs.util.CommonUtil;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private EntryDao entryDao;

	@ModelAttribute
	public Entry setUpForm() {
		return new Entry();
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, params = { "page" })
	public String home(@RequestParam("page") int page, Model model) {
		logger.info("Welcome home!");
		addAttributes(model, page);
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return home(1, model);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, params = { "page" })
	public String onSubmit(@RequestParam("page") int page, @Valid Entry entry,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			addAttributes(model, page);
			return "home";
		}
		entry.setIp(CommonUtil.getIpFormClient());
		entryDao.addEntry(entry);
		return "redirect:";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String onSubmit(@Valid Entry entry, BindingResult bindingResult,
			Model model) {
		return onSubmit(1, entry, bindingResult, model);
	}

	private void addAttributes(Model model, int page) {
		List<Entry> entryList = entryDao.getEntryList(page);
		for (Entry entry : entryList) {
			entry.setMessage(entry.getMessage().replaceAll("\n", "<br />"));
		}
		model.addAttribute(entryList);
		Pages pages = new Pages(entryDao.getPages(), page);
		model.addAttribute(pages);
	}

}
