package io.github.mocanjie.base.mycommon.pager;

/**
 * 分页处理器 - 统一管理分页对象的创建
 *
 * @author canjie.mo
 * @since 2024年9月24日
 */
public class PagerHandler {

    /**
     * 从分页参数创建分页对象
     *
     * @param param 分页参数
     * @return 分页对象
     */
    public static <T> Pager<T> createPager(PagerParam param) {
        return new Pager<>(param.getPageNum(), param.getPageSize());
    }

    /**
     * 创建分页对象（带空值检查）
     *
     * @param pageNum 页码，null时默认为1
     * @param pageSize 页面大小，null时默认为10
     * @return 分页对象
     */
    public static <T> Pager<T> createPager(Integer pageNum, Integer pageSize) {
        return new Pager<>(pageNum != null ? pageNum : 1,
                          pageSize != null ? pageSize : 10);
    }

    /**
     * 创建默认分页对象（第1页，每页10条）
     *
     * @return 默认分页对象
     */
    public static <T> Pager<T> createDefaultPager() {
        return new Pager<>();
    }

    /**
     * 创建指定页面大小的分页对象（从第1页开始）
     *
     * @param pageSize 页面大小
     * @return 分页对象
     */
    public static <T> Pager<T> createPager(int pageSize) {
        return new Pager<>(pageSize);
    }

    /**
     * 创建不分页对象（用于查询全部数据）
     *
     * @return 不分页对象，ignoreCount设为true
     */
    public static <T> Pager<T> createNoPager() {
        return new Pager<T>().setIgnoreCount(true);
    }

}