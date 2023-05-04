package JPA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String description;
	
	private String credits;
	
	private String semester;

	public Course() {
	}

	public Course(Long id, String name, String description, String credits, String semester) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.credits = credits;
		this.semester = semester;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCredits() {
		return credits;
	}

	public String getSemester() {
		return semester;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
}
