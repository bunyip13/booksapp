package pl.bastus.bookapp;

import java.io.*;

public class FileController {
    private File file;
    private Book book;

    /* FILE OPERATIONS */
	@SuppressWarnings("unused")
	private void writeFile(File f, String w) {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)))) {
			bw.write(w);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.err.format("IOException: %s%n", ioe);
		}
	}

	@SuppressWarnings("unused")
	private void writeFile2(File f, String w) {
		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f, true)))) {
			pw.println(w);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.err.format("IOException: %s%n", ioe);
		}
	}

	@SuppressWarnings("unused")
	private void writeArrayToFile() { // TODO: write array to file
        writeFile2(file, book.getBookTitle() + "/"
				+ book.getAuthor() + "/"
				+ book.getBookDate() + "/"
				+ (book.getBookAddedDateString()) + "/n");
	}
    /*
	@SuppressWarnings("unused")
	private void getBooksFromFile(File f) {
		BufferedReader br;
		String line;
		try {
			br = new BufferedReader(new FileReader(f));
			while ((line = br.readLine()) != null) {
				addBookFromFile(line);
			}
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.err.format("IOException: %s%n", ioe);
		}
	}


	private void addBookFromFile(String l) {
		String[] elements = l.split("/");
		Book nextBook = new Book(elements[0], elements[1], elements[2], elements[3], elements[4]);
		books.add(nextBook);
	}

	public void save(String fileName) throws FileNotFoundException {
		BufferedWriter writer = null;
		try {

			writer = new BufferedWriter(new FileWriter(fileName));
			for ( int i = 0; i < nbrMovies; i++)
			{
				writer.write(movies[i].getName());
				writer.newLine();
				writer.flush();
			}

		} catch(IOException ex) {
			ex.printStackTrace();
		} finally{
			if(writer!=null){
				writer.close();
			}
		}
	}
    */
}
