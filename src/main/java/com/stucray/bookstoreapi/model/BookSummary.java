package com.stucray.bookstoreapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookSummary {
    String id;
    String title;
    String description;
    String thumbnail;
}
