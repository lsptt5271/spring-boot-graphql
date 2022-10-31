package graphql.spring_boot.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@FieldNameConstants
@Getter
@Setter
@Table(name = "departments")
@NoArgsConstructor
public class DepartmentEntity {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

}
