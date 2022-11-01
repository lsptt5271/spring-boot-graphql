package graphql.spring_boot.web.graphql.type;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

    private int id;
    private String name;
    private int departmentId;

}
