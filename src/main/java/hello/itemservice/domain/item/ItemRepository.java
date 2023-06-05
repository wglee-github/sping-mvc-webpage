package hello.itemservice.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

	// 실무에서는 ConcurrentHashMap 사용하자. Thread-safe 하다.
	private static final Map<Long, Item> store = new HashMap<Long, Item>();
	Map<String, String> map = new ConcurrentHashMap<>();
	private static long sequence = 0L;
	
	public Item save(Item item) {
		item.setId(++sequence);
		store.put(item.getId(), item);
		return item;
	}
	
	public Item findByid(Long itemId) {
		return store.get(itemId);
	}
	
	public List<Item> findAll(){
		return new ArrayList<>(store.values());
	}
	
	public void update(Long itemId, Item updateParam) {
		Item findItem = findByid(itemId);
		findItem.setItemName(updateParam.getItemName());
		findItem.setPrice(updateParam.getPrice());
		findItem.setQuantity(updateParam.getQuantity());
		store.put(itemId, findItem);
	}

	public void clearStore() {
		store.clear();
	}
}
