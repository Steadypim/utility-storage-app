package dev.steadypim.thewhitehw.homework1.api;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@FieldDefaults(level = PRIVATE)
public class PageDTO<T> {
    List<T> content;
    long totalElements;

    public PageDTO(List<T> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }
}
