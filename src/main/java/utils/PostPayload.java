package utils;

import java.util.HashMap;
import java.util.Map;

public class PostPayload {
	
	public static Map<String,Object> AddNewList = new HashMap<>();
	
	public static Map<String,Object> AddNewBoard = new HashMap<>();
	
	public static Map<String,Object> AddNewCard = new HashMap<>();
	
	public static Map<String,Object> addNewBoard(String name){
		AddNewBoard.put("name", name);
		return AddNewBoard;
	}
	
	public static Map<String,Object> addNewList(String idBoard){
		AddNewList.put("name", FileReader.props.getProperty("list-name", "default-test-name"));
		AddNewList.put("idBoard", idBoard);
		return AddNewList;
	}
	
	public static Map<String,Object> addNewCard(String idList){
		AddNewCard.put("name", FileReader.props.getProperty("card-name", "default-test-name"));
		AddNewCard.put("idList", idList);
		return AddNewCard;
	}

}
