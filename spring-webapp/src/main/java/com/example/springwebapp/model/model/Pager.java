package com.example.springwebapp.model.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pager {
    public int total_Item;
    public int current_Page;
    public int page_Size;
    public int total_Page;
    public int start_Page;
    public int end_Page;

    public Pager(int total_Item, int page, int page_Size)
    {
        int totalPage = (int)Math.ceil((double)total_Item / (double)page_Size);

        int startPage = page - 5;
        int endPage = page + 4;

        if (startPage <= 0)
        {
            endPage = endPage - (startPage - 1);
            startPage = 1;
        }

        if (endPage > totalPage)
        {
            endPage = totalPage;
            if (endPage > 10)
            {
                startPage = endPage - 9;
            }
        }

        this.total_Item = total_Item;
        this.current_Page = page;
        this.page_Size = page_Size;
        this.total_Page = totalPage;
        this.start_Page = startPage;
        this.end_Page = endPage;
    }
}
