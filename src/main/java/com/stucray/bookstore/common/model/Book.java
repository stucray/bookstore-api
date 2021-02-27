package com.stucray.bookstore.common.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Book {

    private String id;
    private String googleId;
    private String title;
    private String subtitle;
    private String description;
    private String publisher;
    private String publishedDate;
    private List<String> authors = new ArrayList<>();
    private List<String> categories;
    private String thumbnail;
    private String smallThumbnail;
}
