package hello.itemservice.web.basic;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

	private final ItemRepository itemRepository;

	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "basic/items";
	}

	@GetMapping("/{itemId}")
	public String item(@PathVariable Long itemId, Model model) {
		Item findItem = itemRepository.findByid(itemId);
		model.addAttribute("item", findItem);
		return "basic/item";
	}
	
	@GetMapping("/add")
	public String addForm() {
		return "basic/addForm";
	}
	
	@PostMapping("/add1")
	public String save1(@ModelAttribute() Item item, Model model) {
		itemRepository.save(item);
		model.addAttribute("item", item);
		return "basic/item"; 
	}
	
	@PostMapping("/add2")
	public String save2(@ModelAttribute("item") Item item) {
		itemRepository.save(item);
//		model.addAttribute("item", saveItem); // 자동 추가, 생략 가능
		return "basic/item"; 
	}
	
	@PostMapping("/add3")
	public String save3(@ModelAttribute Item item) {
		itemRepository.save(item);
		return "basic/item"; 
	}
	
	@PostMapping("/add4")
	public String save4(Item item) {
		itemRepository.save(item);
		return "basic/item"; 
	}
	
	@PostMapping("/add5")
	public String save5(Item item) {
		itemRepository.save(item);
		return "redirect:/basic/items/"+item.getId(); 
	}
	
	@PostMapping("/add")
	public String save6(Item item, RedirectAttributes redirectAttributes) {
		Item saveItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId",saveItem.getId());
		redirectAttributes.addAttribute("status",true);
		
		return "redirect:/basic/items/{itemId}"; 
	}
	
	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, Model model) {
		Item findItem = itemRepository.findByid(itemId);
		model.addAttribute("item", findItem);
		return "basic/editForm"; 
	}
	
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item,  Model model) {
		Item findItem = itemRepository.findByid(itemId);
		findItem.setItemName(item.getItemName());
		findItem.setPrice(item.getPrice());
		findItem.setQuantity(item.getQuantity());
		itemRepository.update(itemId, findItem);
		
		model.addAttribute("item", findItem);
		return "redirect:/basic/items/{itemId}"; 
	}
	
	@PostConstruct
	public void init() {
		itemRepository.save(new Item("itemA", 10000, 10));
		itemRepository.save(new Item("itemB", 20000, 20));
	}
}
