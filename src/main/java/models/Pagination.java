package models;
public class Pagination {
    private int currentPage=1, rowPerPage=3, totalPage=1, totalRow=0;

    public void recalcTotalPage(int tr) {
        totalRow=tr; totalPage=(int)Math.ceil((double)tr/rowPerPage);
        if(totalPage<1) totalPage=1;
    }

    public int getOffset() { return (currentPage-1)*rowPerPage; }

    public boolean isFirstPage() { return currentPage==1; }

    public boolean isLastPage() { return currentPage==totalPage; }

    public void goFirst() { currentPage=1; }

    public void goLast() { currentPage=totalPage; }

    public void goNext() { if(!isLastPage()) currentPage++; }

    public void goPrev() { if(!isFirstPage()) currentPage--; }

    public void goToPage(int p) { if(p>=1 && p <=totalPage) currentPage=p; }

    public int getCurrentPage() { return currentPage; }

    public int getRowPerPage() { return rowPerPage; }

    public int getTotalPage() { return totalPage; }

    public int getTotalRow() { return totalRow; }

    public void setRowPerPage(int n){ rowPerPage=n; }
}