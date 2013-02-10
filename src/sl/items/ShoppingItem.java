package sl.items;

public class ShoppingItem {

	//
	String store, name, genre;
	int price;
	int id;
	
	public ShoppingItem(String store, String name, int price, String genre) {
		
		this.store = store;
		this.name = name;
		this.price = price;
		this.genre = genre;
		
	}//public ShoppingItem(String store, String name, int price, String genre)

	public ShoppingItem(String store, String name, int price, String genre, int id) {
		
		this.store = store;
		this.name = name;
		this.price = price;
		this.genre = genre;
		this.id = id;
		
	}//public ShoppingItem(String store, String name, int price, String genre)

	public String getStore() {
		return store;
	}

	public String getName() {
		return name;
	}

	public String getGenre() {
		return genre;
	}

	public int getPrice() {
		return price;
	}

	public int getId() {
		return id;
	}
	
}
