package atlantbh.restaurants.models;

import java.util.List;

public class PaginatedResult<T> {
    private int pageSize;
    private int pageNumber;
    private List<T> data;
    private int available;


    public PaginatedResult(int pageSize, int pageNumber, List<T> data, int available) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.data = data;
        this.available = available;
    }

    public PaginatedResult() {

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
