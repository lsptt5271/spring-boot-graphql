package graphql.spring_boot.web.graphql.scalar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.apache.catalina.core.ApplicationPart;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

public class ExtendedScalar {
    public static final GraphQLScalarType UploadScalarType = GraphQLScalarType.newScalar()
            .name("Upload")
            .description("A file part in a multipart request")
            .coercing(new Coercing<Upload, Void>() {
                @Override
                public Void serialize(Object dataFetcherResult) {
                    throw new CoercingSerializeException("Upload is a File-only type");
                }

                @Override
                public Upload parseValue(Object input) {
                    if (input instanceof ApplicationPart) {
                        ApplicationPart part = (ApplicationPart) input;
                        try (InputStream is = part.getInputStream()) {
                            String fileName = part.getSubmittedFileName();
                            String contentType = part.getContentType();
                            byte[] content = new byte[(int) part.getSize()];
                            IOUtils.readFully(is, content);
                            part.delete();

                            return new Upload(fileName, contentType, content);
                        } catch (IOException e) {
                            throw new CoercingParseValueException("failed to read upload file");
                        }
                    } else if (Objects.isNull(input)) {
                        return null;
                    } else {
                        throw new CoercingParseValueException("different input");
                    }
                }

                @Override
                public Upload parseLiteral(Object input) {
                    throw new CoercingParseLiteralException("must use variables to specify Upload values");
                }
            }).build();
}