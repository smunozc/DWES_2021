package org.iesalixar.aleal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student implements Serializable {
	
	
	private static final long serialVersionUID = 2258581567332090958L;
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surnames")
	private String surnames;
	
	@Column(name="nid")
	private Integer nid;
	
	@Column(name="birthyear")
	private Integer birthyear;
	
	@Column(name="course")
	private String course;
	
	@OneToMany(mappedBy="student",fetch=FetchType.EAGER)   
	//@JoinColumn(name="book_id")
	private Set<Book> books;
	
	public Student() {
		super();
		this.books = new HashSet<Book>();
	}

	public Student(int id, String name, String surnames, Integer nid, Integer birthyear, String course,
			Set<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.surnames = surnames;
		this.nid = nid;
		this.birthyear = birthyear;
		this.course = course;
		this.books = books;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public Integer getNid() {
		return nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public Integer getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(Integer birthyear) {
		this.birthyear = birthyear;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	public void addBook(Book book) {
		this.books.add(book);
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((birthyear == null) ? 0 : birthyear.hashCode());
//		result = prime * result + ((books == null) ? 0 : books.hashCode());
//		result = prime * result + ((course == null) ? 0 : course.hashCode());
//		result = prime * result + id;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + ((nid == null) ? 0 : nid.hashCode());
//		result = prime * result + ((surnames == null) ? 0 : surnames.hashCode());
//		return result;
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (birthyear == null) {
			if (other.birthyear != null)
				return false;
		} else if (!birthyear.equals(other.birthyear))
			return false;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nid == null) {
			if (other.nid != null)
				return false;
		} else if (!nid.equals(other.nid))
			return false;
		if (surnames == null) {
			if (other.surnames != null)
				return false;
		} else if (!surnames.equals(other.surnames))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", surnames=" + surnames + ", nid=" + nid + ", birthyear="
				+ birthyear + ", course=" + course + ", books=" + booksWithoutStudentInformation() + "]";
	}
	
	private String booksWithoutStudentInformation() {
		List<String> result = new ArrayList<String>();
		getBooks().iterator().forEachRemaining(s -> {
				result.add(s.toStringWithoutStudent());
		});
		return Arrays.toString(result.stream().toArray());
	}

	
	
	

}
