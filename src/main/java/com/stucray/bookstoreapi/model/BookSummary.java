package com.stucray.bookstoreapi.model;

import lombok.Data;

@Data
public class BookSummary {
    String id;
    String title;
    String description;
    String thumbnail;
}
