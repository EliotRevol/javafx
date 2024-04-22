package thecoursemaker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuHandler {
	private MenuBar menubar;
	private int menu_count;
	
	public MenuHandler() {
		menubar = new MenuBar();
		menu_count=0;
	}
	
	public void addMenu(String... menu_names) {
		for (String menu_name : menu_names) {
			menu_count++;
			menubar.getMenus().add(new Menu(menu_name));
		}
	}
	
	public MenuBar getMenuBar() {
		return menubar;
	}
	
	public int getMenuCount() {
		return menu_count;
	}
	
	public Menu findMenu(String menu_name) {
		Menu temp_menu;
		for(int i=0;i<menu_count;i++) {
			temp_menu = menubar.getMenus().get(i);
			if(temp_menu.getText().equals(menu_name)) {
				return temp_menu;
			}
		}
		return null;
	}
	
	public MenuItem findMenuItem(Menu menu,String menu_name) {
		MenuItem temp_menu;
		int numItem = menu.getItems().size();
		for(int i=0;i<numItem;i++) {
			temp_menu = menu.getItems().get(i);
			if(temp_menu.getText().equals(menu_name)) {
				return temp_menu;
			}
		}
		return null;
	}
	
	public MenuItem findMenuItem(String menu_str,String menu_name) {
		MenuItem temp_menu;
		Menu menu = this.findMenu(menu_str);
		int numItem = menu.getItems().size();
		for(int i=0;i<numItem;i++) {
			temp_menu = menu.getItems().get(i);
			if(temp_menu.getText().equals(menu_name)) {
				return temp_menu;
			}
		}
		return null;
	}
	
	public void makeUnclickable(String menu_name, String... menu_items) {
		for(String menu_item:menu_items) {
			MenuItem temp_menu = findMenuItem(menu_name,menu_item);
			temp_menu.setDisable(true);
		}
	}
	
	public void makeClickable(String menu_name, String... menu_items) {
		for(String menu_item:menu_items) {
			MenuItem temp_menu = findMenuItem(menu_name,menu_item);
			temp_menu.setDisable(false);
		}
	}
	
	
	public void addMenuItem(String menu_name,String... menu_item_names) {
		Menu to_add_menu = this.findMenu(menu_name);
		for(String menu_item_name:menu_item_names) {
			to_add_menu.getItems().add(new MenuItem(menu_item_name));
		}
	}
	
	public void setCoordinates(int x, int y) {
		this.menubar.setLayoutX(x);
		this.menubar.setLayoutY(y);
	}
	
	public void setX(int x) {
		
		this.menubar.setTranslateY(-100);
		this.menubar.setLayoutX(x);;
	}
	
	public void setY(int y) {
		this.menubar.setLayoutX(y);
	}
	
	public void setAction(String menu,String menu_item,EventHandler<ActionEvent> action) {
		MenuItem item = this.findMenuItem(menu, menu_item);
		item.setOnAction(action);
	}
}
