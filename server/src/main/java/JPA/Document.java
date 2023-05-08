package JPA;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    public static int getNextPageNumber(EntityManager entityManager, Long document_id){
        Query max = entityManager.createQuery("SELECT max(page.page_number) FROM Page as page WHERE page.document = :document_id");
        max.setParameter("document_id", entityManager.find(Document.class, document_id));

        return (int)(max.getResultList().get(0)) + 1;
    }
    public int getNextPageNumber(EntityManager entityManager){
        Query max = entityManager.createQuery("SELECT max(page.page_number) FROM Page as page WHERE page.document = :document_id");
        max.setParameter("document_id", entityManager.find(Document.class, id));

        return (int)(max.getResultList().get(0)) + 1;
    }

}
