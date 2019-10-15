package com.ss.lms;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopy;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.entity.Publisher;

@RestController
@SpringBootApplication
@RequestMapping("/lms*")
public class LmsApplication
{
	/*
	 * Huge thanks to https://www.baeldung.com/spring-rest-template-list
	 */

	@Autowired
	RestTemplate rt;

	private final String adminUri = "http://localhost:8100/lms/admin";
	private final String libUri = "http://localhost:8090/lms/librarian";
	private final String borrowerUri = "http://localhost:8080/lms/borrower";

	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

	public static void main(String[] args)
	{
		SpringApplication.run(LmsApplication.class, args);
	}

	/*************************************************
	 * 
	 * ALL ADMIN CREATE OPERATIONS
	 * 
	 *************************************************/

	@PostMapping(path = "/admin/author", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
										consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> createAuthor(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @RequestBody Author author)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		HttpEntity<Author> request = new HttpEntity<Author>(author, headers);

		return rt.exchange(adminUri + "/author", HttpMethod.POST, request, Author.class);
	}

	@PostMapping(path = "/admin/publisher", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
											consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Publisher> createPublisher(@RequestHeader("Accept") String accept,
													@RequestHeader("Content-Type") String contentType,
													@RequestBody Publisher publisher)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		HttpEntity<Publisher> request = new HttpEntity<Publisher>(publisher, headers);

		return rt.exchange(adminUri + "/publisher", HttpMethod.POST, request, Publisher.class);
	}
	
	@PostMapping(path = "/admin/book", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
										consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book> createBook(@RequestHeader("Accept") String accept,
											@RequestHeader("Content-Type") String contentType,
											@RequestBody Book book)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		HttpEntity<Book> request = new HttpEntity<Book>(book, headers);
		
		return rt.exchange(adminUri + "/book", HttpMethod.POST, request, Book.class);
	}
	
	@PostMapping(path = "/admin/branch", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
										consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<LibraryBranch> createBranch(@RequestHeader("Accept") String accept,
											@RequestHeader("Content-Type") String contentType,
											@RequestBody LibraryBranch branch)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		HttpEntity<LibraryBranch> request = new HttpEntity<LibraryBranch>(branch, headers);
		
		return rt.exchange(adminUri + "/branch", HttpMethod.POST, request, LibraryBranch.class);
	}
	
	@PostMapping(path = "/admin/borrower", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
										consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Borrower> createBorrower(@RequestHeader("Accept") String accept,
											@RequestHeader("Content-Type") String contentType,
											@RequestBody Borrower borrower)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		HttpEntity<Borrower> request = new HttpEntity<Borrower>(borrower, headers);
		
		return rt.exchange(adminUri + "/borrower", HttpMethod.POST, request, Borrower.class);
	}
	
	/*************************************************
	 * 
	 * ALL ADMIN READ OPERATIONS
	 * 
	 *************************************************/

