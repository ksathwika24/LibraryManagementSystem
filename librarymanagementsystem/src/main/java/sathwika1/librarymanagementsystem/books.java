package sathwika1.librarymanagementsystem;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "published_year")
    private int publishedYear;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}

	public books(int id, String title, String author, int publishedYear) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishedYear = publishedYear;
	}

	@Override
	public String toString() {
		return "books [id=" + id + ", title=" + title + ", author=" + author + ", publishedYear=" + publishedYear + "]";
	}

	public books() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Getters and setters
    // Constructors
}
