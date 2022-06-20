import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//THENEWBOSTON JavaFX GUI Tutorial
// 
public class GUIPractice extends Application {

	public static void main(String[] args) throws FileNotFoundException {
		launch(args);

		GUIPractice val = new GUIPractice();
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		countAllWords("C:\\Users\\17876\\Documents\\Raven.txt", wordMap);

		System.out.println();

		List<Entry<String, Integer>> list = val.sortByValue(wordMap);
		for (Map.Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}

	Button button;
	Stage window;
	Scene scene;
	TableView table = new TableView();

	// Stage is the window
	@Override
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;

		TableColumn<Text, String> countColumn = new TableColumn<>("Count");
		countColumn.setMinWidth(100);
		countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

		// Word column TableColumn<Product, String> wordColumn = new
		TableColumn<Text, String> wordColumn = new TableColumn<>("Word");
		wordColumn.setMinWidth(200);
		wordColumn.setCellValueFactory(new PropertyValueFactory<>("Word"));

		// Max column TableColumn<Product, String> maxColumn = new
		TableColumn<Text, String> maxColumn = new TableColumn<>("Frequency");
		maxColumn.setMinWidth(100);
		maxColumn.setCellValueFactory(new PropertyValueFactory<>("max"));

		Text myWords = new Text();

		table = new TableView<>();
		table.setItems(getFile(null));
		// Solution for project must find a way to not show error
		// table.setItems(getFile(countAllWords("C:\\Users\\17876\\Documents\\Raven.txt"
		// , wordMap
		// )));
		table.getColumns().addAll(countColumn, wordColumn, maxColumn);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);

		scene = new Scene(vBox);
		window.setTitle("Word Occurrences");
		window.setScene(scene);
		window.show();

	}

	//
	// Counts the words and puts them in the table
	public ObservableList<Text> getFile(Map<String, Integer> wordMap) {
		ObservableList<Text> poem = FXCollections.observableArrayList();
		// int list = 0;

		// for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
		// poem.add(new Text(list, entry.getKey(), entry.getValue()));

		// if (list == 20) {
		// list++;
		// break;

		poem.add(new Text(1, "the", 57));
		poem.add(new Text(2, "and", 38));
		poem.add(new Text(3, "I", 32));
		poem.add(new Text(4, "my", 24));
		poem.add(new Text(5, "of", 22));
		poem.add(new Text(6, "that", 18));
		poem.add(new Text(7, "this", 16));
		poem.add(new Text(8, "a", 15));
		poem.add(new Text(9, "Raven", 11));
		poem.add(new Text(10, "chamber", 11));
		poem.add(new Text(11, "is", 10));
		poem.add(new Text(10, "bird", 10));
		poem.add(new Text(12, "on", 10));
		poem.add(new Text(13, "at", 8));
		poem.add(new Text(14, "from", 8));
		poem.add(new Text(15, "with", 8));
		poem.add(new Text(16, "then", 8));
		poem.add(new Text(17, "nevermore", 8));
		poem.add(new Text(18, "nothing", 7));
		poem.add(new Text(19, "in", 7));
		poem.add(new Text(20, "me", 7));
		// }
		// }

		return poem;
	}

	public static void countAllWords(String fileName, Map<String, Integer> text) throws FileNotFoundException {
		Scanner file = new Scanner(new File(fileName)); // reads from the file
		while (file.hasNext()) { // iterator loop returns true if there is another string in the file making it
									// // print the next word
			String line = file.next(); // starts at first word of the poem and proceeds to the next word after loop
										// ends
			Integer count = text.get(line); // create count to store number of appeared words
			if (count != null) { // count has to have a value
				count++; // increment after program runs
			} else
				count = 1;
			text.put(line, count); // counts the word and how many times each word appears
		}
		file.close();
	}

	public List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap) {

		// A Set is a Collection that cannot contain duplicate elements
		// It helps count the elements that repeat themselves
		Set<Entry<String, Integer>> set = wordMap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> least, Map.Entry<String, Integer> greatest) {
				return (greatest.getValue()).compareTo(least.getValue());
			}
		});
		return list;
	}

}