	@GetMapping(value = "/admin/author/{authorId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> readAuthorById(@RequestHeader("Accept") String accept, @PathVariable Integer authorId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/author/" + authorId, HttpMethod.GET, new HttpEntity<Author>(headers),
				Author.class);
	}

	@GetMapping(value = "/admin/author", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Iterable<Author>> readAuthorAll(@RequestHeader("Accept") String accept)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/author", HttpMethod.GET, new HttpEntity<Author>(headers),
				new ParameterizedTypeReference<Iterable<Author>>(){});
	}

	@GetMapping(value = "/admin/publisher/{publisherId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Publisher> readPublisherById(@RequestHeader("Accept") String accept, @PathVariable Integer publisherId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/publisher/" + publisherId, HttpMethod.GET, new HttpEntity<Publisher>(headers),
				Publisher.class);
	}
	
	@GetMapping(value = "/admin/publisher", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Iterable<Publisher>> readPublisherAll(@RequestHeader("Accept") String accept)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/publisher", HttpMethod.GET, new HttpEntity<Publisher>(headers),
				new ParameterizedTypeReference<Iterable<Publisher>>(){});
	}

	@GetMapping(value = "/admin/book/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book> readBookById(@RequestHeader("Accept") String accept, @PathVariable Integer bookId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/book/" + bookId, HttpMethod.GET, new HttpEntity<Book>(headers),
				Book.class);
	}
	
	@GetMapping(value = "/admin/book", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Iterable<Book>> readBookAll(@RequestHeader("Accept") String accept)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/book", HttpMethod.GET, new HttpEntity<Book>(headers),
				new ParameterizedTypeReference<Iterable<Book>>(){});
	}

	@GetMapping(value = "/admin/branch/{branchId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<LibraryBranch> readLibraryBranchById(@RequestHeader("Accept") String accept, @PathVariable Integer branchId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/branch/" + branchId, HttpMethod.GET, new HttpEntity<LibraryBranch>(headers),
				LibraryBranch.class);
	}
	
	@GetMapping(value = "/admin/branch", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Iterable<LibraryBranch>> readLibraryBranchAll(@RequestHeader("Accept") String accept)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/branch", HttpMethod.GET, new HttpEntity<LibraryBranch>(headers),
				new ParameterizedTypeReference<Iterable<LibraryBranch>>(){});
	}

	@GetMapping(value = "/admin/borrower/{cardNo}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Borrower> readBorrowerById(@RequestHeader("Accept") String accept, @PathVariable Integer cardNo)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/borrower/" + cardNo, HttpMethod.GET, new HttpEntity<Borrower>(headers),
				Borrower.class);
	}
	
	@GetMapping(value = "/admin/borrower", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Iterable<Borrower>> readBorrowerAll(@RequestHeader("Accept") String accept)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/borrower", HttpMethod.GET, new HttpEntity<Borrower>(headers),
				new ParameterizedTypeReference<Iterable<Borrower>>(){});
	}

	@GetMapping(value = "/admin/loan/borrower/{cardNo}/branch/{branchId}/book/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<BookLoan> readBookLoanById(@RequestHeader("Accept") String accept, @PathVariable("cardNo") Integer cardNo,
														@PathVariable("branchId") Integer branchId, @PathVariable("bookId") Integer bookId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/loan/borrower/" + cardNo + "/branch/" + branchId + "/book/" + bookId,
				HttpMethod.GET,
				new HttpEntity<BookLoan>(headers),
				BookLoan.class);
	}
	
	@GetMapping(value = "/admin/loan", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Iterable<BookLoan>> readBookLoanAll(@RequestHeader("Accept") String accept)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/loan",
				HttpMethod.GET,
				new HttpEntity<BookLoan>(headers),
				new ParameterizedTypeReference<Iterable<BookLoan>>(){});
	}
	
	
	/*************************************************
	 * 
	 * ALL ADMIN UPDATE OPERATIONS
	 * 
	 *************************************************/

	@PutMapping(value = "/admin/author/{authorId}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
													produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> adminUpdateAuthor(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @PathVariable Integer authorId,
			@RequestBody Author author)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange((adminUri + "/author/" + authorId), HttpMethod.PUT, new HttpEntity<Author>(author, headers),
				Author.class);
	}
	
	@PutMapping(value = "/admin/publisher/{publisherId}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
													produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Publisher> adminUpdatePublisher(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @PathVariable Integer publisherId,
			@RequestBody Publisher publisher)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange((adminUri + "/publisher/" + publisherId), HttpMethod.PUT, new HttpEntity<Publisher>(publisher, headers),
				Publisher.class);
	}
	
	@PutMapping(value = "/admin/book/{bookId}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
													produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book> adminUpdateBook(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @PathVariable Integer bookId,
			@RequestBody Book book)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange((adminUri + "/book/" + bookId), HttpMethod.PUT, new HttpEntity<Book>(book, headers),
				Book.class);
	}
	
	@PutMapping(value = "/admin/branch/{branchId}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
													produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<LibraryBranch> adminUpdateLibraryBranch(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @PathVariable Integer branchId,
			@RequestBody LibraryBranch branch)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange((adminUri + "/branch/" + branchId), HttpMethod.PUT, new HttpEntity<LibraryBranch>(branch, headers),
				LibraryBranch.class);
	}
	
	@PutMapping(value = "/admin/borrower/{cardNo}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
													produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Borrower> adminUpdateBorrower(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @PathVariable Integer cardNo,
			@RequestBody Borrower borrower)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange((adminUri + "/borrower/" + cardNo), HttpMethod.PUT, new HttpEntity<Borrower>(borrower, headers),
				Borrower.class);
	}
	
	@PutMapping(value = "/admin/loan/borrower/{cardNo}/branch/{branchId}/book/{bookId}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
													produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<BookLoan> adminUpdateBookLoan(
			@RequestHeader("Accept") String accept, @RequestBody BookLoan bookLoan,
			@RequestHeader("Content-Type") String contentType, @PathVariable("cardNo") Integer cardNo, 
			@PathVariable("branchId") Integer branchId, @PathVariable("bookId") Integer bookId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange((adminUri + "/loan/borrower/" + cardNo + "/branch/" + branchId + "/book/" + bookId),
				HttpMethod.PUT,
				new HttpEntity<BookLoan>(bookLoan, headers),
				BookLoan.class);
	}

	/*************************************************
	 * 
	 * ALL ADMIN DELETE OPERATIONS
	 * 
	 *************************************************/

	@DeleteMapping(value = "/admin/author/{authorId}")
	public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable Integer authorId)
	{
		RequestEntity<HttpStatus> request = new RequestEntity<>(HttpMethod.DELETE,
				URI.create(adminUri + "/author/" + authorId));

		return rt.exchange(request, HttpStatus.class);
	}

	/*************************************************
	 * 
	 * ALL LIBRARIAN CREATE OPERATIONS
	 * 
	 *************************************************/

	@PostMapping(value = "/librarian/bookcopy/", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            									 consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<BookCopy> librarianCreateBookCopy(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @RequestBody BookCopy bookCopy) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange(libUri + "/bookcopy/", HttpMethod.POST, new HttpEntity<BookCopy>(bookCopy, headers), BookCopy.class);
	}
	
	/*************************************************
	 * 
	 * ALL LIBRARIAN READ OPERATIONS
	 * 
	 *************************************************/
	
	@GetMapping(value = "/librarian/branch/{branchId}", produces =
		{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<LibraryBranch> librarianReadLibraryBranchById(@RequestHeader("Accept") String accept, @PathVariable Integer branchId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(libUri + "/branch/" + branchId, HttpMethod.GET, new HttpEntity<LibraryBranch>(headers),
				LibraryBranch.class);
	}

	@GetMapping(value = "/librarian/branch/", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Iterable<LibraryBranch>> librarianReadAllLibraryBranches(@RequestHeader("Accept") String accept) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(libUri + "/branch/", HttpMethod.GET, new HttpEntity<LibraryBranch>(headers),
				new ParameterizedTypeReference<Iterable<LibraryBranch>>() {});

	}

	@GetMapping(value = "/librarian/book/", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Iterable<Book>> librarianReadAllBooks(@RequestHeader("Accept") String accept) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(libUri + "/book/", HttpMethod.GET, 
				new HttpEntity<Book>(headers), 
				new ParameterizedTypeReference<Iterable<Book>>() {});

	}
	
	@GetMapping(value = "/librarian/book/{bookId}", produces =
		{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book> librarianReadBookById(@RequestHeader("Accept") String accept, @PathVariable Integer bookId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(libUri + "/book/" + bookId, HttpMethod.GET, new HttpEntity<Book>(headers), Book.class);
	}

    @GetMapping(value = "/librarian/bookcopy/book/{bookId}/branch/{branchId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<BookCopy> librarianReadBookCopyById(@RequestHeader("Accept") String accept,
                                                        @PathVariable("branchId") Integer branchId, 
                                                        @PathVariable("bookId") Integer bookId)
    {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", accept);
        
        return rt.exchange(libUri + "/bookcopy/book/" + bookId + "/branch/" + branchId,
                HttpMethod.GET,
                new HttpEntity<BookCopy>(headers),
                BookCopy.class);
    }

	/*************************************************
	 * 
	 * ALL LIBRARIAN UPDATE OPERATIONS
	 * 
	 *************************************************/
	
	@PutMapping(value = "/librarian/branch/{branchId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<LibraryBranch> updateLibraryBranch(@RequestHeader("Accept") String accept,
															@RequestHeader("Content-Type") String contentType, 
															@PathVariable Integer branchId,
															@RequestBody LibraryBranch libraryBranch) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange(libUri + "/branch/" + branchId, HttpMethod.PUT,
				new HttpEntity<LibraryBranch>(libraryBranch, headers), LibraryBranch.class);

	}

	@PutMapping(value = "/librarian/bookcopy/book/{bookId}/branch/{branchId}", consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<BookCopy> updateBookCopy(@RequestHeader("Accept") String accept,
													@RequestHeader("Content-Type") String contentType, 
													@PathVariable Integer bookId,
													@PathVariable Integer branchId, 
													@RequestBody BookCopy bookCopy) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		System.out.println("Helloskdljflsjdf");
		return rt.exchange(libUri + "/bookcopy/book/" + bookId + "/branch/" + branchId, HttpMethod.PUT,
				new HttpEntity<BookCopy>(bookCopy, headers), BookCopy.class);

	}
	
	/*************************************************
	 * 
	 * ALL LIBRARIAN DELETE OPERATIONS
	 * 
	 *************************************************/
	
	@DeleteMapping(value = "/librarian/bookcopy/book/{bookId}/branch/{branchId}")
	public ResponseEntity<HttpStatus> librarianDeleteAuthor(@PathVariable Integer bookId, @PathVariable Integer branchId) {
		RequestEntity<HttpStatus> request = new RequestEntity<>(HttpMethod.DELETE,
				URI.create(libUri + "/bookcopy/book/" + bookId + "/branch/" + branchId));
		return rt.exchange(request, HttpStatus.class);
	}

}
