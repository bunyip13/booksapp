package pl.bastus.bookapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.bastus.bookapp.BookDAO;
import pl.bastus.bookapp.Book;
import pl.bastus.bookapp.dto.BookDTO;

@Controller
public class BooksController {

	@Autowired
    private BookDAO bookDAO;
	
	@RequestMapping("/list")
	public String bookList(Model model) {
		model.addAttribute("books", bookDAO.getBooks());
		return "list";
	}
	
	@RequestMapping("/add")
	public String addBook(HttpServletRequest request,  @ModelAttribute("bookDto") @Valid BookDTO bookDto, BindingResult result) {
		if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
			Book book = new Book();
            book.setBookDate(bookDto.getDate());
			book.setBookTitle(bookDto.getTitle());
			book.setAuthor(bookDto.getAuthor());
			book.setBookPrice(bookDto.getPrice());
			bookDAO.addBookToBooks(book);
			return "redirect:/list";
		}
		return "addd";
	}
	
	@RequestMapping("/book-{id}")
	public String bookAttributes(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("book", bookDAO.getBookByID(id));
		return "attributes";
	}
	
}
