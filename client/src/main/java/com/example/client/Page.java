package com.example.client;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Page {

    private Long id;

    private Document document;

    private int page_number;

    private String content;

}
