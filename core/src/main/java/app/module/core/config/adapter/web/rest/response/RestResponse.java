package app.module.core.config.adapter.web.rest.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RestResponse<T> {
    private String message;

    private T item;
    private List<T> items;
}
