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
	private final String borrowerUri = "http://localhost:8070/lms/borrower";

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

	@GetMapping(value = "/admin/author/{authorId}", produces =
		{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> readAuthorById(@RequestHeader("Accept") String accept, @PathVariable Integer authorId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/author/" + authorId, HttpMethod.GET, new HttpEntity<Author>(headers),
				Author.class);
	}

	@GetMapping(value = "/admin/author", produces =
		{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Iterable<Author>> readAuthorAll(@RequestHeader("Accept") String accept)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);

		return rt.exchange(adminUri + "/author", HttpMethod.GET, new HttpEntity<Author>(headers),
				new ParameterizedTypeReference<Iterable<Author>>(){});
	}

	/*************************************************
	 * 
	 * ALL ADMIN UPDATE OPERATIONS
	 * 
	 *************************************************/

	@PutMapping(value = "/admin/author/{authorId}", consumes =
		{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces =
		{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> updateAuthor(@RequestHeader("Accept") String accept,
			@RequestHeader("Content-Type") String contentType, @PathVariable Integer authorId,
			@RequestBody Author author)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange((adminUri + "/author/" + authorId), HttpMethod.PUT, new HttpEntity<Author>(author, headers),
				Author.class);
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
	public ResponseEntity<BookCopy> createBookCopy(@RequestHeader("Accept") String accept,
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
	public ResponseEntity<LibraryBranch> readLibraryBranchById(@RequestHeader("Accept") String accept,
			String contentType, @PathVariable Integer branchId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange(libUri + "/branch/" + branchId, HttpMethod.GET, new HttpEntity<LibraryBranch>(headers),
				LibraryBranch.class);
	}

	@GetMapping(value = "/librarian/branch/", produces =
		{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<LibraryBranch> readAllLibraryBranches(@RequestHeader("Accept") String accept,
			String contentType)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange(libUri + "/branch/", HttpMethod.GET, new HttpEntity<LibraryBranch>(headers),
				LibraryBranch.class);
	}

	@GetMapping(value = "/librarian/book/", produces =
		{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book> readAllBooks(@RequestHeader("Accept") String accept, String contentType)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange(libUri + "/book/", HttpMethod.GET, new HttpEntity<Book>(headers), Book.class);
	}

	@GetMapping(value = "/librarian/book/{bookId}", produces =
		{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book> readBookById(@RequestHeader("Accept") String accept, String contentType,
			@PathVariable Integer bookId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange(libUri + "/book/" + bookId, HttpMethod.GET, new HttpEntity<Book>(headers), Book.class);
	}

	@GetMapping(value = "/librarian/bookcopy/book/{bookId}/branch/{branchId}", produces =
		{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<BookCopy> readBookCopyById(@RequestHeader("Accept") String accept, String contentType,
			@PathVariable Integer bookId, @PathVariable Integer branchId)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);

		return rt.exchange(libUri + "/bookcopy/book/" + bookId + "/branch/" + branchId, HttpMethod.GET,
				new HttpEntity<Object>(headers), BookCopy.class);
	}

	/*************************************************
	 * 
	 * ALL LIBRARIAN UPDATE OPERATIONS
	 * 
	 *************************************************/
	
	/*************************************************
	 * 
	 * ALL LIBRARIAN DELETE OPERATIONS
	 * 
	 *************************************************/
	
	@DeleteMapping(value = "/librarian/bookcopy/book/{bookId}/branch/{branchId}")
	public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable Integer bookId, @PathVariable Integer branchId) {
		RequestEntity<HttpStatus> request = new RequestEntity<>(HttpMethod.DELETE,
				URI.create(libUri + "/bookcopy/book/" + bookId + "/branch/" + branchId));
		return rt.exchange(request, HttpStatus.class);
	}

}
