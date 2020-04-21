package cn.fjut.gmxx.campusclub.pagehelper;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
/**
 * @author : shenjindui
 * @date : 2020-01-12 14:14
 **/
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页
    private int currentPage;
    //每页的数量
    private int pageSize;
    //当前页的数量
    private int size;
    //总记录数
    private long total;
    //结果集
    private List<T> list;

    public PageInfo() {
    }
    /**
     * 包装Page对象
     *
     * @param list page结果
     */
    public PageInfo(List<T> list, Map<String,Object> parmas) {
        if (list instanceof Collection) {
            if(parmas.get("currentPage")!=null){
                this.currentPage = Integer.parseInt(parmas.get("currentPage").toString());
            }
            this.pageSize = list.size();
            this.list = list;
            this.size = list.size();
            if(parmas.get("total")!=null){
                this.total = Integer.parseInt(parmas.get("total").toString());
            }
        }
    }
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("currentPage=").append(currentPage);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", list=").append(list);
        sb.append(", navigatepageNums=");
        return sb.toString();
    }
}

