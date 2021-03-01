package com.stucray.bookstoreapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookSummary {
    String id;
    String title;
    String description;
    String thumbnail;
}
