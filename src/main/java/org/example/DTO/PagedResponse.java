package org.example.DTO;

import java.util.List;

public class PagedResponse<T> {
    private List<T> content;
    private int currentPage;
    private int totalPages;

    public PagedResponse(List<T> content, int currentPage, int totalPages) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public int getCurrentPage() { return currentPage; }
    public int getTotalPages() { return totalPages; }
    public List<T> getContent() { return content; }
}

