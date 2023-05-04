import EndPoints.DocumentEndPoint;
import JPA.Student;
import JPA.Teacher;
import io.quarkus.runtime.Quarkus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		Quarkus.run(args);
	}

}
