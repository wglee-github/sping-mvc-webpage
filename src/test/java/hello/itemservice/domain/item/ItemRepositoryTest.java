package hello.itemservice.domain.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

	ItemRepository itemRepository = new ItemRepository();
	
	@AfterEach
	void afterEach() {
		itemRepository.clearStore();
	}
	
	@Test
	void save() {
		// given
		Item item = new Item("itemA", 10000, 10);
		Item saveItem = itemRepository.save(item);	
		
		// when
		Item findItem = itemRepository.findByid(item.getId());
		
		// then
		assertThat(findItem).isEqualTo(findItem);
		assertThat(findItem.getItemName()).isEqualTo("itemA");
		assertThat(findItem.getPrice()).isEqualTo(10000);
		assertThat(findItem.getQuantity()).isEqualTo(10);
		
	}

	@Test
	void findAll() {
		// given
		Item item1 = new Item("item1", 10000, 10);
		itemRepository.save(item1);
		
		Item item2 = new Item("item2", 20000, 20);
		itemRepository.save(item2);	
		
		// when
		List<Item> items = itemRepository.findAll();
		
		// then
		assertThat(items.size()).isEqualTo(2);
		assertThat(items).contains(item1, item2);
		assertThat(items.get(0).getPrice()).isEqualTo(10000);
	}
	
	@Test
	void updateItem() {
		// given
		Item item = new Item("item1", 1000, 2000);
		
		Item saveItem = itemRepository.save(item);
		Long itemId = saveItem.getId();
		
		// when
		Item updateItem = new Item("item2", 5000, 6000);
		itemRepository.update(itemId, updateItem);
		
		Item findItem = itemRepository.findByid(itemId);
		
		// then
		assertThat(findItem.getItemName()).isEqualTo(findItem.getItemName());
		assertThat(findItem.getPrice()).isEqualTo(findItem.getPrice());
		assertThat(findItem.getQuantity()).isEqualTo(findItem.getQuantity());
	}
	
}
