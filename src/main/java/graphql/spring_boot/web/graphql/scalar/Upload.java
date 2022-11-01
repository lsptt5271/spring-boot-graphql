package graphql.spring_boot.web.graphql.scalar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Upload {

    private String fileName;
    private String contentType;
    private byte[] content;

}